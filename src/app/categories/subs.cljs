(ns app.categories.subs
  (:require [re-frame.core :refer [reg-sub]]))

(reg-sub
 :categories
 (fn [db _]
   (vals (get-in db [:categories]))))

(reg-sub
 :category
 (fn [db _]
   (let [active-category (get-in db [:nav :active-category])]
     (get-in db [:categories active-category]))))

