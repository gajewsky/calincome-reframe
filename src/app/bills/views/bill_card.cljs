(ns app.bills.views.bill-card
  (:require [app.router :as router]
            [re-frame.core :as rf]
            [app.bills.views.bill-expenses :refer [bill-expenses]]))

(defn bill-card
  [{:keys [id contractor-id user-id date created-at expenses]}]
  [:div
   {:key id}
   [:a {:href (router/path-for :bill :bill-id id)} date]

   [:button {:on-click #(when (js/confirm "Are you sure?")
                          (rf/dispatch [:delete-bill id]))}
    "Delete"]])


