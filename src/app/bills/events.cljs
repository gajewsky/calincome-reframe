(ns app.bills.events
  (:require [re-frame.core :refer [reg-event-db]]))

(reg-event-db
  :delete-expense
  (fn [db [_ expense-id]]
    (let [bill-id (get-in db [:nav :active-bill])]
      (update-in db [:bills bill-id :expenses] dissoc expense-id))))

(reg-event-db
  :upsert-expense
  (fn [db [_ {:keys [id description value subcategory-id track?]}]]
    (let [bill-id (get-in db [:nav :active-bill])]
      (assoc-in db [:bills bill-id :expenses id] {:id id
                                                  :description description
                                                  :value value
                                                  :subcategory-id subcategory-id
                                                  :track? track?}))))


