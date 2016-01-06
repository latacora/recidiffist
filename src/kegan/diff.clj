(ns kegan.diff)

(defn entries
  "Given a nested object, compute the entries in it
  as (fully-qualified path, value) pairs."
  ([obj]
   (entries [] obj))
  ([prefix obj]
   (for [[k v] obj]
     (let [path (conj prefix k)]
       (if (map? v)
         (apply vec (entries path v))
         [path v])))))
