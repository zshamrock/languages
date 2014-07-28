; 121. Universal Computation Engine
; https://www.4clojure.com/problem/121

(in-ns 'user)

(require '[clojure.test :refer [is]])

(defn computation-engine [ops]
  (fn [vars]    
    (let [operators '[+ - * /]
          actionable-operators {'+ + '- - '* * '/ /}]
      (letfn [
              (compute [ops]
                (let [next-op (if (seq? ops) (first ops) ops)]
                  (cond
                    (seq? next-op)
                    (cons (compute next-op) (compute (next ops)))

                    (nil? next-op)
                    []

                    (some #{next-op} operators)
                    (apply (actionable-operators next-op) (compute (next ops)))

                    (number? next-op)
                    (cons next-op (compute (next ops)))

                    :default
                    (cons (vars next-op) (compute (next ops)))
                    )) 
                )]
        (compute ops)))))

(defn- run-all-tests []
  (is (= 2 ((computation-engine '(/ a b))
            '{b 8 a 16})))  

  (is (= 8 ((computation-engine '(+ a b 2))
            '{a 2 b 4})))

  (is (= [6 0 -4]
         (map (computation-engine '(*  (+ 2 a)
                                      (- 10 b)))
              '[{a 1 b 8}
                {b 5 a -2}
                {a 2 b 11}])))

  (is (= 1 ((computation-engine '(/  (+ x 2)
                                    (* 3  (+ y 1))))
            '{x 4 y 1})))

  )

(run-all-tests)
