(ns recidiffist.stream
  (:require
   [recidiffist.diff :as kd]
   [manifold.stream :as ms]
   [net.cgrand.xforms :as x]))

(def pairwise-diffing
  "A transducer that partitions incoming elements into pairs (AB BC
  CD...) and then fancy-diffs them."
  (comp (x/partition 2 1) (map (partial apply kd/fancy-diff))))

(def diffs
  "Given a stream, provide a stream of diffs."
  (partial ms/transform pairwise-diffing))
