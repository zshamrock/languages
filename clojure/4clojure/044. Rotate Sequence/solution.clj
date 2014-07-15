; 44. Rotate Sequence
; http://www.4clojure.com/problem/44

(in-ns 'user)

(require '[clojure.test :refer [is]])

(defn rotate-seq [n sq]
  (let [m (rem n (count sq))]
    (if (pos? m)
      (concat (drop m sq) (take m sq))
      (concat (take-last (Math/abs m) sq) (take (- (count sq) (Math/abs m)) sq))
      )))

(defn- run-all-tests [] 
  (is (=  (rotate-seq 2  [1 2 3 4 5]) '(3 4 5 1 2)))
  (is (=  (rotate-seq -2  [1 2 3 4 5]) '(4 5 1 2 3)))
  (is (=  (rotate-seq 6  [1 2 3 4 5]) '(2 3 4 5 1)))
  (is (=  (rotate-seq 1 '(:a :b :c)) '(:b :c :a)))
  (is (=  (rotate-seq -4 '(:a :b :c)) '(:c :a :b))))

(run-all-tests)
