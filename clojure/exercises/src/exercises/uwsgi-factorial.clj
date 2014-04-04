(ns exercises.uwsgi-factorial
 (:require [exercises.factorial :as f]))

(defn handler [req]
 (let [query-string (get req :query-string "n=10")
          n (Integer/parseInt (nth (clojure.string/split query-string #"=") 1))]

     {:status 200
         :headers {"Content-Type" "text/html" "Server" "uWSGI"}
         :body (str "Factorial of " n " is " (f/fac n) "\n")}
  )
)
