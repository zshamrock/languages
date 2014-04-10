(ns roman-literals-web.core
  (require [ring.util.response :refer :all]))

(defn handler [request]
  (file-response "index.html" {:root "public"}))
;  {:status 200
;   :headers {"Content-Type" "text/html"}
;   :body "Roman literals SPA soon must be there some changes"})

