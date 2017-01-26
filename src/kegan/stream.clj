(ns kegan.stream
  (:require
   [kegan.diff :refer [fancy-diff]]
   [manifold.stream :as ms]
   [net.cgrand.xforms :as x]))

(defn ^:private fancy-diff-pair
  [[a b]]
  (fancy-diff a b))

(def pairwise-diffing
  "A transducer that partitions incoming elements into pairs (AB BC
  CD...) and then fancy-diffs them."
  (comp (x/partition 2 1 nil (x/into []))
        (map fancy-diff-pair)))

(defn diffs
  "Given a stream, provide a stream of diffs."
  [source]
  (ms/transform pairwise-diffing source))
