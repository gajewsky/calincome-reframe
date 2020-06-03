(ns app.incomes.views.incomes-page
  (:require [re-frame.core :as rf]
            [app.incomes.views.income-list :refer [income-list]]
            [reagent-material-ui.core.typography :refer [typography]]
            [reagent-material-ui.core.button :refer [button]]))

(defn incomes-page
  []
  (let [incomes @(rf/subscribe [:incomes])
        logged-in? @(rf/subscribe [:logged-in?])
        current-user-id (:uid @(rf/subscribe [:current-user]))
        save #(rf/dispatch [:create-income %])
        load-incomes #(rf/dispatch [:fetch-incomes])]

    [:<>
     (when logged-in?
       [:<>
        [button {:on-click #(save current-user-id)
                 :color "secondary"
                 :variant "contained"} "Add new"]
        [button {:on-click #(load-incomes)
                 :color "secondary"
                 :variant "contained"} "Load records"]
        [typography {:variant "h4"} "Incomes"]
        [income-list incomes]])]))

