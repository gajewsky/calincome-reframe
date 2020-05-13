(ns app.header.views.public
  (:require [re-frame.core :as rf]
            [app.router :as router]
            [app.nav.views.nav-item :refer [nav-item]]
            [reagent-material-ui.core.box :refer [box]]))

(defn public
  []
  (let [active-page @(rf/subscribe [:active-page])
        nav-items [{:id :sign-up
                    :name "Sign-up"
                    :href (router/path-for :sign-up)
                    :dispatch #(rf/dispatch [:set-active-nav :sign-up])}
                   {:id :log-in
                    :name "Log-in"
                    :href (router/path-for :log-in)
                    :dispatch #(rf/dispatch [:set-active-nav :log-in])}]]
    [box {:display "flex"}
     (for [{:keys [id name href dispatch]} nav-items]
       [nav-item {:key id
                  :id id
                  :name name
                  :href href
                  :dispatch dispatch
                  :active-page active-page}])]))
