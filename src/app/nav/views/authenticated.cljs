(ns app.nav.views.authenticated
  (:require [re-frame.core :as rf]
            [app.nav.views.nav-item :refer [nav-item]]
            ["@smooth-ui/core-sc" :refer [Box]]))

(defn authenticated
  []
  (let [active-nav @(rf/subscribe [:active-nav])
        nav-items [{:id :dashboard
                    :name "Dashboard"
                    :href "#dashboard"
                    :dispatch #(rf/dispatch [:set-active-nav :dashboard])}
                   {:id :incomes
                    :name "Incomes"
                    :href "#incomes"
                    :dispatch #(rf/dispatch [:set-active-nav :incomes])}
                   {:id :expenses
                    :name "Expenses"
                    :href "#expenses"
                    :dispatch #(rf/dispatch [:set-active-nav :expenses])}
                   {:id :savings
                    :name "Savings"
                    :href "#savings"
                    :dispatch #(rf/dispatch [:set-active-nav :savings])}
                   {:id :categories
                    :name "Categories"
                    :href "#categories"
                    :dispatch #(rf/dispatch [:set-active-nav :categories])}
                   {:id :vendors
                    :name "Vendors"
                    :href "#vendors"
                    :dispatch #(rf/dispatch [:set-active-nav :vendors])}
                   {:id :vendors
                    :name "Vendors"
                    :href "#vendors"
                    :dispatch #(rf/dispatch [:set-active-nav :vendors])}
                   {:id :profile
                    :name "Profile"
                    :href "#profile"
                    :dispatch #(rf/dispatch [:set-active-nav :profile])}]]
    [:> Box {:display "flex"
             :justify-content "flex-end"
             :py 1}
     (for [{:keys [id name href dispatch]} nav-items]
       [nav-item {:key id
                  :id id
                  :name name
                  :href href
                  :dispatch dispatch
                  :active-nav active-nav}])]))

