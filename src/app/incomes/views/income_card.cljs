(ns app.incomes.views.income-card
  (:require [app.router :as router]))

(defn income-card
  [{:keys [id user-id date created-at]}]
  [:div
   {:key id}
   [:a {:href (router/path-for :income :income-id id)} date]])


