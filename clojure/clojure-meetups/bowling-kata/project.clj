(defproject bowling-kata "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [midje "1.6.3"]]
  :main ^:skip-aot bowling-kata.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}
             :profiles {:dev {:dependencies [[midje "1.5.1"]]}}})
