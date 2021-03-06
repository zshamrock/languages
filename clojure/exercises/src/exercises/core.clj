(ns exercises.core
  (:require [exercises.factorial :as f]
      [exercises.roman-numerals :as roman])  
  (:gen-class))

(defn- get-n-from-args-or-default [args]
    (let [n (first args)]
        (Integer/parseInt (str (or n 10)))))

(defn -main [& args]    
    (let [n (get-n-from-args-or-default args)]
        (println "factorials from 1 till" n "using apply-merge" (f/factorial-from-0-till-n-with-merge n))
        (println "factorials from 1 till" n "using zipmap     " (f/factorial-from-0-till-n-with-zipmap n))
    )
    
    (println "543 in roman is" (roman/to 543)))   
    

