(ns roman-literals-web.core-test
  (:require [clojure.test :refer :all]
            [roman-literals-web.core :as core :refer :all]
            [ring.util.request :as request-util :refer :all]
            [ring.util.response :as response-util :refer :all]))

(deftest handler-test
  (testing "serving index.html"
    (let [response-file (atom nil) content-type (atom nil)]
      (with-redefs [request-util/in-context? (fn [request context] false)
                    response-util/file-response (fn [file properties] (reset! response-file file))
                    response-util/content-type (fn [t] (reset! content-type t))]
        (core/handler {})
        (is (= "index.html" @response-file))))))
        
