(ns phrag-standalone.main
  (:gen-class)
  (:require [phrag-standalone.system :as phrag-sys]))

(defn -main [& _args]
  (phrag-sys/start))
