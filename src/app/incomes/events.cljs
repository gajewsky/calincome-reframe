(ns app.incomes.events
  (:require [re-frame.core :refer [reg-event-db reg-event-fx]]
            [nano-id.core :refer [nano-id]]
            [app.utils :refer [index-by]]))

(def incomes-path "/incomes/")

(defn income-path
  [id]
  (str incomes-path (name id)))

(reg-event-fx
  :delete-income
  (fn [{:keys [db]} [_ id]]
    {:db (update-in db [:incomes] dissoc id)
     :persist-delete {:path (income-path id)}}))

(reg-event-fx
  :update-income
  (fn [{:keys [db]} [_ {:keys [user-id inc-category date value]}]]
    (let [id (get-in db [:nav :active-income])
          attrs {:id id
                 :user-id user-id
                 :inc-category inc-category
                 :value value
                 :date date }]

      {:db (update-in db [:incomes id] merge attrs)
       :persist {:path (income-path id) :attrs attrs}
       :navigate-to {:path incomes-path}})))

(reg-event-fx
  :create-income
  (fn [{:keys [db]} [_ user-id]]
    (let [id (keyword (nano-id 10))
          time-now (.now js/Date)
          init-attrs {:id id
                 :user-id user-id
                 :inc-category ""
                 :value 0
                 :date time-now
                 :created-at time-now}]
      {:db (assoc-in db [:incomes id] init-attrs)
       :navigate-to {:path (income-path id)}})))

(reg-event-fx
  :fetch-incomes
  (fn [&_]
    {:fetch {:path incomes-path :on-success [:fetch-incomes-success]}}))

(reg-event-db
  :fetch-incomes-success
  (fn [db [_ response]]
    (let [incomes (index-by :id response)]
      (assoc db :incomes incomes))))

