(ns app.db
  (:require [re-frame.core :as rf]))

(def initial-app-db {:auth {:uid nil}
                     :errors {}
                     :nav {:active-page :dashboard
                           :active-nav :dashboard
                           :active-bill nil
                           :active-income nil
                           :active-vendor nil
                           :active-category nil}

                     :bills {}

                     :incomes {}

                     :vendors {}

                     :categories {}

                     :users {"admin@example.com" {:uid "admin@example.com"
                                                  :profile {:first-name "Admin"
                                                            :last-name "Admin"
                                                            :email "admin@example.com"
                                                            :password "password"}
                                                  :role :user }}})

(rf/reg-event-fx
  :initialize-db
  [(rf/inject-cofx :local-store-user)]
  (fn [{:keys [local-store-user]} _]
    {:db (assoc-in initial-app-db [:auth] local-store-user)}))

