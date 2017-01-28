(defproject kegan "0.10.0"
  :description "Immutable logs from changing values."
  :url "https://github.com/RackSec/kegan"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [aleph "0.4.1"]
                 [net.cgrand/xforms "0.8.3"]
                 [com.taoensso/timbre "4.8.0"]]
  :plugins [[lein-cljfmt "0.3.0"]
            [lein-cloverage "1.0.7"]]
  :main ^:skip-aot kegan.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}}
  :deploy-repositories [["releases" :clojars]
                        ["snapshots" :clojars]])
