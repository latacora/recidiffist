(defproject kegan "0.1.0-SNAPSHOT"
  :description "Immutable logs from changing values."
  :url "https://github.com/RackSec/kegan"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]]
  :main ^:skip-aot kegan.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
