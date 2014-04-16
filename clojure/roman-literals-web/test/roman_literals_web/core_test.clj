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
      (with-redefs [res-util/file-response (fn [f & [opts]] {:body f :status 200 :headers {}})]
        (let [r (core/handler (get-request "/"))]
          (println r)
          (is (= "index.html" (r :body)))
          (is (= "text/html" (-> r :headers (get "Content-Type"))))))))
