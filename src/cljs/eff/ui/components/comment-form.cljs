(ns eff.ui.components.comment-form
  (:require
    [eff.iota-util :as iota-util]
    [cljs-time.format :as f]
    [cljs.reader :as reader]
    [cljs-time.coerce :as c]
    [cljs-time.core :as t]
    [reagent.core :as r]
    [keechma.ui-component :as ui]
    [keechma.toolbox.ui :refer  [route> sub> <cmd]]
    [keechma.toolbox.util :refer  [class-names]]))

(defn render  [ctx]
  (r/with-let  [
                message-name  (r/atom nil )
                message-body  (r/atom nil)
                comment-done  (r/atom nil)
                ]
    (let  [
           message  (str  {:name @message-name :comment @message-body} )
           encoded-message  (iota-util/to-trytes message)
           comment-is-loading (sub> ctx :comment-is-loading)
           ]
      [:div.has-text-left  {:style  {:word-wrap  "break-word"}}
       [:div.field
        [:label  "Name"]
        [:div.control
         [ :input.input  {:type  "text"
                          :placeholder  "Name"
                          :value @message-name
                          :on-change #(reset! message-name  (if  (empty?  (-> % .-target .-value)) nil  (-> % .-target .-value) ))} ]]]
       [:div.field
        [:label  "Message"]
        [:div.control
         [:textarea.textarea  {
                               :placeholder  "Message"
                               :value @message-body
                               :on-change #(reset! message-body  (if  (empty?  (-> % .-target .-value)) nil  (-> % .-target .-value) ) )}]]]
       (if (and @message-name @message-body)
         [:div
          [:p "Your inputs are transformed to a "  (count message )  " bytes long message:"]
          [:p  message]
          [:p  "It is then encoded to a "  (count encoded-message)  " trytes message, which is sent to the tangle as the transaction message:"]
          [:p encoded-message]
          [:br]
          ]
         [:p "You must input a name and a comment"]
         )
       [:div.field
        [:div.control
         [:button.button.is-dark                
          {:on-click #(if (and @message-name @message-body) (do (<cmd ctx :toggle-comment-is-loading) (iota-util/send-comment encoded-message (fn [err res] (do (reset! comment-done res) (<cmd ctx :update-comments)
                                                                                                                                                                (<cmd ctx :toggle-comment-is-loading)
                                                                                                                                                                )))))
          :class (class-names {:is-loading comment-is-loading }) }
          "Submit comment"                     

          ]

         ]]
       ] )
    ) )

(def component
  (ui/constructor  {:renderer render
                    :topic :user-actions
                                  :subscription-deps [:comment-is-loading] 
                    })) 

