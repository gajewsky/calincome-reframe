(ns app.bills.subs
  (:require [re-frame.core :refer [reg-sub]]))

(reg-sub
 :bills
 (fn [db _]
   (vals (get-in db [:bills]))))
