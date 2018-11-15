(ns eff.runner
    (:require [doo.runner :refer-macros [doo-tests]]
              [eff.core-test]))

(doo-tests 'eff.core-test)
