(ns app.auth.subs
  (:require [re-frame.core :refer [reg-sub]]))

(reg-sub
 :logged-in?
 (fn [db _]
   (boolean (get-in db [:auth :uid]))))

(reg-sub
 :current-user
 (fn [db _]
   (let [logged-user-id (get-in db [:auth :uid])]
     (get-in db [:users logged-user-id]))))
