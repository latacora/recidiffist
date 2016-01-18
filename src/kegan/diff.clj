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
        [old new] (->> [old new]
                       (map entries)
                       (map (partial into {})))
        [removed-ks added-ks changed-ks] (->> [old new]
                                              (map keys)
                                              (map (partial into #{}))
                                              (apply diff))]
    {:added (->> added-ks (map (juxt identity new)) (into #{}))
     :changed (->> changed-ks (map (juxt identity old new)) (into #{}))
     :removed (->> removed-ks (map (juxt identity old)) (into #{}))}))

(defn with-sentinel
  "Sometimes, your nested value will always have all keys, but use a
  sentinel to mean that the real value is unknown"
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
