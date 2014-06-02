; 164. Language of a DFA
; http://www.4clojure.com/problem/164

(in-ns 'user)

(require '[clojure.test :refer [is]])

(defn- traverse [transitions seen-states state strings string accepts]
  (if (seq transitions)
    (apply clojure.set/union 
           (for [[state-value new-state] (vec transitions) 
                 :when (not (contains? seen-states new-state))
                 :let [new-string (str string state-value)]]
             (traverse (get transitions new-state) (conj seen-states new-state) new-state (if (contains? accepts new-state) (conj strings new-string) string) new-string accepts)))  
    strings)
  )

(defn recognized-strings [{:keys [states alphabet start accepts transitions] :as dfa}]
  (traverse transitions #{start} start #{} "" accepts))

(defn- run-all-test []
  (is (= #{"a" "ab" "abc"}
         (set  (recognized-strings 
                 '{:states #{q0 q1 q2 q3}
                   :alphabet #{a b c}
                   :start q0
                   :accepts #{q1 q2 q3}
                   :transitions  {q0  {a q1}
                                  q1  {b q2}
                                  q2  {c q3}}}))))
  )

(run-all-test)
