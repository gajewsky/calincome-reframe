(ns app.fb.db
  (:require [re-frame.core :refer [reg-fx dispatch]]
            [clojure.walk :refer [keywordize-keys]]
            ["firebase/app" :as firebase]
            [clojure.string :as str]))

(defn colsnap->maps
  [col-snap]
  (->> (.-docs col-snap)
       (map #(.data %))
       (map #(js->clj %))
       (map keywordize-keys)
       vec))

(reg-fx
  :firestore/save
  (fn [{:keys [path attrs]}]
    (let [firestore (.firestore firebase)
          doc-ref (.doc firestore path)]
      (.set doc-ref (clj->js attrs) #js {:merge true}))))

(reg-fx
  :firestore/delete
  (fn [{:keys [path]}]
    (let [firestore (.firestore firebase)
          doc-ref (.doc firestore path)]
      (.delete doc-ref))))

(reg-fx
  :firestore/get-col
  (fn [{:keys [path on-success]}]
    (let [firestore (.firestore firebase)
          col-ref (.collection firestore path)]
      (-> (.get col-ref)
          (.then (fn [col-snap]
                   (let [event (->> (colsnap->maps col-snap)
                                    (conj on-success))]
                     (dispatch event))))))))



