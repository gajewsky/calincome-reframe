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

                     :bills {:bill-01 {:id :bill-01
                                       :divide? false
                                       :contractor-id 1
                                       :user-id "admin@example.com"
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
                                                           :track? false }}}
                             :bill-02 {:id :bill-02
                                       :divide? false
                                       :contractor-id 2
                                       :user-id "admin@example.com"
                                       :date "2020-01-04"
                                       :created-at "2020-01-01"
                                       :expenses { :exp-03 {:id :exp-02
                                                           :description "Bread"
                                                           :value 1222
                                                           :subcategory-id 1
                                                           :track? false }}}}

                     :incomes {:inc-01 { :id :inc-01
                                        :user-id "admin@example.com"
                                        :date "2020-01-01"
                                        :value 324
                                        :created-at "2020-01-01"
                                        :inc-category "Wypłata"}}

                     :vendors {:ven-01 { :id :ven-01
                                        :name "Biedronka"
                                        :description "description"
                                        :category "Food"
                                        :revolut-id "Biedronka"}}

                     :categories {:cat-01 { :id :cat-01
                                        :group "Food"
                                        :name "Home"
                                        :description "description"}}

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

