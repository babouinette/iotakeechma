(ns eff.ui
  (:require 
    [eff.ui.components.footer :as footer]
    [eff.ui.components.header :as header]
    [eff.ui.components.hero :as hero]
    [eff.ui.components.comment-form :as comment-form]
    [eff.ui.components.comments-display :as comments-display]
    [eff.ui.components.toggle-menu-button :as toggle-menu-button]
    [eff.ui.pages.home :as page-home]
    [eff.ui.pages.how-to :as page-how-to]
    [eff.ui.pages.generator :as page-generator]
    [eff.ui.pages.tutorial :as page-tutorial]
    [eff.ui.pages.comments :as page-comments]
    [eff.ui.main :as main]))

(def ui
  {:main main/component
   :header          header/component
   :hero          hero/component
   :comments-display          comments-display/component
   :comment-form          comment-form/component
   :toggle-menu-button          toggle-menu-button/component
   :footer          footer/component
   :page-home       page-home/component
   :page-generator       page-generator/component
   :page-tutorial       page-tutorial/component
   :page-how-to       page-how-to/component
   :page-comments       page-comments/component
   })
