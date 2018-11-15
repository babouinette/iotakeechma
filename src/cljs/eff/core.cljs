(ns eff.core 
  (:require [reagent.core :as reagent]
            [keechma.app-state :as app-state]
            [keechma.toolbox.dataloader.app :as dataloader]
            [keechma.toolbox.forms.app :as forms]
            [eff.controllers :refer [controllers]]
            [eff.iota-util :as iota-util]
            [eff.ui :refer [ui]]
            [eff.subscriptions :refer [subscriptions]]
            [eff.edb :refer [edb-schema]]
            [eff.datasources :refer [datasources]]
            [eff.forms :as eff-forms]))

(def app-definition
  (-> {:components    ui
       :controllers   controllers
       :subscriptions subscriptions
       :routes        [[""  {:page  "home"}]
                       ":page"
                       ":page/:subpage"
                       ]
       :html-element  (.getElementById js/document "app")}
      (dataloader/install datasources edb-schema)
      (forms/install eff-forms/forms eff-forms/forms-automount-fns)))

(defonce running-app (clojure.core/atom nil))

(defn start-app! []
  (reset! running-app (app-state/start! app-definition)))

(defn dev-setup []
  (when ^boolean js/goog.DEBUG
    (enable-console-print!)
    (println "dev mode")))

(defn reload []
  (let [current @running-app]
    (if current
      (app-state/stop! current start-app!)
      (start-app!))))

(defn ^:export main []
  (dev-setup)
  (start-app!))
