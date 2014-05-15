; 117. For Science!
; https://www.4clojure.com/problem/117

(require '[clojure.test :refer [is]])

(defn- normalize-maze 
  "Transform/normalize maze from vector of strings, into vector of vectors. Elements of the inner vector are one string char (\"M\", \"C\", \" \", \"#\")."
  [maze]
  (mapv #(mapv str (vec %)) maze)
  )

(defn- find-what 
  "Find coordinates [x y] of what, mouse, cheese, whatever."
  [maze what]

  (let [what (
               loop [m maze i 0]
               (if (some #{what} (first maze))
                 [i (.indexOf (first maze) what)]
                 (recur (next maze) (inc i))
                 ))]
    what))

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

(defn cheesy-endpoint-reachable? [maze]
  (let [m (normalize-maze maze)
        
        ]
    (loop [cells [] seen #{}])
    )
  false
  )

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
