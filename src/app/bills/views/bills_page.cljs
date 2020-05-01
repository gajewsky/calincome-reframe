(ns app.bills.views.bills-page
  (:require [re-frame.core :as rf]
            [app.bills.views.bill-list :refer [bill-list]]
            ["rebass" :refer [Heading]]))

(defn bills-page
  []
  (let [bills @(rf/subscribe [:bills])
        logged-in? @(rf/subscribe [:logged-in?])
        current-user-id (:uid @(rf/subscribe [:current-user]))
        save #((rf/dispatch [:create-bill %]))]

    [:<>
     (when logged-in?
       [:<>

        [:button {:on-click #(save current-user-id)} "Add new"]
        [:> Heading {:variant "h4"
                     :py 20
                     :font-weight 700}
         "bills"]
        [bill-list bills]])]))
