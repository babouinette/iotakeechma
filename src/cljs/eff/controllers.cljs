(ns eff.controllers
  (:require
    [eff.controllers.user-actions :as user-actions]
    ))

(def controllers
  (-> {
       :user-actions user-actions/controller
       }))
