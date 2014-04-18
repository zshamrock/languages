(require '[clojure.test :refer [is]])

(defn largest-seq [input]
  (if (empty? input)
    []
    (let [lseq (atom []) cseq (atom [])]
      (loop [s input]
        (let [x (first s)]
          (if (empty? @cseq) 
            (reset! cseq [x])
            (if (= (inc (last @cseq)) x)
              (swap! cseq conj x)
              (do 
                (when (> (count @cseq) (count @lseq))
                  (reset! lseq @cseq)
                  (reset! cseq [x])))))
          (when (next s)
            (recur (next s)))))
      (when (> (count @cseq) (count @lseq))
        (reset! lseq @cseq))
      (if (= (count @lseq) 1)
        []
        @lseq))))

(defn- run-all-tests []
  (is (= [] (largest-seq [])))
  (is (= [] (largest-seq [5 4 3 2 1 0])))
  (is (= [0 1 2 3] (largest-seq [1 0 1 2 3 0 4 5])))
  (is (= [5 6] (largest-seq [5 6 1 3 2 7])))
  (is (= [3 4 5] (largest-seq [2 3 3 4 5]))))

(run-all-tests)
