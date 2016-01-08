(ns kegan.stream-test
  (:require
   [kegan.stream :as s]
   [clojure.test :as t :refer [deftest testing is are]]))

(deftest pairwise-diffing-tests
  (are [elems diffs] (= (into [] s/pairwise-diffing elems)
                        diffs)
    []
    []))
