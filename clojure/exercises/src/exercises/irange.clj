(ns exercises.irange)

(defn irange 
    "Inclusive range, same as core range, but generates a vec from start till end inclusive"    
    [start end]
    (conj (vec (range start end)) end))