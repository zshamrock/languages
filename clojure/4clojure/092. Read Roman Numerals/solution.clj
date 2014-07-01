; 92. Read Roman numerals
; https://www.4clojure.com/problem/92

(in-ns 'user)

(require '[clojure.test :refer [is]])
  
(defn read-roman-numerals [roman]
  (let [romans-to-decimals (zipmap '[I V X L C D M] [1 5 10 50 100 500 1000])]
    (loop [number 0
           decimals (map romans-to-decimals (map #(-> % str symbol) roman))]
      (if (seq decimals)
        (let [x (first decimals)
              y (or (fnext decimals) 0)]
          (if (< x y) 
            (recur (+ number (- y x)) (nnext decimals))
            (recur (+ number x) (next decimals)))
          )
        number))))

(defn- run-all-tests []
  (is (= 14 (read-roman-numerals "XIV")))

  (is (= 827 (read-roman-numerals "DCCCXXVII")))

  (is (= 3999 (read-roman-numerals "MMMCMXCIX")))

  (is (= 48 (read-roman-numerals "XLVIII"))))

(run-all-tests)
