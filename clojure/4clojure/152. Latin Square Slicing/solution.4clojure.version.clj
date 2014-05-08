(require '[clojure.test :refer [is]])

(defn latin-squares [v]
  (letfn [
          (latin-square? [square] ; square is a seq where each n elements (of total n*n) represent a row
            (let [n (-> square count Math/sqrt int)
                  distinct-elements (distinct square)]
              (if (= n (count distinct-elements)) ; satisfies one of the definition of latin square, so could be the one
                (let [rows (partition n square)
                      cols (apply map list rows)] 
                  (letfn [
                          (unique-all? [rows-or-cols]
                            (= (* n n) (reduce + (map (comp count distinct) rows-or-cols))))]
                    (and 
                      (unique-all? rows)
                      (unique-all? cols))))
                false)))

          (shift-row 
            [row]
            (when (= 'X (last row)) ; so there is a space to shift
              (let [n (count row)
                    spaces-in-front (count (take-while #{'X} row))
                    elements (take-while #(not= 'X %) (drop spaces-in-front row))]
                (when (seq elements)
                  (concat (repeat (inc spaces-in-front) 'X) elements (repeat (- n spaces-in-front (count elements) 1) 'X))))))

          (reset-row
            [row]
            (let [symbols (filter #(not= 'X %) row)
                  n (count row)]
              (concat symbols (repeat (- n (count symbols)) 'X))))

          (normalize 
            [v]
            (let [max-length (apply max (map count v))
                  rows (count v)                
                  ]
              (for [i (range rows)]
                (let [row (nth v i)]
                  (concat row (repeat (- max-length (count row)) 'X))))))

          (all-sub-squares 
            [v dimension]
            (let [width (count (first v)) 
                  height (count v)]
              (for [x (range (inc (- height dimension))) ; range end is exclusive, but we need the end to be inclusive, so there is inc for it
                    y (range (inc (- width dimension)))]
                (loop [i 0 square []]
                  (if-not (= i dimension)
                    (let [row (take dimension (drop y (nth v (+ i x))))] 
                      (recur (inc i) (concat square row)))
                    square)))))

          (next-shifted-square 
            [square]
            (loop [next-sq [] sq square]
              (when (seq sq)
                (if-let [shifted-row (shift-row (first sq))]
                  (vec (concat next-sq (vector (vec shifted-row)) (map vec (rest sq))))
                  (recur (conj next-sq (vec (reset-row (first sq)))) (rest sq))))))]

    (let [vv (normalize v)
          min-dimension (min (count vv) (count (first vv)))]
      (loop [next-vv vv seen-squares #{}]
        (if (seq next-vv)
          (let [squares
                (for [dimension (range 2 (inc min-dimension))]
                  (let [found-latin-squares (filter #(and (not (some #{'X} %)) (latin-square? %)) (all-sub-squares next-vv dimension)); valid-sub-squares are actual sub squares without the holes, i.e. 'X symbol 
                        ]
                    found-latin-squares))]
            (recur (next-shifted-square next-vv) (into seen-squares (apply concat (filter seq squares))))) ; filter empty seq
          (let [found-latin-squares-by-size (group-by #(-> % count Math/sqrt int) seen-squares)]
            (into {} (map (fn [[k v]] (vector k (count v))) found-latin-squares-by-size))))))))

(defn- run-all-tests []
  (is (= (latin-squares '[[A B C D]
                          [A C D B]
                          [B A D C]
                          [D C A B]])
      {}))

  (is (= (latin-squares '[[A B C D E F]
                          [B C D E F A]
                          [C D E F A B]
                          [D E F A B C]
                          [E F A B C D]
                          [F A B C D E]])
      {6 1}))

  (is (= (latin-squares '[[A B C D]
                          [B A D C]
                          [D C B A]
                          [C D A B]])
      {4 1, 2 4}))

  (is (= (latin-squares '[[B D A C B]
                          [D A B C A]
                          [A B C A B]
                          [B C A B C]
                          [A D B C A]])
      {3 3}))

  (is (= (latin-squares [  [2 4 6 3]
                          [3 4 6 2]
                          [6 2 4]  ])
      {}))

  (is (= (latin-squares [[1]
                      [1 2 1 2]
                      [2 1 2 1]
                      [1 2 1 2]
                      []       ])
      {2 2}))

  (is (= (latin-squares [[3 1 2]
           [1 2 3 1 3 4]
           [2 3 1 3]    ])
      {3 1, 2 2}))

  (is (= (latin-squares [[8 6 7 3 2 5 1 4]
                          [6 8 3 7]
                          [7 3 8 6]
                          [3 7 6 8 1 4 5 2]
                          [1 8 5 2 4]
                          [8 1 2 4 5]])
      {4 1, 3 1, 2 7}))
)

(time (run-all-tests))
