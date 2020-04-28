(ns app.bills.views.bill-page
  (:require [re-frame.core :as rf]
            [app.bills.views.bill-expenses :refer [bill-expenses]]))

(defn bill-page
  []
  (let [{:keys [id
                divide?
                contractor-id
                user-id
                date
                created-at
                expenses]} @(rf/subscribe [:bill])]
    [:<>
     [:div id]
     [:div divide?]
     [:div contractor-id]
     [:div user-id]
     [:div date]
     [:div created-at]
     [:div "Expenses:"
      [bill-expenses expenses]]]))

