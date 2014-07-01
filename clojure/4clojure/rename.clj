(in-ns 'user)

(require '[clojure.java 
           [shell :refer [sh]] 
           [io :refer [file]]])

(let [problems (filter #(re-matches #"^\d{2,}.+" %) (clojure.string/split (:out (sh "ls")) #"\n"))]
  (doseq [problem problems :let [[_ problem-nr problem-name] (re-find #"(^\d{2,})\. (.+)" problem)]]
    (when (< (Integer/parseInt problem-nr) 100)
      (.renameTo (file problem) (file (str "0" problem-nr ". " problem-name))))
    )
  )

(System/exit 0)
