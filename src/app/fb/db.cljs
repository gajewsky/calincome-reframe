(ns app.fb.db
  (:require [re-frame.core :refer [reg-fx dispatch]]
            [clojure.walk :refer [keywordize-keys]]
            [app.fb.firestore :as firestore]
            [clojure.core.async :as async]
            [clojure.string :as str]))

(defn colsnap->maps
  [col-snap]
  (->> col-snap
       (map keywordize-keys)
       vec))

(reg-fx
  :firestore/write!
  (fn [{:keys [ref document]}]
    (firestore/set-db! ref document)))

(reg-fx
  :firestore/write-batch!
  (fn [{:keys [doc-batch]}]
    (async/go
      (let [bill (:bill doc-batch)
            bill-ref [:bills (name (:id bill))]
            expenses (:expenses doc-batch)
            batch (firestore/create-batch)]

        (firestore/set-db! bill-ref bill {:batch batch})

        (doall
          (for [exp expenses]
            (let [exp-ref [:expenses (:id exp)]]
              (firestore/set-db! exp-ref exp {:batch batch}))))
        (async/<! (firestore/commit-batch! batch))))))

(reg-fx
  :firestore/delete!
  (fn [{:keys [ref]}]
    (firestore/delete-db! ref)))

(reg-fx
  :firestore/get-col
  (fn [{:keys [ref on-success]}]
    (async/go
      (let [colsnap (async/<! (firestore/get-db ref))
            event (->> (colsnap->maps colsnap)
                       (conj on-success))]
        (dispatch event)))))
