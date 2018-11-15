(ns eff.ui.main
  (:require  [keechma.ui-component :as ui]
            [keechma.toolbox.ui :refer  [route> sub>]]))

(defn render  [ctx]
  (let  [current-route  (route> ctx)
         current-page  (:page current-route)]
    [:div
     [(ui/component ctx :header)]
     [(ui/component ctx :hero)]
     (case current-page
       "home"     [(ui/component ctx :page-home)]
       "how-to"     [(ui/component ctx :page-how-to)]
       "generator"  [(ui/component ctx :page-generator)]
       "tutorial"  [(ui/component ctx :page-tutorial)]
       "comments"    [(ui/component ctx :page-comments)]
       [:div  "404 route not found"])
     [(ui/component ctx :footer)]]))

(def component
  (ui/constructor  {:renderer render
                    :component-deps  [:header
                                      :hero
                                      :footer
                                      :page-home
                                      :page-generator
                                      :page-how-to
                                      :page-tutorial
                                      :page-comments]}))
