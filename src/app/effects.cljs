(ns app.effects
  (:require [re-frame.core :refer [reg-fx dispatch]]
            ["firebase/app" :as firebase]))

(reg-fx
  :persist
  (fn [{:keys [path attrs]}]
    (let [firestore (.firestore firebase)
          doc-ref (.doc firestore path)]
      (.set doc-ref (clj->js attrs) #js {:merge true}))))

(reg-fx
  :persist-delete
  (fn [{:keys [path]}]
    (let [firestore (.firestore firebase)
          doc-ref (.doc firestore path)]
      (.delete doc-ref))))

(reg-fx
  :fetch
  (fn [{:keys [path on-success]}]
    (let [firestore (.firestore firebase)
          col-ref (.collection firestore path)]
      (-> (.get col-ref)
          (.then (fn [col-snap] (dispatch (conj on-success col-snap))))))))
