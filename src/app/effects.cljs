(ns app.effects
  (:require [re-frame.core :refer [reg-fx dispatch]]
            ["firebase/app" :as firebase]))

(def firestore
  (.firestore firebase))
(.log js/console "dupa")
(reg-fx
  :persist
  (fn [{:keys [path attrs]}]
    (let [doc-ref (.doc firestore path)]
      (.set doc-ref (clj->js attrs) #js {:merge true}))))

; (.log js/console "dupa2")
(reg-fx
  :fetch
  (fn [{:keys [path on-success]}]

    (let [col-ref (.collection firestore path)]
      (-> (.get col-ref)
          (.then (fn [col-snap] (dispatch (conj on-success col-snap))))))))
