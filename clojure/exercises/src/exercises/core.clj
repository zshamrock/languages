(ns exercises.core
  (:require [exercises.factorial :as f])  
  (:gen-class))

(defn- get-n-or-default [n]
    (read-string 
        ((fnil identity "10") n)))

(defn -main [& args]    
    (let [n (get-n-or-default (first args))]
        (println "factorials from 1 till" n "using apply-merge" (f/factorial-from-0-till-n-with-merge n))
    ))   
    

