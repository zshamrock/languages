(in-ns 'user)

; recursive implementation
(defn fib [n]
  (case n
    1 0
    2 1
    (+ (fib (dec n)) (fib (-> n dec dec)))
    )
  )

; recursive implementation doesn't work well on N > ? (but it already works slow on N = 35)
(time (fib 35))

; even the memoized version is not so efficient
(def fib (memoize (fn [n]
                    (case n
                      1 0
                      2 1
                      (+ (fib (dec n)) (fib (-> n dec dec)))
                      ))))

(time (fib 1000))

; taken from http://danmidwood.com/content/2013/02/24/exploring-clojure-memoization.html
(defmacro defn-memo  [name & body]
  `(def ~name  (memoize  (fn ~body))))

; usage of defn-memo
(defn-memo fib  [n]
  (if (>= 1 n)
    n
    (+ (fib (- n 1)) (fib (- n 2)))))

(time (fib 1000))
; but even with memoization (fib 1000) causes StackOverflowError

; accumulator technique
(defn fib-accu [n]
  (loop [x (long 0) y (long 1) N 1]
    (if (= N n) x
      (recur y (+ x y) (inc N)))  
    )
  )

(time (fib-accu 100))


(let  [fibos  (iterate  (fn  [[a0 a1]]  [a1  (+ a0 a1)])  [0 1])
       fibo  (fn  [n]  (->> n dec  (nth fibos) first))]
  (-> 
    100
    fibo
    println))


; explore lazy-cat
