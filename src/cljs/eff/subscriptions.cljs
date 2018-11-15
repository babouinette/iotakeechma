(ns eff.subscriptions
  (:require-macros  [reagent.ratom :refer  [reaction]]))

(defn get-kv  [key]
  (fn  [app-db-atom]
    (reaction
      (get-in @app-db-atom  (flatten  [:kv key])))))

(def subscriptions
  {
   :menu-state  (get-kv :menu-state)
   :comments  (get-kv :comments)
   :comment-is-loading  (get-kv :comment-is-loading)
   })
