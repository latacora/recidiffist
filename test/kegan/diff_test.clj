(ns kegan.diff-test
  (:require [kegan.diff :as d]
            [clojure.test :as t :refer [deftest testing is are]]))

(deftest entries-tests
  (are [obj entries] (= (d/entries obj) entries)
    {:a 1}
    [[[:a] 1]]

    {:a 1
     :b 1}
    [[[:a] 1]
     [[:b] 1]]

    {:a 1
     :b {:c {:d 1}}}
    [[[:a] 1]
     [[:b :c :d] 1]]))

(deftest fancy-diff-tests
  (are [a b diff] (= (d/fancy-diff a b) diff)
    {}
    {}
    {:added []
     :changed []
     :removed []}

    {:a 1}
    {}
    {:added []
     :changed []
     :removed [[[:a] 1]]}

    {}
    {:a 1}
    {:added [[[:a] 1]]
     :changed []
     :removed []}))
