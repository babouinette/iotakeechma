(ns eff.controllers.user-actions
  (:require [keechma.toolbox.pipeline.core :as pp :refer-macros  [pipeline!]]
            [keechma.toolbox.pipeline.controller :as pp-controller]
            [eff.edb :refer [insert-item get-named-item remove-item]]
            [eff.iota-util :as iota-util]
            [promesa.core :as p]
            ))

(defn toggle-menu [app-db]
  (let [menu-state (get-in app-db [:kv :menu-state])]
    (prn "Toggled menu state to : " (not  menu-state) )
    (pp/commit! (assoc-in app-db  [:kv :menu-state] (not menu-state)))
    ))

(defn turn-menu-off [app-db]
  (prn "Reset menu state to false")
  (pp/commit! (assoc-in app-db  [:kv :menu-state] false)))


(def controller
  (pp-controller/constructor
    (fn  [_]
      true)
    {:toggle-menu  (pipeline!  [value app-db] (toggle-menu app-db)) 
     :turn-menu-off  (pipeline!  [value app-db] (turn-menu-off app-db)) 
     :update-comments (pipeline! [value app-db]
                                 (iota-util/get-comments)
                                 (p/delay 1000)
                                 (pp/commit! (assoc-in app-db [:kv :comments] @iota-util/comments)) 
                                 )
     :toggle-comment-is-loading (pipeline! [value app-db]

                                           (let [is-loading (get-in app-db [:kv :comment-is-loading])]
                                             (pp/commit! (assoc-in app-db [:kv :comment-is-loading] (not is-loading))) 
                                             ) )

     }))
