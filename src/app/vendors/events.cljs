(ns app.vendors.events
  (:require [re-frame.core :refer [reg-event-db reg-event-fx]]
            [nano-id.core :refer [nano-id]]
            [app.utils :refer [index-by-id]]))

(def vendors-path "/vendors/")

(defn vendor-path
  [id]
  (str vendors-path (name id)))

(reg-event-fx
  :delete-vendor
  (fn [{:keys [db]} [_ id]]
    {:db (update-in db [:vendors] dissoc id)
     :firestore/delete {:path (vendor-path id)}}))

(reg-event-fx
  :update-vendor
  (fn [{:keys [db]} [_ {:keys [name description category revolut-id]}]]
    (let [id (get-in db [:nav :active-vendor])
          attrs {:id id
                 :name name
                 :description description
                 :category category
                 :revolut-id revolut-id}]

      {:db (update-in db [:vendors id] merge attrs)
       :firestore/save {:path (vendor-path id) :attrs attrs}
       :navigate-to {:path vendors-path}})))

(reg-event-fx
  :create-vendor
  (fn [{:keys [db]} [_]]
    (let [id (keyword (nano-id 10))
          init-attrs {:id id
                      :name ""
                      :description ""
                      :category ""
                      :revolut-id ""}]

      {:db (assoc-in db [:vendors id] init-attrs)
       :navigate-to {:path (vendor-path id)}})))

(reg-event-fx
  :get-vendors
  (fn [&_]
    {:firestore/get-col {:path vendors-path :on-success [:get-vendors-success]}}))

(reg-event-db
  :get-vendors-success
  (fn [db [_ response]]
    (->> response
         index-by-id
         (merge (db :vendors))
         (assoc db :vendors))))
