(defproject chat-server "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/core.async "0.1.303.0-886421-alpha"]]
  :main ^:skip-aot chat-server.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
