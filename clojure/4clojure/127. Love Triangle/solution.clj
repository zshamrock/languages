; 127. Love Triangle 
; https://www.4clojure.com/problem/127

(in-ns 'user)

(require '[clojure.test :refer [is]])

(defn mineral-area [rock]
  (letfn [(binary-of [decimal]
            (loop [n decimal bits '()]
              (if (<= n 1) (conj bits n)
                (recur (quot n 2) (conj bits (rem n 2))))))

          (to-binary [rock]
            (let [binary-rock-not-aligned (map binary-of rock)
                  max-width (apply max (map count binary-rock-not-aligned))
                  ]
              (map #(concat (repeat (- max-width (count %)) 0) %) binary-rock-not-aligned)))]

    (to-binary rock)))

(defn- run-all-tests []
  (is (= 10 (mineral-area [15 15 15 15 15])))
  ; 1111      1111
  ; 1111      *111
  ; 1111  ->  **11
  ; 1111      ***1
  ; 1111      ****

  (is (= 15 (mineral-area [1 3 7 15 31])))
  ; 00001      0000*
  ; 00011      000**
  ; 00111  ->  00***
  ; 01111      0****
  ; 11111      *****

  (is (= 3 (mineral-area [3 3])))
  ; 11      *1
  ; 11  ->  **

  (is (= 4 (mineral-area [7 3])))
  ; 111      ***
  ; 011  ->  0*1

  (is (= 6 (mineral-area [17 22 6 14 22])))
  ; 10001      10001
  ; 10110      101*0
  ; 00110  ->  00**0
  ; 01110      0***0
  ; 10110      10110

  (is (= 9 (mineral-area [18 7 14 14 6 3])))
  ; 10010      10010
  ; 00111      001*0
  ; 01110      01**0
  ; 01110  ->  0***0
  ; 00110      00**0
  ; 00011      000*1

  (is (= nil (mineral-area [21 10 21 10])))
  ; 10101      10101
  ; 01010      01010
  ; 10101  ->  10101
  ; 01010      01010

  (is (= nil (mineral-area [0 31 0 31 0])))
  ; 00000      00000
  ; 11111      11111
  ; 00000  ->  00000
  ; 11111      11111
  ; 00000      00000
  )

(run-all-tests)
