; 106. Number Maze
; https://www.4clojure.com/problem/106

(require '[clojure.test :refer [is]])

(defn shortest-path [start end]
  (letfn [(operations-set [value last-op]
            (concat [+] 
                    (if (not= last-op /) 
                      [*] 
                      []) ; no reason to double just halved number

                    (if (and (not= last-op *) (even? value)) 
                      [/] ; only halve if not doubled and even
                      [])
                    ))
          (next-values [values]
            (into {} 
                  (apply concat (for [[value ops] values]
                                  (map 
                                    (fn [op] 
                                      (let [next-value (op value 2)]
                                        [next-value (operations-set next-value op)])) ops)))))]    

    (loop [values {start (operations-set start :none)} 
           saw #{start}
           length 1]
      (if (contains? saw end)
        length
        (let [new-values (next-values values)]
          (recur new-values 
                 (into saw (vals new-values)) 
                 (inc length))
          )))))


(defn- run-all-tests []
  (is (= 1 (shortest-path 1 1))) ; 1
  (is (= 3 (shortest-path 3 12))) ; 3 6 12
  (is (= 3 (shortest-path 12 3))) ; 12 6 3
  (is (= 3 (shortest-path 5 9))) ; 5 7 9
  (is (= 9 (shortest-path 9 2))) ; 9 18 20 10 12 6 8 4 2
  (is (= 5 (shortest-path 9 12))) ; 9 11 22 24 12
  )

(run-all-tests)
