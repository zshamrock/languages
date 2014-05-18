; 178. Best Hand
; https://www.4clojure.com/problem/178

(require '[clojure.test :refer [is]])

(def ^{:private true} 
  ranks-to-num (merge (zipmap (map #(Character/forDigit % 10) (range 2 10)) (range 2 10)) 
                      (zipmap [\T \J \Q \K \A] (iterate inc 10)))
  )

(defn- suites [cards]
  (mapv #(first %) cards)
  )

(defn- ranks [cards]
  (sort (mapv #(dec (ranks-to-num (second %))) cards)) ; do dec so start with 1 (and for ace start with 0)
  )

(defn- in-sequence? [ranks]
  (let [one? (partial = 1)
          sorted-ranks (sort ranks)]
    (every? one? (map - (next sorted-ranks) (butlast sorted-ranks)))))

(do 
  (is (true? (in-sequence? [1 2 3 4 5])))
  (is (true? (in-sequence? [0 1 2 3 4 5])))
  (is (true? (in-sequence? [5 4])))
  (is (false? (in-sequence? [2 3 4 8 9]))))

(defn- straight-flush? [cards]
  (let [s (suites cards)
        r (ranks cards)]
    (and (every? #{(first s)} s) (in-sequence? r))))

(defn- four-of-a-kind? [cards]
  (let [r (ranks cards)]
   (some #{4} (vals (frequencies r)))))

(defn best-hand [cards]
  (cond 
    (straight-flush? cards) :straight-flush
    (four-of-a-kind? cards) :four-of-a-kind
    :else :high-card))

(defn- run-all-tests []
  (is (= :high-card (best-hand ["HA" "D2" "H3" "C9" "DJ"])))
  (is (= :pair (best-hand ["HA" "HQ" "SJ" "DA" "HT"])))
  (is (= :two-pair (best-hand ["HA" "DA" "HQ" "SQ" "HT"])))
  (is (= :three-of-a-kind (best-hand ["HA" "DA" "CA" "HJ" "HT"])))
  (is (= :straight (best-hand ["HA" "DK" "HQ" "HJ" "HT"])))
  (is (= :straight (best-hand ["HA" "H2" "S3" "D4" "C5"])))
  (is (= :flush (best-hand ["HA" "HK" "H2" "H4" "HT"])))
  (is (= :full-house (best-hand ["HA" "DA" "CA" "HJ" "DJ"])))
  (is (= :four-of-a-kind (best-hand ["HA" "DA" "CA" "SA" "DJ"])))
  (is (= :straight-flush (best-hand ["HA" "HK" "HQ" "HJ" "HT"])))
)

(run-all-tests)
