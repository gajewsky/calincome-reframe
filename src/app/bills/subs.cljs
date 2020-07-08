(ns app.bills.subs
  (:require [re-frame.core :refer [reg-sub]]))

(reg-sub
 :bills
 (fn [db _]
   (vals (get-in db [:bills]))))

(reg-sub
 :bill
 (fn [db _]
   (let [active-bill (get-in db [:nav :active-bill])]
     (get-in db [:bills active-bill]))))

(reg-sub
  :expenses
  (fn [db _]
    (let [active-bill (get-in db [:nav :active-bill]) ]
      (get-in db [:bills active-bill :expenses]))))
