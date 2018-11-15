(ns eff.ui.pages.tutorial
  (:require [keechma.ui-component :as ui]
            [keechma.toolbox.ui :refer  [sub> route>]]
            [keechma.toolbox.util :refer  [class-names]]))


(defn render  [ctx]
  [:div.section.container
   [:div.content.has-text-centered
    [:p.is-size-1  "SOON "
     [:span.icon.is-large
      [:i.fas.fa-registered]]]]]) 

(def component
  (ui/constructor  {:renderer render}))
