(ns app.bills.views.bill-page
  (:require [re-frame.core :as rf]))

(defn bill-page
  []
  (let [{:keys [id divide? contractor-id user-id date created-at]} @(rf/subscribe [:bill])]
    [:<>
     [:div id]
     [:div divide?]
     [:div contractor-id]
     [:div user-id]
     [:div date]
     [:div created-at]]))
