(ns app.vendors.events
  (:require [re-frame.core :refer [reg-event-db reg-event-fx]]
            [nano-id.core :refer [nano-id]]
            [app.utils :refer [index-by-id]]))

(def resource :vendors)

(def index-path
  (str "/" (name resource) "/"))

(defn resource-path
  [id]
  (str index-path (name id)))

(defn ref
  [id]
  (vector resource (name id)))

(reg-event-fx
  :delete-vendor
  (fn [{:keys [db]} [_ id]]
    {:db (update-in db [resource] dissoc (keyword id))
     :firestore/delete! {:ref (ref id)}}))

(reg-event-fx
  :update-vendor
  (fn [{:keys [db]} [_ {:keys [name description category revolut-id]}]]
    (let [id (get-in db [:nav :active-vendor])
          ref (ref id)
          document {:id id
                    :name name
                    :description description
                    :category category
                    :revolut-id revolut-id}]

      {:db (update-in db [resource id] merge document)
       :firestore/write! {:ref ref :document document}
       :navigate-to {:path index-path}})))

(reg-event-fx
  :create-vendor
  (fn [{:keys [db]} [_]]
    (let [id (keyword (nano-id 10))
          init-attrs {:id id
                      :name ""
                      :description ""
                      :category ""
                      :revolut-id ""}]

      {:db (assoc-in db [resource id] init-attrs)
       :navigate-to {:path (resource-path id)}})))

(reg-event-fx
  :get-vendors
  (fn [&_]
    {:firestore/get-col {:ref [resource] :on-success [:get-vendors-success]}}))

(reg-event-db
  :get-vendors-success
  (fn [db [_ response]]
    (->> response
         index-by-id
         (merge (db resource))
         (assoc db resource))))
