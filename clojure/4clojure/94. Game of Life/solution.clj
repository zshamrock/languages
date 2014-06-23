; 94. Game of Life
; https://www.4clojure.com/problem/94

(in-ns 'user)

(require '[clojure.test :refer [is]])

(defn next-generation [board]
  board
  )

(defn- run-all-tests []
  (is (= (next-generation 
           ["      "  
            " ##   "
            " ##   "
            "   ## "
            "   ## "
            "      "])
         ["      "  
          " ##   "
          " #    "
          "    # "
          "   ## "
          "      "]))

  (is (= (next-generation 
           ["     "
            "     "
            " ### "
            "     "
            "     "])
         ["     "
          "  #  "
          "  #  "
          "  #  "
          "     "]))

  (is (= (next-generation 
           ["      "
            "      "
            "  ### "
            " ###  "
            "      "
            "      "])
         ["      "
          "   #  "
          " #  # "
          " #  # "
          "  #   "
          "      "]))
  )

(run-all-tests)
