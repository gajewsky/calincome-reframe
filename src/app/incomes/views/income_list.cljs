(ns app.incomes.views.income-list
  (:require [app.incomes.views.income-card :refer [income-card]]))

(defn income-list
  [incomes]
  [:div {:class "cards"}
   (for [income incomes]
     ^{:key (:id income)}
     [income-card income])])

