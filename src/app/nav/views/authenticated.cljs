(ns app.nav.views.authenticated
  (:require [re-frame.core :as rf]
            [app.router :as router]
            [app.nav.views.nav-item :refer [nav-item]]
            ["@smooth-ui/core-sc" :refer [Box Button]]))

(defn authenticated
  []
  (let [active-page @(rf/subscribe [:active-page])
        nav-items [{:id :dashboard
                    :name "Dashboard"
                    :href (router/path-for :dashboard)
                    :dispatch #(rf/dispatch [:set-active-nav :dashboard])}
                   {:id :incomes
                    :name "Incomes"
                    :href (router/path-for :incomes)
                    :dispatch #(rf/dispatch [:set-active-nav :incomes])}
                   {:id :expenses
                    :name "Expenses"
                    :href (router/path-for :expenses)
                    :dispatch #(rf/dispatch [:set-active-nav :expenses])}
                   {:id :categories
                    :name "Categories"
                    :href (router/path-for :categories)
                    :dispatch #(rf/dispatch [:set-active-nav :categories])}
                   {:id :vendors
                    :name "Vendors"
                    :href (router/path-for :vendors)
                    :dispatch #(rf/dispatch [:set-active-nav :vendors])}
                   {:id :profile
                    :name "Profile"
                    :href (router/path-for :profile)
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
                  :active-page active-page}])
     [:> Button { :on-click #(rf/dispatch [:log-out])} "Logout"]]))

