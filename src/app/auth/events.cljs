(ns app.auth.events
  (:require [re-frame.core :refer [reg-event-fx after reg-cofx]]
            [cljs.reader :refer [read-string]]))

(def calincome-user-key "calincome-user")

(defn set-user-ls!
  [{:keys [auth]}]
  (when (:uid auth) (.setItem js/localStorage calincome-user-key (str auth))))

(defn remove-user-ls!
  []
  (.removeItem js/localStorage calincome-user-key))

(def set-user-interceptors [(after set-user-ls!)])
(def remove-user-interceptors [(after remove-user-ls!)])

(reg-cofx
  :local-store-user
  (fn [cofx _]
    (assoc cofx :local-store-user
           (read-string
             (.getItem js/localStorage calincome-user-key)))))

(reg-event-fx
  :sign-up
  set-user-interceptors
  (fn [{:keys [db]} [_ {:keys [first-name last-name email password]}]]
    {:db (-> db
             (assoc-in [:auth :uid] email)
             (assoc-in [:users email] {:id email
                                       :profile {:first-name first-name
                                                 :last-name last-name
                                                 :email email
                                                 :password password
                                                 :img "img/avatar.svg"}}))
     :dispatch [:set-active-nav :dashboard]
     :navigate-to {:path "/dashboard"}}))

(reg-event-fx
  :log-in
  set-user-interceptors
  (fn [{:keys [db]} [_ {:keys [email password]}]]
    (let [user (get-in db [:users email])
          correct-password? (= (get-in user [:profile :password]) password)]
      (cond
        (not user)
        {:db (assoc-in db [:errors :email] "User not found")}

        (not correct-password?)
        {:db (assoc-in db [:errors :email] "Wrong password")}

        correct-password?
        {:db (-> db
                 (assoc-in [:auth :uid] email)
                 (update-in [:errors] dissoc :email))
         :dispatch [:set-active-nav :dashboard]
         :navigate-to {:path "/dashboard"}}))))

(reg-event-fx
  :log-out
  remove-user-interceptors
  (fn [{:keys [db]} _]
    {:db (assoc-in db [:auth :uid] nil)
     :dispatch [:set-active-nav :log-in]
     :navigate-to {:path "/log-in"}}))
