(ns kegan.stream-test
  (:require
   [kegan.stream :as s]
   [clojure.test :as t :refer [deftest testing is are]]
   [manifold.deferred :as md]
   [manifold.stream :as ms]))

(def update-elems
  [{:a 1}
   {:a 2}
   {:a 3}])

(def update-diffs
  [{:added [] :changed [[[:a] 1 2]] :removed []}
   {:added [] :changed [[[:a] 2 3]] :removed []}])

(def add-update-remove-elems
  [{:a 1}
   {:a 2
    :b 1}
   {:b 2}])

(def add-update-remove-diffs
  [{:added [[[:b] 1]], :changed [[[:a] 1 2]], :removed []}
   {:added [], :changed [[[:b] 1 2]], :removed [[[:a] 2]]}])

(deftest pairwise-diffing-tests
  (are [elems diffs] (= diffs
                        (butlast
                         ;; last record is because of finalization
                         (into [] s/pairwise-diffing elems)))
    []
    nil ;; (butlast [])

    update-elems
    update-diffs

    add-update-remove-elems
    add-update-remove-diffs))

(deftest diffs-tests
  (are [elems diffs] (= diffs
                        (-> (ms/->source elems) (s/diffs) (ms/stream->seq)))
    []
    []

    update-elems
    update-diffs

    add-update-remove-elems
    add-update-remove-diffs))
