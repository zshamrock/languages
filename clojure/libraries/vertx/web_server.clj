#_(defdeps 
  [[io.vertx/vertx-platform "2.1.2"]
   [io.vertx/lang-clojure "1.0.2"]
   ]
  )

(ns example.server
  (:require [vertx.http :as http])
  )

(-> (http/server)
    (http/on-request
      (fn [req]
        (let [uri (.uri req)]
          (println uri)
          (-> req 
              (http/server-response)
              (http/send-file (str "webroot/" (if (= "/" uri) "index.html" uri)))))))
    (http/listen 8080))
