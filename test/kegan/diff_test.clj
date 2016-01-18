(ns kegan.diff-test
  (:require
   [kegan.diff :as d]
   [clojure.test :as t :refer [deftest testing is are]]))

(deftest entries-tests
  (are [obj entries] (= entries (d/entries obj))
    {:a 1}
    #{[[:a] 1]}

    {:a 1
     :b 1}
    #{[[:a] 1]
      [[:b] 1]}

    {:a 1
     :b {:c {:d 1}}}
    #{[[:a] 1]
      [[:b :c :d] 1]}

    {:a 1
     :b {:c {:d 1}
         :i {:j 1}
         :p {:q 1}}}
    #{[[:a] 1]
      [[:b :c :d] 1]
      [[:b :i :j] 1]
      [[:b :p :q] 1]}))

(deftest fancy-diff-tests
  (are [a b diff] (= diff (d/fancy-diff a b))
    {}
    {}
    {:added #{}
     :changed #{}
     :removed #{}}

    {:a 1}
    {}
    {:added #{}
     :changed #{}
     :removed #{[[:a] 1]}}

    {}
    {:a 1}
    {:added #{[[:a] 1]}
     :changed #{}
     :removed #{}}

    {:a 1}
    {:a 2}
    {:added #{}
     :changed #{[[:a] 1 2]}
     :removed #{}}

    {:a {:b 1}}
    {:a {:b 2}}
    {:added #{}
     :changed #{[[:a :b] 1 2]}
     :removed #{}}

    {:a {:b 1}
     :c 1}
    {:a {:b 2}
     :c 1}
    {:added #{}
     :changed #{[[:a :b] 1 2]}
     :removed #{}}

    {:a {:b 1}}
    {:a {:b 1}
     :c {:d 1}}
    {:added #{[[:c :d] 1]}
     :changed #{}
     :removed #{}}

    {:a {:b 1}
     :c {:d 1}}
    {:a {:b 1}}
    {:added #{}
     :changed #{}
     :removed #{[[:c :d] 1]}}

    {:a {:b 1}
     :c {:d 1}
     :x 1}
    {:a {:b 1}
     :e {:f 1}
     :x 2}
    {:added #{[[:e :f] 1]}
     :changed #{[[:x] 1 2]}
     :removed #{[[:c :d] 1]}}

    {:a {:b 1
         :c 1}
     :p {:q 1}
     :x 1}
    {:a {:b 1
         :c 2}
     :i {:j 1}
     :x 2}
    {:added #{[[:i :j] 1]}
     :changed #{[[:x] 1 2]
                [[:a :c] 1 2]}
     :removed #{[[:p :q] 1]}}))

(deftest with-sentinel-tests
  (testing "defaults (nil sentinel, all paths eligible)"
    (are [a b diff] (= diff (d/with-sentinel (d/fancy-diff a b)))
      {}
      {}
      {:added #{}
       :changed #{}
       :removed #{}}

      {:a nil}
      {:a 1}
      {:added #{[[:a] 1]}
       :changed #{}
       :removed #{}}))
  (testing "explicit sentinel"
    (are [sentinel a b diff] (-> (d/fancy-diff a b)
                                 (d/with-sentinel {:sentinel sentinel})
                                 (= diff))
      nil
      {}
      {}
      {:added #{}
       :changed #{}
       :removed #{}}

      nil
      {:a nil}
      {:a 1}
      {:added #{[[:a] 1]}
       :changed #{}
       :removed #{}}

      :xyzzy
      {:a :xyzzy}
      {:a 1}
      {:added #{[[:a] 1]}
       :changed #{}
       :removed #{}}))
  (testing "explicit predicate"
    (are [pred a b diff] (-> (d/fancy-diff a b)
                             (d/with-sentinel {:path-pred pred})
                             (= diff))
      (constantly true)
      {}
      {}
      {:added #{}
       :changed #{}
       :removed #{}}

      (constantly true)
      {:a nil}
      {:a 1}
      {:added #{[[:a] 1]}
       :changed #{}
       :removed #{}}

      (partial = [:a])
      {:a nil}
      {:a 1}
      {:added #{[[:a] 1]}
       :changed #{}
       :removed #{}}

      (partial = [:a])
      {:a nil
       :b nil}
      {:a 1
       :b 1}
      {:added #{[[:a] 1]}
       :changed #{[[:b] nil 1]}
       :removed #{}})))
