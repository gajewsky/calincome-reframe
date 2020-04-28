(ns app.bills.events
  (:require [re-frame.core :refer [reg-event-db]]))

(reg-event-db
  :delete-expense
  (fn [db [_ expense-id]]
    (let [bill-id (get-in db [:nav :active-bill])]
      (update-in db [:bills bill-id :expenses] dissoc expense-id))))


