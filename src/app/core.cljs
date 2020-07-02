(ns app.core
  (:require [reagent.dom :as r]
            [re-frame.core :as rf]
            [app.db]
            [app.router :as router]
            ;; -- firebase --
            [app.fb.firebase :refer [FB]]
            [app.fb.db]
            ;; -- auth --
            [app.auth.views.sign-up :refer [sign-up]]
            [app.auth.views.log-in :refer [log-in]]
            [app.auth.views.profile :refer [profile]]
            [app.auth.events]
            [app.auth.subs]
            ;; -- pages --
            [app.dashboard.views.dashboard :refer [dashboard]]
            ;; -- bills --
            [app.bills.views.bill-page :refer [bill-page]]
            [app.bills.views.bill-editor :refer [bill-editor]]
            [app.bills.subs]
            [app.bills.events]
            ;; -- incomes --
            [app.incomes.views.income-page :refer [income-page]]
            [app.incomes.views.income-editor :refer [income-editor]]
            [app.incomes.subs]
            [app.incomes.events]
            ;; -- vendors --
            [app.vendors.views.vendor-page :refer [vendor-page]]
            [app.vendors.views.vendor-editor :refer [vendor-editor]]
            [app.vendors.subs]
            [app.vendors.events]
            ;; -- categories --
            [app.categories.views.category-page :refer [category-page]]
            [app.categories.views.category-editor :refer [category-editor]]
            [app.categories.subs]
            [app.categories.events]
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
    :incomes [income-page]
    :income [income-editor]
    :bills [bill-page]
    :bill [bill-editor]
    :categories [category-page]
    :category [category-editor]
    :vendors [vendor-page]
    :vendor [vendor-editor]
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
