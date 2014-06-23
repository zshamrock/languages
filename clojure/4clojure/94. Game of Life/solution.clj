; 94. Game of Life
; https://www.4clojure.com/problem/94

(in-ns 'user)

(require '[clojure.test :refer [is]])

(defn next-generation [board]
  (let [neighbours-positions
        (for [x (range -1 2) y (range -1 2) :when (or (not= x 0) (not= y 0))] [x y])

        width (count (first board))
        height (count board)

        generation
        (for [x (range width) 
              y (range height)
              :let [cell (get-in board [x y])
                    dead-cell? (= cell \space)
                    neighbours (map #(get-in board [(+ x (first %)) (+ y (second %))] \space) neighbours-positions)
                    live-neighbours-count (count (filter #(= \# %) neighbours))]]
          (if dead-cell? ; dead cell
            (cond 
              (= 3 live-neighbours-count) \# ; surrounded by exactly 3 live cells, so reproduction
              :else \space) ; cell stays dead :(
            (cond 
              (< live-neighbours-count 2) \space ; cell dies, under-population
              (> live-neighbours-count 3) \space ; cell dies, overcrowding
              :else \# ; survive till the next generation
              )))]
    (mapv #(apply str %) (partition width generation))
    )
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
