(ns phrag-standalone.core
  (:require [phrag-standalone.system :as sys]))

(defn -main [& _args]
  sys/start)
