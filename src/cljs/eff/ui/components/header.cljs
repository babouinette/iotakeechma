(ns eff.ui.components.header
  (:require
    [keechma.ui-component :as ui]
            [keechma.toolbox.ui :refer  [route> sub> <cmd]]
            [keechma.toolbox.util :refer  [class-names]]))

(def pages
  [{:route  {:page  "home"}
    :title  "About"}
   {:route  {:page  "how-to"}
    :title  "How To"}
   {:route  {:page  "generator"}
    :title  "Generate"}
   {:route  {:page  "comments"}
    :title  "Comments"}
   {:route  {:page  "tutorial"}
    :title  "Tutorial"}])

(defn render-nav-item  [ctx  {:keys  [route title]}]
  (let  [current-page  (:page  (route> ctx))]
    [:a.navbar-item
     {:key title
      :on-click #(<cmd ctx :turn-menu-off)
      :href (ui/url ctx route)
      :class (class-names {:is-active (= current-page (:page route))})}
     title]))

(defn render-nav  [ctx]
  (let  [menu-is-active?  (sub> ctx :menu-state)]
  [:div.navbar-menu {:class (class-names {:is-active menu-is-active?})}
   [:div.navbar-end
    (doall  (map #(render-nav-item ctx %) pages))]]))

(defn render  [ctx]
  [:nav.navbar.is-dark.is-fixed-top
   [:div.container
    [:div.navbar-brand
     [:a.navbar-item  {:href (ui/url ctx {:page "home"})}
      [:img  {:src  "https://iotaprices.com/img/iota_icon_light.svg" :height  "300"}]
      [:p {:style {:padding-left "1em"}} "IOTA Passphra.se"]]
     [(ui/component ctx :toggle-menu-button)]
     ]
    [render-nav ctx]
    ]])

(def component
  (ui/constructor  {:renderer render
 :subscription-deps  [:menu-state] 
                    :component-deps  [:toggle-menu-button] 
                    :topic :user-actions
                    }))
