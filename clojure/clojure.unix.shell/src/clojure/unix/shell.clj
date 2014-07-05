(ns clojure.unix.shell
  (:require [clojure.java.shell :refer [sh]]
            [clojure.java.io :refer [file]])  
  )

; took from clojure.java.io
(defn- parse-args [args]
  (let [default-encoding "UTF-8" ;; see sh doc string
        default-opts {:files-only false}
        [cmd opts] (split-with string? args)]
    [cmd (merge default-opts (apply hash-map opts))]))

(defn- -exec [cmd & args]
  (let [returns (apply sh cmd args)
        {:keys [out exit err]} returns]    
    (if (zero? exit)
      (when-not (empty? out) out)
      (do 
        err 
        (System/exit exit)))))

(defn ls 
  "ls <optional directory, by default current directory> + options. Available options are: :files-only true"
  [& args]
  (let [[cmd opts] (parse-args args)
        dir (or (first cmd) ".")
        files (clojure.string/split (-exec "ls" :dir dir) #"\n") 
        java-files (map file files)
        {:keys [files-only]} opts]
    (if files-only
      (filter #(.isFile %) java-files)
      java-files)))

; TODO: check if the src and dest are directories (both), and do recursive copying
; meaningfull combinations: file->file, file->directory, directory->directory 
(defn cp 
  "copy src into dest. Both src and dest can be keywords or strings (in any combination), for ease of use and less typing. Ex.: (cp :README.md :documentation)."
  [src dest]
  (let [from (name src)
        to (name dest)]
    (-exec "cp" from to)))

(defn rm 
  "rm the target. Target can be the keyword."
  [target]
  (-exec "rm" (name target)))

; commands to implement: mv chown chmod grep sort pwd cd? shutdown service ps free top kill cat df mount mkdir whereis whatis which locate tail deb/rpm/yum (based on the distribution) wget   

; maybe specific commands/support for git, as plugins, extensions
