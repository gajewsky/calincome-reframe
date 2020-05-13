(ns app.header.views.header
  (:require
    [app.header.views.nav :refer [nav]]
    [app.utils :refer [make-styles]]
    [reagent-material-ui.core.app-bar :refer [app-bar]]
    [reagent-material-ui.core.toolbar :refer [toolbar]]
    [reagent-material-ui.core.typography :refer [typography]]))

(defn header
  []
  [app-bar {:position "static" }
   [toolbar
    [typography
     {:variant "h6"
      :display "block"
      :style {:flexGrow 1}}
     "Calincome"]
    [nav]]])
