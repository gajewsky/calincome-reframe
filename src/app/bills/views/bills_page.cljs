(ns app.bills.views.bills-page
  (:require [re-frame.core :as rf]
            [app.bills.views.bill-list :refer [bill-list]]
            [reagent-material-ui.core.typography :refer [typography]]
            [reagent-material-ui.core.button :refer [button]]))

(defn bills-page
  []
  (let [bills @(rf/subscribe [:bills])
        logged-in? @(rf/subscribe [:logged-in?])
        current-user-id (:uid @(rf/subscribe [:current-user]))
        save #((rf/dispatch [:create-bill %]))]

    [:<>
     (when logged-in?
       [:<>
        [button {:on-click #(save current-user-id)
                 :color "secondary"
                 :variant "contained"} "Add new"]
        [typography {:variant "h4"} "Bills"]
        [bill-list bills]])]))
