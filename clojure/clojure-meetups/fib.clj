(in-ns 'user)

; recursive implementation
(defn fib [n]
  (cond    
    (= n 1) 0
    (= n 2) 1
    :default 
    (+ (fib (dec n)) (fib (-> n dec dec)))
    )
  )
