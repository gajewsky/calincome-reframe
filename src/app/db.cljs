(ns app.db
  (:require [re-frame.core :as rf]))

(def initial-app-db {:auth {:uid nil}
                     :errors {}
                     :nav {:active-page :dashboard
                           :active-nav :dashboard }
                     :bill {:bill-01 {:id :rec-01
                                         :divide? false
                                         :contractor-id 1
                                         :user-id 1
                                         :date "2020-01-03"
                                         :created-at "2020-01-01"
                                         :expenses {:exp-01 {:id :exp-01
                                                             :description "Pasta"
                                                             :value 203
                                                             :subcategory-id 1
                                                             :track? false }
                                                    :exp-02 {:id :exp-02
                                                             :description "Pesto"
                                                             :value 432
                                                             :subcategory-id 1
                                                             :track? false }
                                                    :exp-03 {:id :exp-02
                                                             :description "Bread"
                                                             :value 1222
                                                             :subcategory-id 1
                                                             :track? false }}}}
                     :users {"admin@example.com" {:uid "admin@example.com"
                                                  :profile {:first-name "Admin"
                                                            :last-name "Admin"
                                                            :email "admin@example.com"
                                                            :password "password"}
                                                  :role :user }}})

(rf/reg-event-db
  :initialize-db
  (fn [_ _]
    initial-app-db))
