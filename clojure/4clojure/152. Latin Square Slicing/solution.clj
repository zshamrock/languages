; 152. Latin Square Slicing
; https://www.4clojure.com/problem/152

(require '[clojure.test :refer [is]])

(defn- latin-square? [square] ; square is a seq where each n elements (of total n*n) represent a row
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

(defn- latin-square?-tests []
  (is (latin-square? '[A B C B C A C A B]))
  (is (not (latin-square? '[A B C B C A C A C])))
  (is (latin-square? [1 2 2 1]))
  (is (not (latin-square? [1 2 1 2])))
  (is (not (latin-square? [1 2 3 4]))))

(latin-square?-tests)

(defn- shift-row 
  "Shift row to the right, the holes are marked with 'X where elements can be placed."
  [row]
  (when (= 'X (last row)) ; so there is a space to shift
    (let [n (count row)
          spaces-in-front (count (take-while #{'X} row))
          elements (take-while #(not= 'X %) (drop spaces-in-front row))]
      (when (seq elements)
        (concat (repeat (inc spaces-in-front) 'X) elements (repeat (- n spaces-in-front (count elements) 1) 'X))))))

(defn- shift-row-test []
  (is (= '[X X X A B C X] (shift-row '[X X A B C X X])))
  (is (= '[X X X X A B C] (shift-row '[X X X A B C X])))
  (is (= nil (shift-row '[X X X A B C ])))
  (is (= '[X A B C] (shift-row '[A B C X])))
  (is (= nil (shift-row '[A B C])))
  (is (= nil (shift-row '[])))
  (is (= nil (shift-row '[X X X X])))
)

(shift-row-test)

(defn- normalize 
  "Normalize vector V means expand all rows smaller than maximal length filling the rest with 'X."
  [v]
  (let [max-length (apply max (map count v))
        rows (count v)                
        ]
    (for [i (range rows)]
      (let [row (nth v i)]
        (concat row (repeat (- max-length (count row)) 'X))))))

(= [[1 2 'X 'X] [1 2 3 4] ['X 'X 'X 'X] [1 'X 'X 'X]] (normalize [[1 2] [1 2 3 4] [] [1]]))

(defn- all-sub-squares 
  "Return all sub squares given dimension. v is a normalized version of the original V."
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

(defn- all-sub-squares-test []
  (is (= '((1 2 1 2) (2 X 2 3) (X X 3 4) (1 2 X X) (2 3 X X) (3 4 X X) (X X 1 X) (X X X X) (X X X X)) 
         (all-sub-squares '((1 2 X X) (1 2 3 4) (X X X X) (1 X X X)) 2)))

  (is (= '((1 2 X 1 2 3 X X X) (2 X X 2 3 4 X X X) (1 2 3 X X X 1 X X) (2 3 4 X X X X X X))
         (all-sub-squares '((1 2 X X) (1 2 3 4) (X X X X) (1 X X X)) 3)))

  (is (= '((1 2 X X 1 2 3 4 X X X X 1 X X X))
         (all-sub-squares '((1 2 X X) (1 2 3 4) (X X X X) (1 X X X)) 4)))
  
  (is (= '((1 2 A B) (2 3 B C) (3 4 C D) (A B W X) (B C X Y) (C D Y Z)) 
         (all-sub-squares '((1 2 3 4) (A B C D) (W X Y Z)) 2)))) 

(all-sub-squares-test)

(defn- next-shifted-square 
  "Returns the next shifted square or nil otherwise. square arg is an vec of vecs."
  [square]
  (let [size (count square)]
    (loop [i 0 shifted-square []]
      (let [row (nth square i)]
        (if-let [shifted-row (shift-row row)]
          (let [square-head (conj shifted-square (vec shifted-row))
                square-rest (nthrest square (inc i))]
            (if (seq square-rest)
              (into square-head square-rest)
              square-head))
          (when-not (= (inc i) size)
            (recur (inc i) (conj shifted-square (vec row)))))))))

(defn- next-shifted-square-test []
  (is (= '[[X A B] [X A B] [A B C]] (next-shifted-square '[[A B X] [X A B] [A B C]])))

  (is (= '[[A B C] [X X A] [A B C]] (next-shifted-square '[[A B C] [X A X] [A B C]])))
  
  (is (= '[[A B C] [X A B] [X A X]] (next-shifted-square '[[A B C] [X A B] [A X X]])))
  
  (is (= nil (next-shifted-square '[[A B C] [X A B] [A B C]])))

  (is (= nil (next-shifted-square '[[A B C] [X A B] [X X X]])))) 

(next-shifted-square-test)

(all-sub-squares (next-shifted-square (normalize [[1 1 1 1]
                                                  [1 2 1 2]
                                                  [2 1 2 1]
                                                  [1 2 1 2]
                                                  []       ])) 2)

(defn latin-squares [v]
  (let [vv (normalize v)
        min-dimension (min (count vv) (count (first vv)))]
    (loop [next-vv vv seen-squares #{}]
      (if (seq next-vv)
        (let [squares
              (for [dimension (range 2 (inc min-dimension))]
                (let [valid-sub-squares (filter #(not (some #{'X} %)) (all-sub-squares next-vv dimension)); valid-sub-squares are actual sub squares without the holes, i.e. 'X symbol 
                      found-latin-squares (filter latin-square? valid-sub-squares)]
                  found-latin-squares))]
          (recur (next-shifted-square next-vv) (into seen-squares (apply concat (filter seq squares))))) ; filter empty seq
        (let [found-latin-squares-by-size (group-by #(-> % count Math/sqrt int) seen-squares)]
          (println "seen-squares" seen-squares)
          (into {} (map (fn [[k v]] (vector k (count v))) found-latin-squares-by-size)))))))

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

(run-all-tests)
