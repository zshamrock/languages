; 171. Intervals
; https://www.4clojure.com/problem/171

(in-ns 'user)

(require '[clojure.test :refer [is]])

(defn into-intervals [sq]
  (letfn [(split-into-ranges [sq] 
            (reduce (fn [intervals num]
                      (let [last-interval (last intervals)]
                        (if (seq last-interval)
                          (let [last-num (last last-interval)]
                            (if (= (inc last-num) num)
                              (conj (subvec intervals 0 (dec (count intervals))) (conj last-interval num))
                              (conj intervals [num])))
                          [[num]])))          
                    []
                    (distinct (sort sq))))]
    (let [ranges (split-into-ranges sq)]
      (mapv (juxt first last) ranges)))
  )

(defn- run-all-tests []
  (is (= (into-intervals [1 2 3]) [[1 3]]))
  (is (= (into-intervals [10 9 8 1 2 3]) [[1 3] [8 10]]))
  (is (= (into-intervals [1 1 1 1 1 1 1]) [[1 1]]))
  (is (= (into-intervals []) []))
  (is (= (into-intervals [19 4 17 1 3 10 2 13 13 2 16 4 2 15 13 9 6 14 2 11])
     [[1 4] [6 6] [9 11] [13 17] [19 19]]))
  )

(run-all-tests)

; cgrand solution, pretty nice one, good usage case of (juxt)
(fn [c]
  (let [s (set c)]
    (map (juxt first last)
         (take-nth 2 (partition-by #(contains? s %)
                                   (and (seq s)
                                        (range (apply min s) (inc (apply max s)))))))))
