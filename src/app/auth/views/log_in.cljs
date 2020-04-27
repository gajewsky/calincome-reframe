(ns app.auth.views.log-in
  (:require [reagent.core :as r]
            [re-frame.core :as rf]
            [app.components.form-group :refer [form-group]]
            ["@material-ui/core" :refer [Box Button]]))

(defn log-in
  []
  (let [initial-values {:email "" :password ""}
        values (r/atom initial-values)]
    (fn []
      [:> Box
       [:> Box
        [form-group {:id :email
                     :label "Email"
                     :type "email"
                     :values values}]
        [form-group {:id :password
                     :label "Password"
                     :type "password"
                     :values values}]
        [:> Box
         [:> Box
          [:a {:href "#sign-up"
               :on-click #(rf/dispatch [:set-active-nav :sign-up])}
           "Need an account? Register!"]]
         [:> Box
          [:> Button {:on-click #(rf/dispatch [:log-in @values])}
           "Log in"]]]]])))
