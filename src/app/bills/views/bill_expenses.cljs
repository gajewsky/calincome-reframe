(ns app.bills.views.bill-expenses)

(defn bill-expenses
  [expenses]

  [:div {:class "cards"}
   (for [{:keys [id description]} (vals expenses)]
     ^{:key id}
     [:div description])])
