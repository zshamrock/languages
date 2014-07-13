(in-ns 'user)

(let [
      jumps (map #(Long/parseLong %) (clojure.string/split "2 3 4" #" "))      
      find-solution  (fn [jumps]
                       (let [longest-jump (apply max jumps)]
                         (loop [solution longest-jump]
                           (if (every? zero? (map #(rem solution %) jumps)) solution
                             (recur (+ solution longest-jump))))))]
  (println (find-solution jumps)))


(defn prime-factorization [n]
  (let [primes (loop [number n primes []]
                 (let [next-prime (first (filter #(zero? (rem number %)) [2 3 5 7 11]))]
                   (if next-prime
                     (recur (/ number next-prime) (conj primes next-prime))
                     (if (= number 1) primes 
                       (conj primes number)))))]
    primes))

(defn count-occurences [coll]
  (reduce #(assoc %1 %2 (inc (%1 %2 0))) {} coll)
  )


(let [all-factorization (for [n [597 322 187 734 498 215 176 451 114 204]]
                          (count-occurences (prime-factorization n)))
      lcm (apply merge-with max all-factorization)]
  (println lcm)
  (long (reduce #(* %1 (long (Math/pow (key %2) (val %2)))) 1 lcm)))

; different ways to do count of elements in the collection
(let [coll [2 2 3 4 2 5 5]] 
  (= 
    (into {} (map #(vector (key %) (count (val %))) (group-by identity coll)))
    (reduce #(assoc %1 %2  (inc  (%1 %2 0)))  {} coll)
    (apply merge-with + (map #(assoc {} % 1) coll))
    {2 3, 3 1, 4 1, 5 2}
    ))

(merge-with + {:a 12} {:b 4} {:a 3 :b 7})

; solution formatted for hackerrank
(letfn [(count-occurences [coll]
          (reduce #(assoc %1 %2 (inc (%1 %2 0))) {} coll))

        (prime-factorization [n]
          (let [primes (loop [number n primes []]
                         (let [next-prime (first (filter #(zero? (rem number %)) [2 3 5 7 11]))]
                           (if next-prime
                             (recur (/ number next-prime) (conj primes next-prime))
                             (if (= number 1) primes 
                               (conj primes number)))))]
            (sort primes)))]
  (let [_ (read-line)
        jumps (map #(Long/parseLong %) (clojure.string/split  (read-line) #" "))
        all-factorization (for [n jumps]
                            (count-occurences (prime-factorization n)))
        lcm (apply merge-with max all-factorization)]
    (println (long (reduce #(* %1 (long (Math/pow (key %2) (val %2)))) 1 lcm)))))
