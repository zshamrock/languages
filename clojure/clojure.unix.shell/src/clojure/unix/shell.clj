(ns clojure.unix.shell
  (:require [clojure.java.shell :refer [sh]]
            [clojure.java.io :refer [file]])  
  )

; took from clojure.java.io
(defn- parse-args
  [args]
  (let [default-encoding "UTF-8" ;; see sh doc string
        default-opts {:files-only false}
        [cmd opts] (split-with string? args)]
    [cmd (merge default-opts (apply hash-map opts))]))

(defn ls [& args]
  (let [[cmd opts] (parse-args args)
        dir (or (first cmd) ".")
        files (clojure.string/split (:out (sh "ls" :dir dir)) #"\n") 
        java-files (map file files)
        {:keys [files-only]} opts]
    (if files-only
      (filter #(.isFile %) java-files)
      java-files
      )))
