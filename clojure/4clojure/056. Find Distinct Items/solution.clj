; 56. Flipping out
; http://www.4clojure.com/problem/56

(in-ns 'user)

(require '[clojure.test :refer [is]])

(defn find-distinct [v]
  (reduce #(if (= (.indexOf %1 %2) -1) (conj %1 %2) %1) [] v))

(defn- run-all-tests [] 
  (is (=  (find-distinct  [1 2 1 3 1 2 4])  [1 2 3 4])))

(run-all-tests)
