; 152. Latin Square Slicing
; https://www.4clojure.com/problem/152

(require '[clojure.test :refer [is]])

(defn- latin-square? [square] ; square is a seq where each n elements (of total n*n) represent a row
  (let [n (-> square count Math/sqrt int)
        rows (partition n square)
        cols (apply map list rows)]
    (letfn [
            (unique-all? [rows-or-cols]
              (= (* n n) (reduce + (map (comp count distinct) rows-or-cols))))]
      (and 
        (unique-all? rows)
        (unique-all? cols)))))

(defn- latin-square?-tests []
  (is (latin-square? '[A B C B C A C A B]))
  (is (not (latin-square? '[A B C B C A C A C])))
  (is (latin-square? [1 2 2 1]))
  (is (not (latin-square? [1 2 1 2]))))

(latin-square?-tests)

(defn- shift-row 
  "Shift row to the right, the holes are marked with 'X where elements can be placed."
  [row]
  (when (= 'X (last row)) ; so there is a space to shift
    (let [n (count row)
          spaces-in-front (count (take-while #{'X} row))
          elements (take-while #(not= 'X %) (drop spaces-in-front row))]
      (concat (repeat (inc spaces-in-front) 'X) elements (repeat (- n spaces-in-front (count elements) 1) 'X)))))

(defn- shift-row-test []
  (is (= '[X X X A B C X] (shift-row '[X X A B C X X])))
  (is (= '[X X X X A B C] (shift-row '[X X X A B C X])))
  (is (= nil (shift-row '[X X X A B C ])))
  (is (= '[X A B C] (shift-row '[A B C X])))
  (is (= nil (shift-row '[A B C])))
  (is (= nil (shift-row '[])))
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
    (for [x (range (inc (- width dimension))) ; range end is exclusive, but we need the end to be inclusive, so there is inc for it
          y (range (inc (- height dimension)))]
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
         (all-sub-squares '((1 2 X X) (1 2 3 4) (X X X X) (1 X X X)) 4)))) 

(all-sub-squares-test)

(defn- next-shifted-square 
  "Returns the next shifted square or nil otherwise. square arg is an seq of seqs."
  [square]
  (let [n (apply max (map count square))])
  nil
  )

(defn latin-squares [v]
  {})

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
