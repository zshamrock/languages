(defproject exercises "0.1.0-SNAPSHOT"
  :description "Clojure exercises"
  :url "https://github.com/zshamrock/languages/tree/master/clojure/exercises"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                          [org.clojure/clojure-contrib "1.2.0"]]
  :main ^:skip-aot exercises.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
