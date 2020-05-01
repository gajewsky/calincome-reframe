(ns app.bills.events
  (:require [re-frame.core :refer [reg-event-db reg-event-fx]]))

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
(reg-event-db
  :update-bill
  (fn [db [_ {:keys [id divide? contractor-id user-id date]}]]
    (let [bill-id (get-in db [:nav :active-bill])]
      (update-in db [:bills bill-id] merge {:id id
                                     :divide? divide?
                                     :contractor-id contractor-id
                                     :user-id user-id
                                     :date date }))))

(reg-event-fx
  :create-bill
  (fn [{:keys [db]} [_ user-id]]
    (let [bill-id (keyword (str "bill-" (random-uuid)))
          exp-id (keyword (str "exp-" (random-uuid)))
          time-now (.now js/Date)
          bill-path (str "/bills/" (name bill-id))]
      {:db (assoc-in db [:bills bill-id] {:id bill-id
                                          :divide? false
                                          :contractor-id ""
                                          :user-id user-id
                                          :date time-now
                                          :created-at time-now
                                          :expenses {exp-id {:id exp-id
                                                             :description ""
                                                             :value 0
                                                             :subcategory-id ""
                                                             :track? false} }})
       :dispatch [:set-active-nav :log-in]
       :navigate-to {:path bill-path}})))

