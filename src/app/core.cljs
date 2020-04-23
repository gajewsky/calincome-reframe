(ns app.core
  (:require [reagent.dom :as r]
            [re-frame.core :as rf]
            [app.db]
            [app.router :as router]
            ;; -- auth --
            [app.auth.views.sign-up :refer [sign-up]]
            [app.auth.views.log-in :refer [log-in]]
            [app.auth.views.profile :refer [profile]]
            [app.auth.events]
            [app.auth.subs]
            ;; -- pages --
            [app.dashboard.views.dashboard :refer [dashboard]]
            [app.incomes.views.incomes :refer [incomes]]
            [app.savings.views.savings :refer [savings]]
            [app.categories.views.categories :refer [categories]]
            [app.vendors.views.vendors :refer [vendors]]
            [app.warranties.views.warranties :refer [warranties]]
            ;; -- bills --
            [app.bills.views.bills-page :refer [bills-page]]
            [app.bills.subs]
            ;; -- nav ---
            [app.nav.views.nav :refer [nav]]
            [app.nav.events]
            [app.nav.subs]
            [app.theme :refer [calincome-theme]]
            ["@smooth-ui/core-sc" :refer [Normalize ThemeProvider Grid Row Col]]))

(defn pages
  [page-name]
  (case page-name
    :sign-up [sign-up]
    :log-in [log-in]
    :dashboard [dashboard]
    :incomes [incomes]
    :bills [bills-page]
    :savings [savings]
    :categories [categories]
    :vendors [vendors]
    :warranties [warranties]
    :profile [profile]
    [dashboard]))

(defn app
  []
  (let [active-nav @(rf/subscribe [:active-nav])]
    [:<>
     [:> Normalize]
     [:> ThemeProvider {:theme calincome-theme}
      [:> Grid {:fluid false}
       [:> Row
        [:> Col
         [nav]
         [pages active-nav]]]]]]))


(defn ^:dev/after-load start
  []
  (r/render [app]
            (.getElementById js/document "app")))

(defn ^:export init
  []
  (router/start!)
  (rf/dispatch-sync [:initialize-db])
  (start))
