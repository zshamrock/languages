(ns exercises.roman-numerals-test
    (:require [clojure.test :refer :all]
        [exercises.roman-numerals :as roman]
        [exercises.irange :refer [irange]]
        ))

(deftest from-1-to-10
 (testing "all numbers from 1 to 10"
   (is (= ["I" "II" "III" "IV" "V" "VI" "VII" "VIII" "IX" "X"] (vec (map roman/to (irange 1 10)))))))

(deftest special-roman-literals
    (testing "all special roman literals V, X, L, C, D"
        (is (= ["V" "X" "L" "C" "D"] (vec (map roman/to [5 10 50 100 500]))))))

(deftest from-decimals-to-roman-test
    (testing "987 -> CMLXXXVII"
        (is (= (roman/to 987) "CMLXXXVII"))))


