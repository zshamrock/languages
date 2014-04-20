; 79. Triangle Minimal Path
; https://www.4clojure.com/problem/79

(require '[clojure.test :refer [is]])

; the idea is start from the bottom of the triangle, and reduce each level by all possible path weights
(defn triangle-min-path [triangle]
  (let [reverse-triangle (reverse triangle)]
    (loop [path (map list (first reverse-triangle))
           smaller-triangle (next reverse-triangle)]
      (if (seq smaller-triangle)
        (recur 
          (map-indexed #(map (partial + %2) (concat (nth path %1) (nth path (inc %1)))) (first smaller-triangle))
          (next smaller-triangle))
        (apply min (first path))))))

(defn- run-all-tests []
  (is (= 7 (triangle-min-path 
             '([1]
              [2 4]
             [5 1 4]
            [2 3 4 5])))) ; 1->2->1->3

  (is (= 20 (triangle-min-path
              '([3]
               [2 4]
              [1 9 3]
             [9 9 2 4]
            [4 6 6 7 8]
           [5 7 3 5 1 4])))) ; 3->4->3->2->7->1
)

(run-all-tests)
