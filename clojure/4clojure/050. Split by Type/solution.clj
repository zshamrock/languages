; 50. Split by Type
; http://www.4clojure.com/problem/50

(in-ns 'user)

(require '[clojure.test :refer [is]])

#(vals (group-by type %))

(run-all-tests)
