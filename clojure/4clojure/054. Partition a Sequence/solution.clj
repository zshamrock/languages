; 054. Partition a Sequence
; https://www.4clojure.com/problem/054

(in-ns 'user)

(require '[clojure.test :refer [is]])

(defn partition-seq [n s]
  (loop [i 1 
         items s
         p []        
         current-seq []]
    (if (seq items)
      (if (zero? (rem i n))
        (recur (inc i) (next items) (conj p (conj current-seq (first items))) [])
        (recur (inc i) (next items) p (conj current-seq (first items))))
      p)))

(defn- run-all-tests []
  (is (= (partition-seq 3 (range 9)) '((0 1 2) (3 4 5) (6 7 8))))  
  (is (=  (partition-seq 2  (range 8)) '((0 1)  (2 3)  (4 5)  (6 7))))
  (is (=  (partition-seq 3  (range 8)) '((0 1 2)  (3 4 5))))
  )

(run-all-tests)
