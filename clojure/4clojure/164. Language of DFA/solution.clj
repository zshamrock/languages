; 164. Language of a DFA
; http://www.4clojure.com/problem/164

(in-ns 'user)

(require '[clojure.test :refer [is]])

(defn- traverse [transitions seen-states state strings string dfa]
  (if (seq transitions)
    (apply clojure.set/union 
           (for [[state-value new-state] (vec transitions) 
                 :when (not (contains? seen-states new-state))
                 :let [new-string (str string state-value)]]
             (do
               (comment (println state-value new-state))

               (traverse 
                 (get (:transitions dfa) new-state) 
                 (conj seen-states new-state) 
                 new-state 
                 (if (contains? (:accepts dfa) new-state) (conj strings new-string) strings) 
                 new-string 
                 dfa))))  
    strings)
  )

(defn recognized-strings [{:keys [states alphabet start accepts transitions] :as dfa}]
  (lazy-seq (traverse (get transitions start) #{start} start [] "" dfa)))

(defn- run-all-test []
  (is (= #{"a" "ab" "abc"}
         (set (recognized-strings 
                 '{:states #{q0 q1 q2 q3}
                   :alphabet #{a b c}
                   :start q0
                   :accepts #{q1 q2 q3}
                   :transitions  {q0  {a q1}
                                  q1  {b q2}
                                  q2  {c q3}}}))))

  (is (= #{"hi" "hey" "hello"}
         (set (recognized-strings 
                '{:states #{q0 q1 q2 q3 q4 q5 q6 q7}
                  :alphabet #{e h i l o y}
                  :start q0
                  :accepts #{q2 q4 q7}
                  :transitions {q0 {h q1}
                                q1 {i q2, e q3}
                                q3 {l q5, y q4}
                                q5 {l q6}
                                q6 {o q7}}})))) 

  (is (= (set (let [ss "vwxyz"] (for [i ss, j ss, k ss, l ss] (str i j k l))))
         (set (recognized-strings 
                '{:states #{q0 q1 q2 q3 q4}
                  :alphabet #{v w x y z}
                  :start q0
                  :accepts #{q4}
                  :transitions {q0 {v q1, w q1, x q1, y q1, z q1}
                                q1 {v q2, w q2, x q2, y q2, z q2}
                                q2 {v q3, w q3, x q3, y q3, z q3}
                                q3 {v q4, w q4, x q4, y q4, z q4}}}))))

  (let [res (take 2000 (recognized-strings 
                         '{:states #{q0 q1}
                           :alphabet #{0 1}
                           :start q0
                           :accepts #{q0}
                           :transitions {q0 {0 q0, 1 q1}
                                         q1 {0 q1, 1 q0}}}))]
    (is (and (every? (partial re-matches #"0*(?:10*10*)*") res)
             (= res (distinct res)))))
  )

(run-all-test)
