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

(deftest pairwise-diffing-tests
  (are [elems diffs] (= (into [] s/pairwise-diffing elems)
                        diffs)
    []
    []

    update-elems
    update-diffs))
