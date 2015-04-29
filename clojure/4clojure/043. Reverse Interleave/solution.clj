; 43. Reverse Interleave
; http://www.4clojure.com/problem/43

(in-ns 'user)

(require '[clojure.test :refer [is]])

(defn reverse-interleave [v n]
  (let [indexed (map-indexed #(vector %1 %2) v)
        grouped (group-by #(mod (first %) n) indexed)
        result (map #(map second %) (vals grouped))]
    result))



(run-all-tests)
