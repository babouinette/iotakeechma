(ns eff.core-test
  (:require [cljs.test :refer-macros [deftest testing is]]
            [eff.core :as core]))

(deftest fake-test
  (testing "fake description"
    (is (= 1 2))))
