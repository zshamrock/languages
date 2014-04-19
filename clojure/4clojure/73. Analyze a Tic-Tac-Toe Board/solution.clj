; 73. Analyze a Tic-Tac-Toe Board
; https://www.4clojure.com/problem/73

(require '[clojure.test :refer [is]])

(defn all-rows []
  (partition 3 (for [x (range 0 3) y (range 0 3)] [x y])))

(defn all-cols []
  (partition 3 (for [x (range 0 3) y (range 0 3)] [y x])))

(defn diagonals []
  (partition 3 (concat (for [x (range 0 3)] [x x]) (for [x (range 0 3)] [x (- 2 x)]))))

(defn possible-win-positions []
  (concat (all-rows) (all-cols) (diagonals)))

(defn analyze [board]
  (let [winner (fn [lines]
                 (cond 
                   (some #{":x:x:x"} lines)
                   :x

                   (some #{":o:o:o"} lines)
                   :o

                   :else
                   nil))
        as-lines (fn [args]
                   (map (fn [args] (apply str args)) (apply (fn [x y z] (map list x y z)) args)))]

    (let [found-winner
          (loop [x 0]
            (let [row-and-col (as-lines (for [y (range 0 3)] [((board x) y) ((board y) x)])) 
                  w (winner row-and-col)]
              (cond 
                (not (nil? w))
                w

                :else 
                (if (not= x 2)
                  (recur (inc x))
                  nil))))]
      (if (not (nil? found-winner))
        found-winner
        ; else diagonals
        (winner (as-lines (for [x (range 0 3)] [((board x) x) ((board x) (- 2 x))])))))))

(defn- run-all-tests []
  (is (= nil (analyze [[:e :e :e]
                       [:e :e :e]
                       [:e :e :e]])))

  (is (= :x (analyze [[:x :e :o]
                      [:x :e :o]
                      [:x :e :o]])))

  (is (= :o (analyze [[:e :x :e]
                      [:o :o :o]
                      [:x :e :x]])))

  (is (= nil (analyze [[:x :e :o]
                       [:x :x :e]
                       [:o :x :o]])))

  (is (= :x (analyze [[:x :e :e]
                      [:o :x :e]
                      [:o :e :x]])))

  (is (= :o (analyze [[:x :e :o]
                      [:x :o :e]
                      [:o :e :x]])))

  (is (= nil (analyze [[:x :o :x]
                       [:x :o :x]
                       [:o :x :o]]))))

(run-all-tests)
