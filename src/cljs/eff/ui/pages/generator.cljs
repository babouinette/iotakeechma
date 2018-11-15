(ns eff.ui.pages.generator
  (:require [keechma.ui-component :as ui]
            [keechma.toolbox.ui :refer  [sub> route>]]
            [keechma.toolbox.util :refer  [class-names]]))


(defn render  [ctx]
  [:div
  [:p "Hello from generator seeed page content"]])

(def component
  (ui/constructor  {:renderer render}))
