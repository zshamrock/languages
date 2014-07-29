; 150. Palindromic Numbers
; https://www.4clojure.com/problem/150

(in-ns 'user)

(require '[clojure.test :refer [is]])

(defn palindromic-numbers [len start-from]
  (letfn [(gen-palindrome [len start-from]
            (cond
              (= len 1)
              start-from

              (= len 2)
              (if (>= (mod start-from 10) (quot start-from 10))
                (+ (* (inc (quot start-from 10)) 10) (inc (quot start-from 10)))
                (+ (* (quot start-from 10) 10) (quot start-from 10))
                )
              )

              (= len 3)
              ()
            
            )]
    (gen-palindrome len start-from)
    )  
  )

(defn- run-all-tests []
  
  )

(run-all-tests)
