(defproject phrag-standalone "0.4.5"
  :description ""
  :url "https://github.com/ykskb/phrag-standalone"
  :min-lein-version "2.0.0"
  :aot :all
  :main phrag-standalone.main
  :dependencies [[org.clojure/clojure "1.10.3"]
                 [eftest "0.5.9"]
                 [integrant "0.8.0"]
                 [integrant/repl "0.3.2"]
                 [metosin/reitit "0.5.15"]
                 [ch.qos.logback/logback-classic "1.1.1"]
                 [org.postgresql/postgresql "42.3.0"]
                 [org.xerial/sqlite-jdbc "3.34.0"]
                 [ring-cors "0.1.13"]
                 [ring/ring-core "1.9.5"]
                 [ring/ring-json "0.5.1"]
                 [ring/ring-jetty-adapter "1.9.3"]
                 [threatgrid/ring-graphql-ui "0.1.3"]
                 [com.github.ykskb/phrag "0.4.5"]
                 [hikari-cp "2.14.0"]
                 [environ "1.2.0"]]
  :resource-paths ["resources" "target/resources"]
  :plugins [[lein-eftest "0.5.9"]
            [lein-cloverage "1.2.2"]
            [lein-environ "1.2.0"] ]
  :eftest {:report eftest.report.pretty/report
           :report-to-file "target/junit.xml"}
  :profiles
  {:dev  [:project/dev :profiles/dev]
   :repl {:repl-options {:init-ns user}}
   :profiles/dev {:env {:db-type "postgresql"
                        :db-host "localhost"
                        :db-port "5432"
                        :db-user "postgres"
                        :db-password "example"
                        :db-current-schema "public"
                        :db-name "sns"}}
   :project/dev  {:source-paths   ["dev/src"]
                  :resource-paths ["dev/resources"]
                  :dependencies   []}})

