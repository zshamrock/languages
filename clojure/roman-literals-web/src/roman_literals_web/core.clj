(ns roman-literals-web.core
  (require [ring.util.response :as response-util :refer :all]
           [ring.util.request :as request-util :refer :all]))


(defn- static-response
  "Serve static files"
  ([file] (static-response file "text/html"))
  ([file content-type]
    (-> (response-util/file-response file {:root "public"})
        (response-util/content-type content-type))))

(defn handler [request]
  (cond
    (request-util/in-context? request "/js/") 
      (static-response (request-util/path-info request) "application/javascript")
    (= (request-util/path-info request) "/")  
      (static-response "index.html")
    :else 
      (response-util/not-found "")))
