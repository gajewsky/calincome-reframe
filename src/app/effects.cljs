(ns app.effects
  (:require [re-frame.core :refer [reg-fx]]
            ["firebase/app" :as firebase]))

(def firestore
  (.firestore firebase))

(reg-fx
  :persist
  (fn [{:keys [path attrs]}]
    (let [fs-ref (.doc firestore path)]
      (.set fs-ref (clj->js attrs) #js {:merge true}))))
