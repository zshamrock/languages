; 150. Palindromic Numbers
; https://www.4clojure.com/problem/150

(in-ns 'user)

(require '[clojure.test :refer [is]])

(defn palindromic-numbers [len start-from]
  (letfn [(gen-palindrome [len start-from]
            (cond
              ; TODO: think about better strategy to generate palindromes
              )
            
            )]
    (gen-palindrome len start-from)
    )  
  )

(defn- run-all-tests []
  
  )

(run-all-tests)
