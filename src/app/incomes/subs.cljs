(ns app.incomes.subs
  (:require [re-frame.core :refer [reg-sub]]))

(reg-sub
 :incomes
 (fn [db _]
   (vals (get-in db [:incomes]))))

(reg-sub
 :income
 (fn [db _]
   (let [active-income (get-in db [:nav :active-income])]
     (get-in db [:incomes active-income]))))

