(in-ns 'user)

; recursive implementation
(defn fib [n]
  (case n
    1 0
    2 1
    (+ (fib (dec n)) (fib (-> n dec dec)))
    )
  )
