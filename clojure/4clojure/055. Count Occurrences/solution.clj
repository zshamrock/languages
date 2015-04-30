; 55. Count Occurrences
; http://www.4clojure.com/problem/55

(in-ns 'user)

(require '[clojure.test :refer [is]])

(defn count-occurrences [v]
  (into {} (map #(vector (key %) (count (val %))) (group-by identity v))))

(defn- run-all-tests [] 
  (is (=  (count-occurrences  [1 1 2 3 2 1 1])  {1 4, 2 2, 3 1}))
  (is (=  (count-occurrences  [:b :a :b :a :b])  {:a 2, :b 3}))
  (is (=  (count-occurrences '([1 2]  [1 3]  [1 3]))  {[1 2] 1, [1 3] 2})))

(run-all-tests)
