(ns app.bills.events
  (:require [re-frame.core :refer [reg-event-db reg-event-fx]]
            [nano-id.core :refer [nano-id]]))

(def resource :bills)

(def index-path
  (str "/" (name resource) "/"))

(defn ref
  [id]
  (vector resource (name id)))

(reg-event-db
  :delete-bill
  (fn [db [_ id]]
    (update-in db [:bills] dissoc (keyword id))))

(reg-event-fx
  :update-bill
  (fn [{:keys [db]} [_ {:keys [divide? contractor-id user-id date expenses]}]]
    (let [id (get-in db [:nav :active-bill])
          ref (ref id)
          document {:id id
                    :divide? divide?
                    :contractor-id contractor-id
                    :user-id user-id
                    :date date
                    :expenses expenses}
          doc-batch {:bill document
                     :expenses (vals expenses)}]

      {:db (update-in db [resource id] merge document)
       ; :firestore/write-batch! {:doc-batch doc-batch}
       :navigate-to {:path index-path}})))

(reg-event-fx
  :create-bill
  (fn [{:keys [db]} [_ user-id]]
    (let [bill-id (keyword (nano-id 10))
          exp-id (keyword (nano-id 10))
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
                                                             :track? false}}})
       :navigate-to {:path bill-path}})))

