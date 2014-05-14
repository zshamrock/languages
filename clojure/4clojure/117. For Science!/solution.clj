; 117. For Science!
; https://www.4clojure.com/problem/117

(require '[clojure.test :refer [is]])

(defn cheesy-endpoint-reachable? [maze]
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
