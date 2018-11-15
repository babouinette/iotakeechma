(ns eff.ui.components.hero
  (:require [keechma.ui-component :as ui]
            [keechma.toolbox.ui :refer  [route> sub>]]
            [keechma.toolbox.util :refer  [class-names]]))

(defn hero [color title subtitle]
  [:section.hero  {:class color}
   [:div.hero-body
    [:div.container
     [:h1.title  title]
     [:h2.subtitle  subtitle]]]])

(defn render [ctx]
  (let [current-route (route> ctx)
        current-page (:page current-route)]
    (case current-page
      "home"     (hero "is-warning" "nice" "try")
      "how-to"     (hero "is-primary" "nice" "try")
      "generator"     (hero "is-danger" "nice" "try")
      "tutorial"     (hero "is-info" "nice" "try")
      "comments"     (hero "is-light" "nice" "try")
      [:p "Not found" ])))

(def component
  (ui/constructor  {:renderer render}))
