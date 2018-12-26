(ns recidiffist.stream-test
  (:require
   [recidiffist.stream :as s]
   [clojure.test :as t :refer [deftest testing is are]]
   [manifold.deferred :as md]
   [manifold.stream :as ms]))

(def update-elems
  [{:a 1}
   {:a 2}
   {:a 3}])

(def update-diffs
  [{:added #{}
    :changed #{[[:a] 1 2]}
    :removed #{}}
   {:added #{}
    :changed #{[[:a] 2 3]}
    :removed #{}}])

(def add-update-remove-elems
  [{:a 1}
   {:a 2
    :b 1}
   {:b 2}])

(def add-update-remove-diffs
  [{:added #{[[:b] 1]}
    :changed #{[[:a] 1 2]}
    :removed #{}}
   {:added #{}
    :changed #{[[:b] 1 2]}
    :removed #{[[:a] 2]}}])

(deftest diffs-tests
  (are [elems diffs]
      (let [into-reference (into [] s/pairwise-diffing elems)]
        (->> elems ms/->source s/diffs ms/stream->seq (= diffs into-reference)))

    []
    []

    update-elems
    update-diffs

    add-update-remove-elems
    add-update-remove-diffs))
