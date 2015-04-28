; 44. Flipping out
; http://www.4clojure.com/problem/46

(in-ns 'user)

(require '[clojure.test :refer [is]])

(defn rotate-seq [n sq]
  (let [m (rem n (count sq))]
    (if (pos? m)
      (concat (drop m sq) (take m sq))
      (concat (take-last (Math/abs m) sq) (take (- (count sq) (Math/abs m)) sq))
      )))

(defn flip-out [f]
  (fn flip [& args] (apply f (reverse args))))

(defn- run-all-tests [] 
  (is (= 3 ((flip-out nth) 2 [1 2 3 4 5]))))

(run-all-tests)
