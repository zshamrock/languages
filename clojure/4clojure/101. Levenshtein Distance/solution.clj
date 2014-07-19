; 101. Levenshtein Distance
; https://www.4clojure.com/problem/101

(in-ns 'user)

(require '[clojure.test :refer [is]])

(defn -levenshtein-distance [x y]
  (let [cx (count x) 
        cy (count y)
        distance (fn [x y]
                   (count (filter false? (map = x y))))
        try-all (fn [x y]
                  (let [cx (count x) 
                        cy (count y)
                        distances                         
                        (for [i (range (inc (- cy cx)))]
                          (distance x (take cx (drop i y))))
                        min-distance (apply min distances)]
                    (+ min-distance (- cy cx))))]
    (cond
      (= cx cy) (distance x y)
      (> cx cy) (try-all y x)
      :else (try-all x y)))
  )

(defn generate [alphabets size]
  (cond 
    (= (count alphabets) size) [alphabets]
    (= 1 size) (mapv str alphabets) 
    :else (vec (concat 
                 (map #(str (first alphabets) %) (generate (subs alphabets 1) (dec size))) 
                 (generate (subs alphabets 1) size)))
    )
  )

(is (= (mapv str "lisp") (generate "lisp" 1)))
(is (= ["li" "ls" "lp" "is" "ip" "sp"] (generate "lisp" 2)))
(is (= ["lis" "lip" "lsp" "isp"] (generate "lisp" 3)))
(is (= ["lisp"] (generate "lisp" 4)))

(def lev
  (memoize (fn [a b i j]
             (cond 
               (zero? (min i j)) 
               (max i j)
               
               :default
               (min (+ (lev a b (dec i) j) 1) 
                    (+ (lev a b i (dec j)) 1) 
                    (+ (lev a b (dec i) (dec j)) (if (= (get a (dec i)) (get b (dec j))) 0 1))
                    )
               )
            
            ))
  )

(is (= (lev "kitten" "sitting" (count "kitten") (count "sitting")) 3))

; final solution, actually because 4clojure doesn't accept (def) to declare memoize recursive function inside let (partial lev lev) trick is used
; another approach is to declare fn (f (fn [s a b) ...)), then define memoize (g (memoize f)) and apply g to f (g g a b), so actually s becomes g, which is memoized f :)
(defn levenshtein-distance [a b]
  (let [
        lev (memoize (fn [f a b]
                       (cond 
                         (zero? (min (count a) (count b))) 
                         (max (count a) (count b))

                         :default
                         (min (+ (f f (rest a) b) 1) 
                              (+ (f f a (rest b)) 1) 
                              (+ (f f (rest a) (rest b)) (if (= (first a) (first b)) 0 1))
                              )
                         )))


        ]
    ((partial lev lev) a b) 
    )
  )


(defn- run-all-tests []
  (is (= (levenshtein-distance "kitten" "sitting") 3))
  (is (= (levenshtein-distance "closure" "clojure") (levenshtein-distance "clojure" "closure") 1))
  (is (= (levenshtein-distance "xyx" "xyyyx") 2))
  (is (= (levenshtein-distance "" "123456") 6))
  (is (= (levenshtein-distance "Clojure" "Clojure") (levenshtein-distance "" "") (levenshtein-distance [] []) 0))
  (is (= (levenshtein-distance [1 2 3 4] [0 2 3 4 5]) 2))
  (is (= (levenshtein-distance '(:a :b :c :d) '(:a :d)) 2))
  (is (= (levenshtein-distance "ttttattttctg" "tcaaccctaccat") 10))
  (is (= (levenshtein-distance "gaattctaatctc" "caaacaaaaaattt") 9)))

(run-all-tests)
