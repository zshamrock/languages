(ns exercises.roman-numerals-test
    (:require [clojure.test :refer :all]
        [exercises.roman-numerals :as roman]
        [exercises.irange :refer [irange]]
        ))

(deftest from-1-to-10-test
   (testing "all numbers from 1 to 10"
       (is (= ["I" "II" "III" "IV" "V" "VI" "VII" "VIII" "IX" "X"] (vec (map roman/to (irange 1 10)))))))

(deftest special-roman-literals-test
    (testing "all special roman literals V, X, L, C, D, M"
        (is (= ["V" "X" "L" "C" "D" "M"] (vec (map roman/to [5 10 50 100 500 1000]))))))

(deftest from-decimals-to-roman-test
    (testing "987 -> CMLXXXVII"
        (is (= (roman/to 987) "CMLXXXVII"))))

(deftest max-supported-decimal-test
    (testing "max supported decimal 3999"
        (is (= "MMMCMXCIX" (roman/to 3999)))))

(deftest from-roman-to-decimals-test
  (testing "MMMCMXCIX -> 3999"
    (is (= 3999 (roman/from "MMMCMXCIX")))))

(deftest to-roman-and-back-to-decimals-test
  (testing "to 2014 and back"
    (is (= 2014 (roman/from (roman/to 2014))))))

(deftest from-I-to-X-test
  (testing "from I to X"
    (is (= (irange 1 10) (vec (map roman/from ["I" "II" "III" "IV" "V" "VI" "VII" "VIII" "IX" "X"]))))))
