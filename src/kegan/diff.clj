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
   (into #{}
         (mapcat
          (fn [[k v]]
            (let [path (conj prefix k)]
              (if (map? v)
                (entries path v)
                [[path v]]))))
         obj)))

(defn ^:private annotate
  "Annotate ks in a set with the given view fns."
  [ks & views]
  (into #{} (map (apply juxt identity views)) ks))

(defn fancy-diff
  [prev curr]
  (let [[old new _] (diff prev curr)
        [old new] (->> [old new]
                       (map entries)
                       (map (partial into {})))
        [removed-ks added-ks changed-ks] (->> [old new]
                                              (map keys)
                                              (map (partial into #{}))
                                              (apply diff))]
    {:added (annotate added-ks new)
     :changed (annotate changed-ks old new)
     :removed (annotate removed-ks old)}))

(defn with-sentinel
  "Sometimes, your nested value will always have all keys, but use a
  sentinel to mean that the real value is unknown."
  ([fancy-diff]
   (with-sentinel fancy-diff {}))
  ([{:keys [changed] :as fancy-diff}
    {:keys [sentinel path-pred]
     :or {sentinel nil
          path-pred (constantly true)}}]
   (let [is-sentinel? (comp (partial = sentinel) second)
         path-ok? (comp path-pred first)
         actually-added? (every-pred is-sentinel? path-ok?)
         actually-added (into #{} (filter actually-added? changed))]
     (-> fancy-diff
         (update :changed set/difference actually-added)
         (update :added into (for [[path _ value] actually-added]
                               [path value]))))))
