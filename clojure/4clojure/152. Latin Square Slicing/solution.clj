; 152. Latin Square Slicing
; https://www.4clojure.com/problem/152

(require '[clojure.test :refer [is]])

(defn- latin-square? [square] ; square is a seq where each n elements (of total n*n) represent a row
  (let [n (int (Math/sqrt (count square)))
        cols (partition n square)
        rows (apply map list cols)]
    (letfn [
            (unique-all? [cols-or-rows]
              (= (* n n) (reduce + (map (comp count distinct) cols-or-rows))))]
      (and 
        (unique-all? rows)
        (unique-all? cols)))))

(defn- latin-square?-tests []
  (is (latin-square? '[A B C B C A C A B]))
  (is (not (latin-square? '[A B C B C A C A C])))
  (is (latin-square? [1 2 2 1]))
  (is (not (latin-square? [1 2 1 2]))))

(latin-square?-tests)

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
