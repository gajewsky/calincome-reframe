(ns app.nav.views.authenticated
  (:require [re-frame.core :as rf]
            [app.router :as router]
            [app.nav.views.nav-item :refer [nav-item]]
            [reagent-material-ui.core.tabs :refer [tabs]]
            [reagent-material-ui.core.box :refer [box]]))

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
                   {:id :bills
                    :name "Bills"
                    :href (router/path-for :bills)
                    :dispatch #(rf/dispatch [:set-active-nav :bills])}
                   {:id :categories
                    :name "Categories"
                    :href (router/path-for :categories)
                    :dispatch #(rf/dispatch [:set-active-nav :categories])}
                   {:id :vendors
                    :name "Vendors"
                    :href (router/path-for :vendors)
                    :dispatch #(rf/dispatch [:set-active-nav :vendors])}]]
    [box {:display "flex"
          :justify-content "flex-end"
          :py 1}
     [tabs
      {:indicator-color "primary"
       :text-color "primary"
       :value false}
      (for [{:keys [id name href dispatch]} nav-items]
        [nav-item {:key id
                   :id id
                   :name name
                   :href href
                   :dispatch dispatch
                   :active-page active-page}])]]))
