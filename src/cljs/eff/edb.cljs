(ns eff.edb
  (:require [entitydb.core]
            [keechma.toolbox.edb :refer-macros [defentitydb]]))

(def edb-schema {
                :comments {:id :id} })

(defentitydb edb-schema)
