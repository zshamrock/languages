(ns roman-literals-web.core-test
  (:require [clojure.test :refer :all]
            [roman-literals-web.core :as core :refer :all]
            [ring.util.request :as req-util :refer :all]
            [ring.util.response :as res-util :refer :all]))

(deftest handler-test
  (testing "serving index.html"
    (let [response-file (atom nil) content-type (atom nil)]
      (with-redefs [req-util/in-context? (fn [request context] false)
                    req-util/path-info (fn [request] "index.html")
                    res-util/file-response (fn [file & [opts]] (reset! response-file file))
                    res-util/content-type (fn [response t] (reset! content-type t))]
        (core/handler {})
        (is (= "index.html" @response-file))
        (is (= "text/html" @content-type))))))
