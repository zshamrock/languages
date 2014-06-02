; 106. Number Maze
; https://www.4clojure.com/problem/106

(in-ns 'user)

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
          (next-values [values saw]
            (into {} 
                  (apply concat 
                         (for [[value [ops path]] values]
                           (map 
                             (fn [op] 
                               (let [next-value (op value 2)]
                                 (when (not (contains? saw next-value))
                                   [next-value [(operations-set next-value op) (conj path value)]]))) 
                             ops)))))]

    (loop [values {start [(operations-set start :none) []]} ; keep track of available operations as well the path
           saw #{start} ; remember what we saw so far, in order not to do the same computation over and over again
           length 1]
      (if (contains? saw end)
        (do
          (let [found-path (conj (get-in values [end 1]) end)]
            (println (str start "->" end ": " found-path))) ; path
          length) ; length
        (let [new-values (next-values values saw)]
          (recur new-values 
                 (into saw (keys new-values)) 
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
