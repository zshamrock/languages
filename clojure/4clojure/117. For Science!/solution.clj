; 117. For Science!
; https://www.4clojure.com/problem/117

(require '[clojure.test :refer [is]])

(defn- normalize-maze 
  "Transform/normalize maze from vector of strings, into vector of vectors. Elements of the inner vector are one string char (\"M\", \"C\", \" \", \"#\")."
  [maze]
  (mapv #(mapv str (vec %)) maze)
  )

(is (= [3 1] (find-mouse (normalize-maze 
                 ["#######"
                  "#     #"
                  "#  #  #"
                  "#M # C#"
                  "#######"]))))

(is (= [3 5] (find-cheese (normalize-maze 
                 ["#######"
                  "#     #"
                  "#  #  #"
                  "#M # C#"
                  "#######"]))))

(defn- find-what 
  "Find coordinates [x y] of what, mouse, cheese, whatever."
  [maze what]
  (let [x (.indexOf (map #(some #{what} %) maze) what)
        y (.indexOf (nth maze x) what)]
    [x y]))

(defn- find-mouse
  "Find mouse [x y] coordinates."
  [maze]
  (find-what maze "M")
  )

(defn- find-cheese
  "Find cheese [x y] coordinates."
  [maze]
  (find-what maze "C")
  )

(defn- reachable-cells [maze [x y]]
  (set (for [[i j] [[1 0] [-1 0] [0 1] [0 -1]] :let [v (get-in maze [(+ x i) (+ y j)] "#")] :when (or (= " " v) (= "C" v))]
    [(+ x i) (+ y j)]
    )))

(is (= #{[1 2] [2 1]} (reachable-cells (normalize-maze
                 ["#######"
                  "#     #"
                  "#  #  #"
                  "#M # C#"
                  "#######"]) [1 1])))

(is (= #{} (reachable-cells (normalize-maze 
                 ["###M###"]) [0 4])))

(defn cheesy-endpoint-reachable? [maze]
  (let [m (normalize-maze maze)
        mouse (find-mouse m)
        cheese (find-cheese m)]
    (loop [cells #{mouse} seen #{mouse}]
      (if-not (empty? cells)
        (let [reachable (mapcat (partial reachable-cells m) cells)]
          (if (some #{cheese} reachable)
            true
            (recur (apply disj (set reachable) seen) (into seen reachable))
            )
          )
        false))))

(defn- run-all-tests []
  (is (= true  (cheesy-endpoint-reachable? ["M   C"])))
 
  (is (= false (cheesy-endpoint-reachable? ["M # C"])))

  (is (= true  (cheesy-endpoint-reachable? 
                 ["#######"
                  "#     #"
                  "#  #  #"
                  "#M # C#"
                  "#######"])))

  (is (= false (cheesy-endpoint-reachable? 
                 ["########"
                  "#M  #  #"
                  "#   #  #"
                  "# # #  #"
                  "#   #  #"
                  "#  #   #"
                  "#  # # #"
                  "#  #   #"
                  "#  #  C#"
                  "########"])))

  (is (= false (cheesy-endpoint-reachable? 
                 ["M     "
                  "      "
                  "      "
                  "      "
                  "    ##"
                  "    #C"])))

  (is (= true  (cheesy-endpoint-reachable? 
                 ["C######"
                  " #     "
                  " #   # "
                  " #   #M"
                  "     # "])))

  (is (= true  (cheesy-endpoint-reachable? 
                 ["C# # # #"
                  "        "
                  "# # # # "
                  "        "
                  " # # # #"
                  "        "
                  "# # # #M"])))
  )

(run-all-tests)
