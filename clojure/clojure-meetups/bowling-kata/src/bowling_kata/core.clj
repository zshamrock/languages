(ns bowling-kata.core
  (:gen-class))

(defn rolls [pins]
  (loop [p pins score 0]
    (if (seq p)
      (let [current-pins (first p) 
            next-pins (fnext p) 
            nnext-pins (fnext (next p))]
        (cond 
          (= 10 current-pins) (let [frame-score (if (and next-pins nnext-pins) (+ 10 next-pins nnext-pins) 0)]
                                (recur (next p) (+ score frame-score))) ; strike

          (= 10 (+ current-pins (or next-pins 0))) (let [frame-score (+ 10 nnext-pins)]
                                                     (recur (nnext p) (+ score frame-score))) ; spare

          :else (let [frame-score (if next-pins (+ current-pins next-pins) 0)] 
                  (recur (nnext p) (+ score frame-score)))))
      score)))


