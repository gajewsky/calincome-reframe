(ns app.vendors.events
  (:require [re-frame.core :refer [reg-event-db reg-event-fx]]))

(reg-event-db
  :delete-vendor
  (fn [db [_ vendor-id]]
    (update-in db [:vendors] dissoc vendor-id)))

(reg-event-fx
  :update-vendor
  (fn [{:keys [db]} [_ {:keys [id name description category revolut-id]}]]
    (let [vendor-id (get-in db [:nav :active-vendor])]

      {:db (update-in db [:vendors vendor-id] merge {:id id
                                     :name name
                                     :description description
                                     :category category
                                     :revolut-id revolut-id})
       :navigate-to {:path "/vendors/"}})))

(reg-event-fx
  :create-vendor
  (fn [{:keys [db]} [_]]
    (let [vendor-id (keyword (str "ven-" (random-uuid)))
          vendor-path (str "/vendors/" (name vendor-id))]

      {:db (assoc-in db [:vendors vendor-id] {:id vendor-id
                                              :name ""
                                              :description ""
                                              :category ""
                                              :revolut-id ""})
       :navigate-to {:path vendor-path}})))

