(defproject kegan "0.2.0-SNAPSHOT"
  :description "Immutable logs from changing values."
  :url "https://github.com/RackSec/kegan"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [aleph "0.4.1-SNAPSHOT"]
                 [net.cgrand/xforms "0.1.0"]
                 [com.taoensso/timbre "4.2.1-SNAPSHOT"]]
  :plugins [[lein-cljfmt "0.3.0"]]
  :main ^:skip-aot kegan.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}}
  :deploy-repositories [["releases" :clojars]
                        ["snapshots" :clojars]])
