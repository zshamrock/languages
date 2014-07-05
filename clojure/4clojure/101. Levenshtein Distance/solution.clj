; 101. Levenshtein Distance
; https://www.4clojure.com/problem/101

(in-ns 'user)

(require '[clojure.test :refer [is]])

(defn levenshtein-distance [x y]
  (let [cx (count x) 
        cy (count y)]
    (cond
      (= cx cy)
      (count (filter false? (map = x y)))))
  )

(defn- run-all-tests []
  (is (= (levenshtein-distance "kitten" "sitting") 3))
  (is (= (levenshtein-distance "closure" "clojure") (levenshtein-distance "clojure" "closure") 1))
  (is (= (levenshtein-distance "xyx" "xyyyx") 2))
  (is (= (levenshtein-distance "" "123456") 6))
  (is (= (levenshtein-distance "Clojure" "Clojure") (levenshtein-distance "" "") (levenshtein-distance [] []) 0))
  (is (= (levenshtein-distance [1 2 3 4] [0 2 3 4 5]) 2))
  (is (= (levenshtein-distance '(:a :b :c :d) '(:a :d)) 2))
  (is (= (levenshtein-distance "ttttattttctg" "tcaaccctaccat") 10))
  (is (= (levenshtein-distance "gaattctaatctc" "caaacaaaaaattt") 9)))

(run-all-tests)
