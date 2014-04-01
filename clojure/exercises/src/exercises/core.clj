(ns exercises.core
  (:require [exercises.factorial :as f])  
  (:gen-class))

(defn -main [& args]
    (def n 10)
    (println "factorials from 1 till" n "using apply-merge" (f/factorial-from-0-till-n-with-merge n)))
