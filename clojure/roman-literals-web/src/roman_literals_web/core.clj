(ns roman-literals-web.core
  (require [ring.util.response :refer :all]
           [ring.util.request :refer :all]))

(defn handler [request]
  (println (path-info request))
  (cond
    (in-context? request "/js/") 
      (-> 
        (file-response (path-info request) {:root "public"})
        (content-type "application/javascript"))
    :else 
      (-> 
        (file-response "index.html" {:root "public"})
        (content-type "text/html"))))
;  {:status 200
;   :headers {"Content-Type" "text/html"}
;   :body "Roman literals SPA soon must be there some changes"})

