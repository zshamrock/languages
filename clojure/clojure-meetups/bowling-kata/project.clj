(defproject bowling-kata "0.1.0-SNAPSHOT"
  :description "Bowling kata"
  :url "https://gist.github.com/zshamrock/90d3cb80bc05a43d94e1"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]]
  :main ^:skip-aot bowling-kata.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}
             :dev {:dependencies [[midje "1.6.3"]]}})
