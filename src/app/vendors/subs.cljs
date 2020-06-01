(ns app.vendors.subs
  (:require [re-frame.core :refer [reg-sub]]))

(reg-sub
 :vendors
 (fn [db _]
   (vals (get-in db [:vendors]))))

(reg-sub
 :vendor
 (fn [db _]
   (let [active-vendor (get-in db [:nav :active-vendor])]
     (get-in db [:vendors active-vendor]))))

