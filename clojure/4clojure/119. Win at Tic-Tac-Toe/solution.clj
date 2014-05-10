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
  (let [row (nth board x)
        col (mapv #(get-in board [% y]) (range 3))
        left-diagonal (mapv #(get-in board [% %]) (range 3))
        right-diagonal (mapv #(get-in board [% (- 2 %)]) (range 3))
        win-pattern (repeat 3 player)]
    (if (= :e (get-in board [x y]))
      (or (= (assoc row y player) win-pattern) 
          (= (assoc col x player) win-pattern)
          (and (on-left-diagonal? x y) (= (assoc left-diagonal x player) win-pattern))
          (and (on-right-diagonal? x y) (= (assoc right-diagonal x player) win-pattern)))
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
