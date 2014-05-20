(ns bowling-kata.core
  (:gen-class))

(require '[clojure.test :refer [is]])

(defn rolls [pins]
  (loop [p pins score 0]
    (if (seq p)
      (let [current-pins (first p)
            next-pins (fnext p)
            nnext-pins (fnext (next p))]
        (cond
          ; strike
          (= 10 current-pins) (let [frame-score (if (and next-pins nnext-pins) (+ 10 next-pins nnext-pins) 0)] ; "if" for strike in the last frame
                                (recur (next p) (+ score frame-score)))

          ; spare
          (= 10 (+ current-pins (or next-pins 0))) (let [frame-score (+ 10 nnext-pins)] ; "or" to go safely to :else part in the last frame spare
                                                     (recur (nnext p) (+ score frame-score)))

          :else (let [frame-score (if next-pins (+ current-pins next-pins) 0)] ; "if" for spare in the last frame
                  (recur (nnext p) (+ score frame-score)))))
      score)))

(do
  (is (= 0 (rolls (repeat 20 0)))) ; no rolls
  (is (= 133 (rolls [1 4 4 5 6 4 5 5 10 0 1 7 3 6 4 10 2 8 6]))) ; sample game
  (is (= 160 (rolls [4 6 7 3 2 8 5 5 6 4 9 1 5 5 3 7 8 2 8 2 7]))) ; all spares
  (is (= 300 (rolls (repeat 12 10)))) ; perfect game
  )
