(ns exercises.factorial
    (:require [exercises.irange :as e]))

(defn fac 
    "Calculate factorial of N"
    [n]
    (cond
        (= 0 n) 1
        :else (* n (fac (dec n)))))

(defn factorial-from-0-till-n-with-merge 
    "Produce seq of factorial in the range from 1 till n using 'apply merge'"
    [n]
    (let [rng (e/irange 1 n)]
        (apply merge 
            (map #(assoc {} %1 (fac %1)) rng))))

(defn factorial-from-0-till-n-with-zipmap 
    "Produce seq of factorial in the range from 1 till n using 'zipmap'"
    [n]
    (let [rng (e/irange 1 n)]
        (zipmap rng (map fac rng))))