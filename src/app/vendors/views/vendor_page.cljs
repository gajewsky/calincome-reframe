(ns app.vendors.views.vendor-page
  (:require [re-frame.core :as rf]
            [app.vendors.views.vendor-list :refer [vendor-list]]
            [reagent-material-ui.core.typography :refer [typography]]
            [reagent-material-ui.core.button :refer [button]]))

(defn vendor-page
  []
  (let [vendors @(rf/subscribe [:vendors])
        logged-in? @(rf/subscribe [:logged-in?])
        save #(rf/dispatch [:create-vendor])]

    [:<>
     (when logged-in?
       [:<>
        [button {:on-click #(save)
                 :color "secondary"
                 :variant "contained"} "Add new"]
        [typography {:variant "h4"} "vendors"]
        [vendor-list vendors]])]))

