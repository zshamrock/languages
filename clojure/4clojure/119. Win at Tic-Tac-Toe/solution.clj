; 119. Win at Tic-Tac-Toe
; https://www.4clojure.com/problem/119

(require '[clojure.test :refer [is]])

(defn- win-move? [player [x y] board]
  (let [row (assoc (nth board x) y player)
        col (assoc (mapv #(get-in board [% x]) (range 3)) x player)
        winner (repeat 3 player)]
    (or (= row winner) (= col winner))))

(win-move? :x [0 0] [[:e :x :x] [:o :o :e] [:x :x :o]])

(defn win-coordinates [player board]
  (mapcat (fn [x y]) (range 3))
  #{}
  )

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
