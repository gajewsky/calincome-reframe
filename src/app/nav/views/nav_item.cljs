(ns app.nav.views.nav-item
  (:require
    [reagent-material-ui.core.bottom-navigation-action :refer [bottom-navigation-action]]
    [reagent-material-ui.core.tab :refer [tab]]
    [reagent-material-ui.core.box :refer [box]]))

(defn nav-item
  [{:keys [id href name dispatch active-page]}]
  [tab {:key id
        :value id
        :label name
        :on-click dispatch
        :href href
        :selected (= active-page id)}])

