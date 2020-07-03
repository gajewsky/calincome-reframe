(ns app.categories.events
  (:require [re-frame.core :refer [reg-event-db reg-event-fx]]
            [nano-id.core :refer [nano-id]]
            [app.utils :refer [index-by-id]]))

(def resource :categories)

(def index-path
  (str "/" (name resource) "/"))

(defn resource-path
  [id]
  (str index-path (name id)))

(defn ref
  [id]
  (vector resource (name id)))

(reg-event-fx
  :delete-category
  (fn [{:keys [db]} [_ id]]
    {:db (update-in db [resource] dissoc (keyword id))
     :firestore/delete! {:ref (ref id)}}))

(reg-event-fx
  :update-category
  (fn [{:keys [db]} [_ {:keys [group name description]}]]
    (let [id (get-in db [:nav :active-category])
          ref (ref id)
          document {:id id
                    :group group
                    :name name
                    :description description}]

      {:db (update-in db [resource id] merge document)
       :firestore/write! {:ref ref :document document}
       :navigate-to {:path index-path}})))

(reg-event-fx
  :create-category
  (fn [{:keys [db]} [_]]
    (let [id (keyword (nano-id 10))
          init-attrs {:id id
                      :group ""
                      :name ""
                      :description ""}]

      {:db (assoc-in db [resource id] init-attrs)
       :navigate-to {:path (resource-path id)}})))

(reg-event-fx
  :get-categories
  (fn [&_]
    {:firestore/get-col {:ref [resource] :on-success [:get-categories-success]}}))

(reg-event-db
  :get-categories-success
  (fn [db [_ response]]
    (->> response
         index-by-id
         (merge (db resource))
         (assoc db resource))))
