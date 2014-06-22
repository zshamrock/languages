(in-ns 'user)

(require '[clojure.java [io :refer [file]] 
                         [shell :refer [sh]]])

(let [files (-> (file ".") (.listFiles))
      projects (filter #(.isDirectory %) files)]
  (doseq [project projects]
    (println "Update" (str (.getName project) "..."))
    (println (:out (sh "git" "pull" :dir project))))
  )
(System/exit 0)
