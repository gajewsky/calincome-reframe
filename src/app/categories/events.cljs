(ns app.categories.events
  (:require [re-frame.core :refer [reg-event-db reg-event-fx]]
            [nano-id.core :refer [nano-id]]
            [app.utils :refer [index-by]]))

(def categories-path "/categories/")

(defn category-path
  [id]
  (str categories-path (name id)))

(reg-event-fx
  :delete-category
  (fn [{:keys [db]} [_ id]]
    {:db (update-in db [:categories] dissoc id)
     :persist-delete {:path (category-path id)}}))

(reg-event-fx
  :update-category
  (fn [{:keys [db]} [_ {:keys [group name description]}]]
    (let [id (get-in db [:nav :active-category])
          attrs {:id id
                 :group group
                 :name name
                 :description description}]

      {:db (update-in db [:categories id] merge attrs)
       :persist {:path (category-path id) :attrs attrs}
       :navigate-to {:path categories-path}})))

(reg-event-fx
  :create-category
  (fn [{:keys [db]} [_]]
    (let [id (keyword (nano-id 10))
          init-attrs {:id id
                      :group ""
                      :name ""
                      :description ""}]

      {:db (assoc-in db [:categories id] init-attrs)
       :navigate-to {:path (category-path id)}})))

(reg-event-fx
  :fetch-categories
  (fn [&_]
    {:fetch {:path categories-path :on-success [:fetch-categories-success]}}))

(reg-event-db
  :fetch-categories-success
  (fn [db [_ response]]
    (let [categories (index-by :id response)]
      (assoc db :categories categories))))
