(ns app.auth.views.log-in
  (:require [reagent.core :as r]
            [re-frame.core :as rf]
            [app.components.form-group :refer [form-group]]
            [reagent-material-ui.core.container :refer [container]]
            [reagent-material-ui.core.avatar :refer [avatar]]
            [reagent-material-ui.core.css-baseline :refer [css-baseline]]
            [reagent-material-ui.core.typography :refer [typography]]
            [reagent-material-ui.core.button :refer [button]]
            [reagent-material-ui.core.grid :refer [grid]]
            [reagent-material-ui.core.box :refer [box]]
            [reagent-material-ui.core.link :refer [link]]))

(defn log-in
  []
  (let [initial-values {:email "" :password ""}
        values (r/atom initial-values)
        paper-class {:margin-top "10px"
                     :display "flex"
                     :flex-direction "column"
                     :align-items "center"}]
    (fn []
      [container {:component "main"
                  :max-width "xs"}
       [css-baseline]
       [:div {:style paper-class}
        [avatar]
        [typography {:component "h1"
                     :variant "h5"} "Log in"]
        [:div
         [form-group {:id :email
                      :label "Email"
                      :type "email"
                      :values values}]
         [form-group {:id :password
                      :label "Password"
                      :type "password"
                      :values values}]
         [button {:type "submit"
                  :full-width "true"
                  :variant "contained"
                  :color "secondary"
                  :on-click #(rf/dispatch [:log-in @values])}
          "Log in"]
         [grid {:container "true"}
          [grid {:item "true"}
           [:a {:href "#sign-up"
                :on-click #(rf/dispatch [:set-active-nav :sign-up])}
            "Need an account? Register!"]]]]]
       [box {:mt "8"}
        [typography
         {:variant "body2"
          :color "textSecondary"
          :align "center"}
         "Copyright © "
         [link
          {:color "inherit"
           :href "https://github.com/gajewsky/calincome-reframe"}
          "Calincome 2020"]]]])))
