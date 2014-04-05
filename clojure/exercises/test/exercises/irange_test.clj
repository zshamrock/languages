(ns exercises.irange-test
    (:require [clojure.test :refer :all]
        [exercises.irange :refer :all]))

(deftest irange-from-1-till-5
    (testing "irange of 5 must be [1 2 3 4 5]"
        (is (= (irange 1 5) [1 2 3 4 5]))))

(deftest irange-of-1
    (testing "irange of 1 must be [1]"
        (is (= (irange 1 1) [1]))))
