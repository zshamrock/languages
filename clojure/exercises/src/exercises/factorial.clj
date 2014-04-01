(ns exercises.factorial)

(defn irange 
    "Inclusive range, same as core range, but generates a vec from start till end inclusive"    
    [start end]
    (conj (vec (range start end)) end))

(defn fac 
    "Calculate factorial of N"
    [n]
    (cond
        (= 0 n) 1
        :else (* n (fac (dec n)))))

(defn factorial-from-0-till-n-with-merge 
    "Produce seq of factorial in the range from 1 till n using 'apply merge'"
    [n]
    (apply merge 
        (map #(assoc {} %1 (fac %1)) (irange 1 n))))

(defn factorial-from-0-till-n-with-zipmap 
    "Produce seq of factorial in the range from 1 till n using 'zipmap'"
    [n]
    (zipmap (irange 1 n) (map fac (irange 1 n))))