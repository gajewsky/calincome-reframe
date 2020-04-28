(ns app.bills.views.bill-expenses
  (:require [re-frame.core :as rf]))

(defn bill-expenses
  []
  (fn []
    (let [expenses @(rf/subscribe [:expenses])]
      [:div {:class "cards"}
       (for [{:keys [id description]} expenses]
         ^{:key id}

         [:<>
          [:div description]
          [:a {:href "#"
               :on-click #(when (js/confirm "Are you sure?")
                            (rf/dispatch [:delete-expense id]))}
           "Delete"]])])))


