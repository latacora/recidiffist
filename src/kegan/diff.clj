(ns kegan.diff
  (:require
   [clojure.data :refer [diff]]
   [clojure.set :as set]))

(defn entries
  "Given a nested object, compute the entries in it
  as (fully-qualified path, value) pairs."
  ([obj]
   (entries [] obj))
  ([prefix obj]
   (->> obj
        (mapcat
         (fn [[k v]]
           (let [path (conj prefix k)]
             (if (map? v)
               (entries path v)
               [[path v]]))))
        (into #{}))))

(defn fancy-diff
  [prev curr]
  (let [[old new _] (diff prev curr)
        [old new] (->> [old new] (map entries) (map (partial into {})))
        [old-keys new-keys]  (->> [old new] (map keys) (map (partial into #{})))]
    {:added (map (juxt identity new) (set/difference new-keys old-keys))
     :changed (map (juxt identity old new) (set/intersection new-keys old-keys))
     :removed (map (juxt identity old) (set/difference old-keys new-keys))}))
