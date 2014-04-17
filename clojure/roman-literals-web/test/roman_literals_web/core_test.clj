(ns roman-literals-web.core-test
  (:require [clojure.test :refer :all]
            [roman-literals-web.core :as core :refer :all]
            [ring.util.request :as req-util :refer :all]
            [ring.util.response :as res-util :refer :all]))

(defn- path-with-prefix [path]
  (str "public/" path))

(defn- build-request [uri]
  {:server-port 3000 
   :server-name "localhost" 
   :remote-addr "127.0.0.1"
   :uri uri
   :scheme :http
   :request-method :get
   :headers {}})

(defn- make-request [uri]
  (core/handler (build-request uri)))

(defn- verify [uri {:keys [expected-path expected-content-type]}]
  (let [r (make-request uri) 
        actual-path (-> r :body .getPath)
        actual-content-type (-> r :headers (get "Content-Type"))]
    (is (= expected-path actual-path))
    (is (= expected-content-type actual-content-type))))

(deftest handler-test
  (testing "serving index.html"
    (verify "/" {:expected-path (path-with-prefix "index.html")
                 :expected-content-type "text/html"}))

  (testing "serving js file"
    (verify "/js/main.js" {:expected-path (path-with-prefix "js/main.js")
                           :expected-content-type "application/javascript"}))
  
  (testing "not found"
    (let [r (make-request "/none")]
      (is (= 404 (r :status)))
      (is (= "" (r :body))))))
