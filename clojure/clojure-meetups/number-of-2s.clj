(in-ns 'user)

(require '[clojure.test :refer [is]])

; counts the number of '2' digits between 0 and n
(defn number-of-2s [number]
  (let [all-tenth (loop [n (map #(-> % str Integer/parseInt) (str number)) cnt 0]
                    (if (seq n) 
                      (let [head (first n) 
                            pos (count n)
                            ]
                        (cond
                          (> head 2) 
                          (recur (rest n) (+ cnt (->> pos dec (Math/pow 10))))

                          (= head 2)
                          (recur (rest n) (+ cnt 1 (Integer/parseInt (apply str (or (next n) [0])))))

                          :default 
                          (recur (rest n) cnt)
                          )
                        )
                      cnt
                      )
                    )]
    (int (+ all-tenth (quot number 10)))
    )
  )

(defn- run-all-tests [] 
  (is (= (number-of-2s 25) 9))
  (is (= (number-of-2s 1) 0))
  (is (= (number-of-2s 2) 1))
  (is (= (number-of-2s 0) 0))
  (is (= (number-of-2s 6) 1))
  (is (= (number-of-2s 10) 1))
  (is (= (number-of-2s 11) 1))
  (is (= (number-of-2s 12) 2))
  (is (= (number-of-2s 125) 19)))

(run-all-tests)
