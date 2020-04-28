(ns app.bills.views.bill-list
  (:require [app.bills.views.bill-card :refer [bill-card]]))

(defn bill-list
  [bills]
  [:div {:class "cards"}
   (for [bill bills]
     [bill-card bill])])

