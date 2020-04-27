(ns app.bills.views.bill-list
  (:require ["@material-ui/core" :refer [Box]]))

(defn expense-list
  [{expenses :expenses}]

  [:> Box {:class "cards"}
   (for [expense (vals expenses)]
     (:description expense)

     )])

(defn bill-list
  [bills]
  [:> Box {:class "cards"}
   (for [bill bills]
     (let [{:keys [date]} bill]
       [:<>

        ^{:key (:id bill)}
        date

        [expense-list bill]]))])
