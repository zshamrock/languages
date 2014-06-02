; 164. Language of a DFA
; http://www.4clojure.com/problem/164

(in-ns 'user)

(require '[clojure.test :refer [is]])

(defn recognized-strings [{:keys [states alphabet start accepts] :as dfa}]
  (let [strings (atom #{})]

    (loop [seen-states #{start} 
           state start 
           string ""]
      (let [transitions (get (dfa :transitions) state)]
        (doseq [[state-value new-state] (vec transitions) 
                :when (not (contains? seen-states new-state))
                :let [new-string (str string state-value)]]
          (when (contains? accepts new-state)
            (swap! strings conj new-string))

          (recur (conj seen-states new-state) new-state new-string))))

    @strings))

(defn- run-all-test []
  (is (= #{"a" "ab" "abc"}
         (set  (recognized-strings 
                 '{:states #{q0 q1 q2 q3}
                   :alphabet #{a b c}
                   :start q0
                   :accepts #{q1 q2 q3}
                   :transitions  {q0  {a q1 b q2}
                                  q1  {b q2}
                                  q2  {c q3}}}))))
  )

(run-all-test)
