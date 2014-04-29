; 82. Word Chains
; https://www.4clojure.com/problem/82

(require '[clojure.test :refer [is]])

(defn- chainable? [a b]  
  (let [[x y] (if (>= (count a) (count b)) [a b] [b a])] ; x is always the longest (or the same length) word, y the shortest
    (if (= (count x) (count y))
      (= 1 
         (loop [w1 x w2 y diff 0]
           (if (seq w1)          
             (recur (rest w1) (rest w2) (if (not= (first w1) (first w2)) (inc diff) diff))
             diff)))

      (loop [i 0]
        (let [one-char-deleted-x (str (subs x 0 i) (subs x (inc i)))]
          (if (= one-char-deleted-x y)
            true
            (if (not= i (dec (count x)))
              (recur (inc i))
              false)))))))

(defn- all-possible-chainable-words [chain]
  (into {} (for [word chain]
             (let [chainable (filter #(and (not= word %) (chainable? word %)) chain)]
               [word chainable]))))

(defn- chainable?-test []
  (is (= true (chainable? "hat" "hot")))
  (is (= true (chainable? "cot" "coat")))
  (is (= true (chainable? "coat" "cot")))
  (is (= false (chainable? "cot" "animal")))
  (is (= false (chainable? "cot" "pos"))))

(defn word-chain? [chain]
  (let [chainable-words (all-possible-chainable-words chain)]
    (letfn [
            (try-to-solve [chain-so-far used-words]
              (if (= used-words chain) 
                true
                (let [last-word (last chain-so-far)]
                  (loop [possible-next-words-in-chain (get chainable-words last-word)]
                    (if-let [next-word (first possible-next-words-in-chain)] 
                      (when-not (contains? used-words next-word)
                        (try-to-solve (conj chain-so-far next-word) (conj used-words next-word)))
                      (if (next possible-next-words-in-chain)
                        (recur (next possible-next-words-in-chain))
                        false))))))]
      (loop [words chain]
        (if (seq words)
          (if (try-to-solve [(first words)] #{(first words)})
            true
            (recur (next words)))
          false)))))
        

(defn- run-all-tests []
  (is (= true (all-possible-chainable-words #{"hat" "coat" "dog" "cat" "oat" "cot" "hot" "hog"})))
  (is (= false (word-chain? #{"cot" "hot" "bat" "fat"})))
  (is (= false (word-chain? #{"to" "top" "stop" "tops" "toss"})))
  (is (= true (word-chain? #{"spout" "do" "pot" "pout" "spot" "dot"})))
  (is (= true (word-chain? #{"share" "hares" "shares" "hare" "are"})))
  (is (= false (word-chain? #{"share" "hares" "hare" "are"}))))

(run-all-tests)
