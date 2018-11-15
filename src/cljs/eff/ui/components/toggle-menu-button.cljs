(ns eff.ui.components.toggle-menu-button
  (:require  [keechma.ui-component :as ui]
            [keechma.toolbox.ui :refer  [sub> <cmd]]
            [keechma.toolbox.util :refer  [class-names]]))

(defn render [ctx]
  (let  [menu-is-active? (sub> ctx :menu-state)]
    [:a.navbar-burger
     {:on-click #(<cmd ctx :toggle-menu)
      :style {:color "white"}
      :class (class-names {:is-active menu-is-active?})
      :role "button"
      :aria-label "menu"
      :aria-expanded "false"}
     [:span {:aria-hidden "true"}]
     [:span {:aria-hidden "true"}]
     [:span {:aria-hidden "true"}]]))

(def component
  (ui/constructor  {:renderer render
                    :subscription-deps  [:menu-state]
                    :topic :user-actions
                    }
                    ))
