; 84. Transitive Closure
; https://www.4clojure.com/problem/84

(require '[clojure.test :refer [is]])

(defn transitive-closure [pairs-set]
  (let [pairs-map (into {} pairs-set)]
    (into #{} 
          (apply concat
                 (for [pair pairs-map]
                   (loop [a (key pair) b (val pair) visited #{b} result [[a b]]]
                     (let [c (get pairs-map b)]
                       (if (and c (not (contains? visited c)))
                         (recur a c (conj visited c) (conj result [a c]))
                         result))))))))

  
(defn- run-all-tests []
  (let [divides #{[8 4] [9 3] [4 2] [27 9]}]
    (is (= (transitive-closure divides) #{[4 2] [8 4] [8 2] [9 3] [27 9] [27 3]})))

  (let [more-legs
        #{["cat" "man"] ["man" "snake"] ["spider" "cat"]}]
    (is (= (transitive-closure more-legs)
           #{["cat" "man"] ["cat" "snake"] ["man" "snake"]
             ["spider" "cat"] ["spider" "man"] ["spider" "snake"]})))

  (let [progeny
        #{["father" "son"] ["uncle" "cousin"] ["son" "grandson"]}]
    (is (= (transitive-closure progeny)
           #{["father" "son"] ["father" "grandson"]
             ["uncle" "cousin"] ["son" "grandson"]}))))

(run-all-tests)
