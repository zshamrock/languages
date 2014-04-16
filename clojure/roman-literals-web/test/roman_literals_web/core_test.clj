(ns roman-literals-web.core-test
  (:require [clojure.test :refer :all]
            [roman-literals-web.core :as core :refer :all]
            [ring.util.request :as req-util :refer :all]
            [ring.util.response :as res-util :refer :all]))

(defn- get-request [uri]
  {:server-port 3000 
   :server-name "localhost" 
   :remote-addr "127.0.0.1"
   :uri uri
   :scheme :http
   :request-method :get
   :headers {}})

(deftest handler-test
  (testing "serving index.html"
    (let [r (core/handler (get-request "/"))]
      (is (= "index.html" (-> r :body .getName)))
      (is (= "text/html" (-> r :headers (get "Content-Type")))))))
