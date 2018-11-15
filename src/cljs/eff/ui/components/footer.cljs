(ns eff.ui.components.footer
  (:require  [keechma.ui-component :as ui]))

(defn render  [ctx]
  [:footer.footer
   [:div.container
    [:div.content.has-text-centered
     [:p  "Made with "  [:span.icon.has-text-danger  [:i.fas.fa-heartbeat]]  " by "  [:strong  "Babouinette"]] 
     [:p  "If you want to donate, you can send some iotas to :"]
     [:a {:href  (ui/url ctx  {:page  "home"})}  "IOTAPassphrase"]]]]) 

(def component
  (ui/constructor  {:renderer render}))



