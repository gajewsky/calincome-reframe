(ns app.incomes.events
  (:require [re-frame.core :refer [reg-event-db reg-event-fx]]
            [nano-id.core :refer [nano-id]]
            [app.utils :refer [index-by-id]]))

(def resource :incomes)

(def index-path
  (str "/" (name resource) "/"))

(defn resource-path
  [id]
  (str index-path (name id)))

(defn ref
  [id]
  (vector resource (name id)))

(reg-event-fx
  :delete-income
  (fn [{:keys [db]} [_ id]]
    {:db (update-in db [resource] dissoc (keyword id))
     :firestore/delete! {:ref (ref id)}}))

(reg-event-fx
  :update-income
  (fn [{:keys [db]} [_ {:keys [user-id inc-category date value]}]]
    (let [id (get-in db [:nav :active-income])
          ref (ref id)
          document {:id id
                    :user-id user-id
                    :inc-category inc-category
                    :value value
                    :date date}]

      {:db (update-in db [resource id] merge document)
       :firestore/write! {:ref ref :document document}
       :navigate-to {:path index-path}})))

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
      {:db (assoc-in db [resource id] init-attrs)
       :navigate-to {:path (resource-path id)}})))

(reg-event-fx
  :get-incomes
  (fn [&_]
    {:firestore/get-col {:ref [resource] :on-success [:get-incomes-success]}}))

(reg-event-db
  :get-incomes-success
  (fn [db [_ response]]
    (->> response
         index-by-id
         (merge (db resource))
         (assoc db resource))))

