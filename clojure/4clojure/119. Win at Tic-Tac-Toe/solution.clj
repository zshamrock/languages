; 119. Win at Tic-Tac-Toe
; https://www.4clojure.com/problem/119

(require '[clojure.test :refer [is]])

(defn- on-left-diagonal? [x y]
  (= x y))

(defn- on-right-diagonal? [x y]
  (= 2 (+ x y)))

(is (= true (on-left-diagonal? 1 1)))
(is (= true (on-right-diagonal? 0 2)))

(defn- win-move? [player [x y] board]
  (let [row (assoc (nth board x) y player)
        col (assoc (mapv #(get-in board [% y]) (range 3)) x player)
        left-diagonal (assoc (mapv #(get-in board [% %]) (range 3)) x player)
        right-diagonal (assoc (mapv #(get-in board [% (- 2 %)]) (range 3)) x player)
        win-pattern (repeat 3 player)]
    (if (= :e (get-in board [x y]))
      (or (= row win-pattern) 
          (= col win-pattern)
          (and (on-left-diagonal? x y) (= left-diagonal win-pattern))
          (and (on-right-diagonal? x y) (= right-diagonal win-pattern)))
      false)))

(is (= true (win-move? :x [0 1] [[:o :e :e] 
                                 [:o :x :o] 
                                 [:x :x :e]])))

(defn win-coordinates [player board]
  (set 
    (filter #(win-move? player % board) 
            (for [x (range 3) y (range 3)] [x y]))))

(defn- run-all-tests [] 

  (is (= (win-coordinates 
           :x [[:o :e :e] 
               [:o :x :o] 
               [:x :x :e]])
         #{[2 2] [0 1] [0 2]}))

  (is (= (win-coordinates 
           :x [[:x :o :o] 
               [:x :x :e] 
               [:e :o :e]])
         #{[2 2] [1 2] [2 0]}))

  (is (= (win-coordinates 
           :x [[:x :e :x] 
               [:o :x :o] 
               [:e :o :e]])
         #{[2 2] [0 1] [2 0]}))

  (is (= (win-coordinates 
           :x [[:x :x :o] 
               [:e :e :e] 
               [:e :e :e]])
         #{}))

  (is (= (win-coordinates 
           :o [[:x :x :o] 
               [:o :e :o] 
               [:x :e :e]])
         #{[2 2] [1 1]})))

(run-all-tests)
