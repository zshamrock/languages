; 58 Split by Type
; http://www.4clojure.com/problem/58

(in-ns 'user)

(require '[clojure.test :refer [is]])

(defn func-comp [& args]
  (fn [& params] 
    (loop [ff (reverse args)
           params params
           apply? true]
      (if (seq ff)
        (let [f (first ff)] 
          (recur (next ff) 
                 (if apply? 
                   (apply f params)
                   (f params)) 
                 false))
        params))))


(defn run-all-tests [] 
  (is (=  [3 2 1]  ((func-comp rest reverse)  [1 2 3 4])))
  (is (= 5  ((func-comp  (partial + 3) second)  [1 2 3 4])))
  (is (= true  ((func-comp zero? #(mod % 8) +) 3 5 7 9)))
  (is (= "HELLO"  ((func-comp #(.toUpperCase %) #(apply str %) take) 5 "hello world"))))


(run-all-tests)
