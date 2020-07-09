(ns app.bills.events
  (:require [re-frame.core :refer [reg-event-db reg-event-fx]]
            [nano-id.core :refer [nano-id]]
            [app.utils :refer [index-by-id]]))

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
          bill {:id (name id)
                :divide? divide?
                :contractor-id contractor-id
                :user-id user-id
                :date date}
          doc (merge bill {:expenses expenses})
          doc-batch  {:bill bill
                      :expenses (vals expenses)}]

      {:db (update-in db [resource id] merge doc)
       :firestore/write-batch! {:doc-batch doc-batch}
       :navigate-to {:path index-path}})))

(reg-event-fx
  :create-bill
  (fn [{:keys [db]} [_ user-id]]
    (let [bill-id (nano-id 10)
          bill-key (keyword bill-id)
          exp-id (nano-id 10)
          exp-key (keyword exp-id)
          time-now (.now js/Date)
          bill-path (str "/bills/" bill-id)]
      {:db (assoc-in db [:bills bill-key] {:id bill-id
                                           :divide? false
                                           :contractor-id ""
                                           :user-id user-id
                                           :date time-now
                                           :created-at time-now
                                           :expenses {exp-key {:id exp-id
                                                               :bill-id bill-id
                                                               :description ""
                                                               :value 0
                                                               :subcategory-id ""
                                                               :track? false}}})
       :navigate-to {:path bill-path}})))

(reg-event-fx
  :get-bills
  (fn [&_]
    {:firestore/get-col {:ref [resource] :on-success [:get-bills-success]}}))

(reg-event-db
  :get-bills-success
  (fn [db [_ response]]
    (->> response
         index-by-id
         (merge (db resource))
         (assoc db resource))))
