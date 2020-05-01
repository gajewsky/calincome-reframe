(ns app.incomes.views.incomes-page
  (:require [re-frame.core :as rf]
            [app.incomes.views.income-list :refer [income-list]]
            ["rebass" :refer [Heading]]))

(defn incomes-page
  []
  (let [incomes @(rf/subscribe [:incomes])
        logged-in? @(rf/subscribe [:logged-in?])
        current-user-id (:uid @(rf/subscribe [:current-user]))
        save #((rf/dispatch [:create-income %]))]

    [:<>
     (when logged-in?
       [:<>

        [:button {:on-click #(save current-user-id)} "Add new"]
        [:> Heading {:variant "h4"
                     :py 20
                     :font-weight 700}
         "incomes"]
        [income-list incomes]])]))

