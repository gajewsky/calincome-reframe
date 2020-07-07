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
  (fn [{:keys [ref doc-batch]}]
    (async/go
      (let [new-exp-ids (keys (:expenses doc-batch))
            bill (:bill doc-batch)
            bill-ref [:bills (name (:id bill))]
            expenses (:expenses doc-batch)
            batch (firestore/create-batch)]

        ; (.log js/console "expenses" expenses)
        ; (.log js/console "expenses" expenses)
        ; (firestore/set-db! bill-ref bill {:batch batch})
        ; (firestore/set-db! [:expenses "dupa1"] {:value 1} {:batch batch})
        ; (firestore/set-db! [:expenses "dupa2"] {:value 1} {:batch batch})
        ; (async/<! (firestore/commit-batch! batch))
        )
      )


    ))

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
