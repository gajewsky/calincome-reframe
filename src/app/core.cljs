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
            [app.savings.views.savings :refer [savings]]
            [app.categories.views.categories :refer [categories]]
            [app.vendors.views.vendors :refer [vendors]]
            [app.warranties.views.warranties :refer [warranties]]
            ;; -- bills --
            [app.bills.views.bills-page :refer [bills-page]]
            [app.bills.views.bill-editor :refer [bill-editor]]
            [app.bills.subs]
            [app.bills.events]
            ;; -- incomes --
            [app.incomes.views.incomes-page :refer [incomes-page]]
            [app.incomes.views.income-editor :refer [income-editor]]
            [app.incomes.subs]
            [app.incomes.events]
            ;; -- nav --
            [app.nav.views.nav :refer [nav]]
            [app.header.views.header :refer [header]]
            [app.nav.events]
            [app.nav.subs]
            [app.theme :refer [calincome-theme]]
            ;; -- ui components --
            [reagent-material-ui.core.drawer :refer [drawer]]
            [reagent-material-ui.core.container :refer [container]]
            [reagent-material-ui.styles :as styles]
            [reagent-material-ui.core.css-baseline :refer [css-baseline]]))

(defn pages
  [page-name]
  (case page-name
    :sign-up [sign-up]
    :log-in [log-in]
    :dashboard [dashboard]
    :incomes [incomes-page]
    :income [income-editor]
    :bills [bills-page]
    :bill [bill-editor]
    :savings [savings]
    :categories [categories]
    :vendors [vendors]
    :warranties [warranties]
    :profile [profile]
    [dashboard]))

(defn app
  []
  (let [active-page @(rf/subscribe [:active-page])]
    [:<>
     [css-baseline]
     [styles/theme-provider (styles/create-mui-theme calincome-theme)
      [header]
      [container
       [nav]
       [container
        [pages active-page]]]]]))

(defn ^:dev/after-load start
  []
  (r/render [app]
            (.getElementById js/document "app")))

(defn ^:export init
  []
  (router/start!)
  (rf/dispatch-sync [:initialize-db])
  (start))
