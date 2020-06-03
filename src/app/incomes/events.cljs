(ns app.incomes.events
  (:require [re-frame.core :refer [reg-event-db reg-event-fx]]
            [clojure.walk :refer [keywordize-keys]]))

(reg-event-db
  :delete-income
  (fn [db [_ income-id]]
    (update-in db [:incomes] dissoc income-id)))

(reg-event-fx
  :update-income
  (fn [{:keys [db]} [_ {:keys [id user-id inc-category date value]}]]
    (let [income-id (get-in db [:nav :active-income])
          incomes-path "/incomes/"
          income-path (str "/incomes/" (name income-id))
          income-attrs {:id id
                        :user-id user-id
                        :inc-category inc-category
                        :value value
                        :date date }]

      {:db (update-in db [:incomes income-id] merge income-attrs)
       :navigate-to {:path incomes-path}
       :persist {:path income-path :attrs income-attrs}})))

(reg-event-fx
  :create-income
  (fn [{:keys [db]} [_ user-id]]
    (let [income-id (keyword (str "inc-" (random-uuid)))
          time-now (.now js/Date)
          income-path (str "/incomes/" (name income-id))
          income-attrs {:id income-id
                        :user-id user-id
                        :inc-category ""
                        :value 0
                        :date time-now
                        :created-at time-now}]
      {:db (assoc-in db [:incomes income-id] income-attrs)
       :navigate-to {:path income-path}})))
(reg-event-fx
  :fetch-incomes
  (fn [&_]
    {:fetch {:path "/incomes/" :on-success [:fetch-incomes-success]}}))

; (reg-event-db
;   :fetch-incomes-success
;   (fn [db [_ col-snap]]
;     (.log js/console col-snap)
;     (let [
;           doc-snaps (.-docs col-snap)
;           incomes (map #(keywordize-keys (.data %)) doc-snaps)
;           ]
;       (.log js/console incomes)
;       (.log js/console (assoc db :incomes incomes))
;       db
;       )
;     )
;   )

(defn index-by
  "Transform a coll to a map with a given key as a lookup value"
  [key coll]
  (->> coll
       (map (juxt key identity))
       (into {})))

(reg-event-db
  :fetch-incomes-success
  (fn [db [_ col-snap]]
    (let [incomes (->> (.-docs col-snap)
                      (map #(.data %))
                      (map #(js->clj %))
                      (map keywordize-keys)
                      vec
                      (index-by :id))]

      (assoc db :incomes incomes))))

