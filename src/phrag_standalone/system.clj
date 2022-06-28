(ns phrag-standalone.system
  (:require [integrant.core :as ig]
            [environ.core :refer [env]]
            [ring.adapter.jetty :as jetty]
            [hikari-cp.core :as hkr]
            [phrag.route :as route]
            [reitit.ring :as ring]
            [reitit.ring.coercion :as coercion]
            [reitit.coercion.spec]
            [reitit.ring.middleware.muuntaja :as muuntaja]
            [reitit.ring.middleware.exception :as exception]
            [reitit.ring.middleware.multipart :as multipart]
            [reitit.ring.middleware.parameters :as parameters]
            [ring.middleware.params :as params]
            [ring-graphql-ui.core :as gql]
            [muuntaja.core :as m]))

;;; App

(defmethod ig/init-key ::app [_ {:keys [gql-route]}]
  (ring/ring-handler
   (ring/router
    [gql-route]
    {:data {:coercion reitit.coercion.spec/coercion
            :muuntaja m/instance
            :middleware [parameters/parameters-middleware
                         ;; params/wrap-params
                         muuntaja/format-negotiate-middleware
                         muuntaja/format-response-middleware
                         exception/exception-middleware
                         muuntaja/format-request-middleware
                         coercion/coerce-response-middleware
                         coercion/coerce-request-middleware
                         multipart/multipart-middleware]}})
   (ring/routes
    (gql/graphiql {:endpoint "/graphql"})
    (ring/create-default-handler))))

(defmethod ig/init-key :database.sql/conn-pool [_ db-type]
  (let [spc (if db-type
              {:adapter db-type
               :username (env :db-user)
               :password (env :db-password)
               :database-name (env :db-name)
               :server-name (env :db-host)
               :port-number (env :db-port)
               :current-schema (env :db-current-schema)
               :string-type "unspecified"}
              {:jdbc-url (env :jdbc-url)})
        data-src (delay (hkr/make-datasource spc))]
    {:datasource @data-src}))

(defmethod ig/init-key ::server [_ {:keys [app options]}]
  (jetty/run-jetty app options))

(defmethod ig/halt-key! ::server [_ server]
  (.stop server))

(def config {:database.sql/conn-pool (env :db-type)
             :phrag.route/reitit
             {:db (ig/ref :database.sql/conn-pool)
              :default-limit (env :default-limit 1000)
              :max-nest-level (env :max-nest-level 3)}
             ::app {:gql-route (ig/ref :phrag.route/reitit)}
             ::server {:app (ig/ref ::app)
                       :options {:port  (read-string (env :service-port "3000"))
                                 :join? false}}})

(defn start []
  (ig/init config))
