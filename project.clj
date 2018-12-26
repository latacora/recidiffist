(defproject recidiffist "0.11.0-SNAPSHOT"
  :description "Immutable logs from changing values."
  :url "https://github.com/latacora/recidiffist"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [manifold "0.1.8"]
                 [net.cgrand/xforms "0.19.0"
                  :exclusions [org.clojure/clojurescript]]
                 [com.taoensso/timbre "4.10.0"]]
  :plugins [[lein-cljfmt "0.6.3"]
            [lein-cloverage "1.0.13"]]
  :main ^:skip-aot recidiffist.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}}
  :deploy-repositories [["releases" :clojars]
                        ["snapshots" :clojars]])
