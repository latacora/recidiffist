(ns kegan.stream-test
  (:require
   [kegan.stream :as s]
   [clojure.test :as t :refer [deftest testing is are]]))
(def update-elems
  [{:a 1}
   {:a 2}
   {:a 3}])

(def update-diffs
  [{:added [] :changed [[[:a] 1 2]] :removed []}
   {:added [] :changed [[[:a] 2 3]] :removed []}
   {:added [] :changed [] :removed [[[:a] 3]]}])

(def add-update-remove-elems
  [{:a 1}
   {:a 2 :b 1}
   {:b 2}])

(def add-update-remove-diffs
  [{:added [[[:a] 1]], :changed [], :removed []}
   {:added [[[:b] 1]], :changed [[[:a] 1 2]], :removed []}
   {:added [], :changed [[[:b] 1 2]], :removed [[[:a] 2]]}])


(deftest pairwise-diffing-tests
  (are [elems diffs] (= (into [] s/pairwise-diffing elems)
                        diffs)
    []
    []

    update-elems
    update-diffs

    add-update-remove-elems
    add-update-remove-diffs))
