(ns app.incomes.views.income-card
  (:require [app.router :as router]
            [re-frame.core :as rf]))

(defn income-card
  [{:keys [id user-id date created-at]}]
  [:div
   {:key id}
   [:a {:href (router/path-for :income :income-id id)} date]

   [:button {:on-click #(when (js/confirm "Are you sure?")
                          (rf/dispatch [:delete-income id]))}
    "Delete"]])
