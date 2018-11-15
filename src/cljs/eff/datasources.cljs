(ns eff.datasources
  (:require 
    [keechma.toolbox.dataloader.subscriptions :refer  [map-loader]]
    [eff.iota-util :as iota-util]
    [promesa.core :as p]
    [eff.ui.pages.comments :as comment-page]
    ) )

(def datasources {:comments
                  {:target [:kv :comments]
                   :loader  (map-loader
                              (fn  [req]
                                (when  (:params req)
                                  (do
                                    (iota-util/get-comments)
                                   @iota-util/comments )
                                    )) )
                   :params  (fn  [prev  {:keys  [page]} deps]
                              (when  (= "comments" page) true))}
                  })
