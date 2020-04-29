(ns app.bills.views.bill-editor
  (:require [re-frame.core :as rf]
            [reagent.core :as r]
            [app.components.form-group :refer [form-group]]
            [app.bills.views.bill-expenses :refer [bill-expenses]]))

(defn bill-page
  []
  (let [bill @(rf/subscribe [:bill])
        values (r/atom bill)
        save (fn [{:keys [id divide? contractor-id user-id date]}]
               (rf/dispatch [:update-bill {:id id
                                           :divide? divide?
                                           :contractor-id contractor-id
                                           :user-id user-id
                                           :date date }]))]
    [:<>
     [form-group {:id :divide?
                  :label "Divide?"
                  :type "text"
                  :values values}]
     [form-group {:id :contractor-id
                  :label "Contractor"
                  :type "text"
                  :values values}]
     [form-group {:id :user-id
                  :label "User"
                  :type "text"
                  :values values}]
     [form-group {:id :date
                  :label "Date"
                  :type "text"
                  :values values}]
     [:button {:on-click #(save @values)} "Save"]
     [:div "Expenses:"
      [bill-expenses]]]))

