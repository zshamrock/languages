; 164. Language of a DFA
; http://www.4clojure.com/problem/164

(in-ns 'user)

(require '[clojure.test :refer [is]])

(defn recognized-strings [dfa]
  #{} 
  )

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
