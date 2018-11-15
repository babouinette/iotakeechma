(ns eff.ui.components.comments-display
  (:require
    [eff.iota-util :as iota-util]
    [cljs-time.format :as f]
    [cljs.reader :as reader]
    [cljs-time.coerce :as c]
    [cljs-time.core :as t]
    [keechma.ui-component :as ui]
    [keechma.toolbox.ui :refer  [route> sub> <cmd]]
    [keechma.toolbox.util :refer  [class-names]]))

(defn render-comment  [ctx {:keys [timestamp comment]}]
  (let  [comment-parsed  (reader/read-string  comment)
         date (c/from-long (* 1000  timestamp))
         unique-key (apply str (take 5 (repeatedly #(char  (+  (rand 26) 65)))))
         ]
    [:div.box {:key unique-key}
     [:div.media-content
      [:div.content
       [:h4  [:strong  (:name comment-parsed)]  [:small  " commented on "  (f/unparse  (f/formatters :mysql) date)]]
       [:p  (:comment comment-parsed)]
       ]]]
    ))

(defn render  [ctx]
  (let [comments (sub> ctx :comments)]
    [:div.section
     (prn "loaded comments" comments)
     ( doall  (map #(render-comment ctx %) comments))
     ]) )

(def component
  (ui/constructor  {:renderer render
                    :subscription-deps [:comments] 
                    })) 

