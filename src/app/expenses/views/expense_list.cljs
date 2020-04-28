(ns app.expenses.views.expense-list)

(defn expense-list
  [expenses]

  [:div {:class "cards"}
   (for [expense (vals expenses)]
     (:description expense))])

