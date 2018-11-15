(ns eff.ui.pages.comments
  (:require
    [keechma.ui-component :as ui]
    [eff.iota-util :as iota-util]
    [keechma.toolbox.ui :refer  [sub> route>]]
    [keechma.toolbox.util :refer  [class-names]]))

(defn render  [ctx]
  [:div.container.content.section.has-text-centered
   [:div.box
    [:h1.has-text-left  "Post a comment"]
   [(ui/component ctx :comment-form)]
    ]
   [:h5  {:style  {:word-wrap  "break-word"}}  "Comment seed : " iota-util/comment-seed ]
   [:img  {:src  (str  "https://api.qrserver.com/v1/create-qr-code/?data=" iota-util/comment-seed  "&size=80x80" )}]
   [:h5  "You can add this seed to trinity and view all the comments transactions, and watch your comment being posted live :)"]
   [(ui/component ctx :comments-display)]
   ])

(def component
  (ui/constructor  {:renderer render
                   :component-deps [:comments-display
                                    :comment-form] 
                    }))
