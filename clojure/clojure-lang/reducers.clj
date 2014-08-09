(in-ns 'user)

(require '[clojure.core.reducers :as r])

(let [input (take 10000000 (iterate inc 1))]
  (comment (time (reduce + (r/filter even? (r/map #(* 2 %) input)))))
  (time (reduce + (filter even? (map #(* 2 %) input))))
  )

; reducers output "Elapsed time: 127570.001846 msecs"
; - CPU was busy
; - result was nil

; core map/filter output: "Elapsed time: 48215.499799 msecs"
; - result was 100000010000000
