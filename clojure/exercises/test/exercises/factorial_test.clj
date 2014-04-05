(ns exercises.factorial-test
    (:require [clojure.test :refer :all]
        [exercises.factorial :refer :all]))

(def ^{:private true} factorial-of-10 3628800)

(deftest factorial-of-10-test
    (testing (str "factorial of 10 must be " factorial-of-10) 
        (is (= (fac 10) factorial-of-10))))

(deftest factorial-of-0-test
    (testing "factorial of 0 must be 1" 
        (is (= (fac 0) 1))))

(deftest factorial-of-1-test
    (testing "factorial of 1 must be 1" 
        (is (= (fac 1) 1))))

(deftest factorial-sets-must-be-the-same
    (testing "factorial sets produced by factorial-from-0-till-n-with-merge and factorial-from-0-till-n-with-zipmap must be the same"
        (is (= (factorial-from-0-till-n-with-merge 8) (factorial-from-0-till-n-with-zipmap 8)))))