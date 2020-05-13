(ns app.auth.views.sign-up
  (:require [reagent.core :as r] [re-frame.core :as rf]
            [app.components.form-group :refer [form-group]]
            [reagent-material-ui.core.container :refer [container]]
            [reagent-material-ui.core.avatar :refer [avatar]]
            [reagent-material-ui.core.css-baseline :refer [css-baseline]]
            [reagent-material-ui.core.typography :refer [typography]]
            [reagent-material-ui.core.button :refer [button]]
            [reagent-material-ui.core.grid :refer [grid]]
            [reagent-material-ui.core.box :refer [box]]))

(defn sign-up
  []
  (let [initial-values {:first-name "" :last-name "" :email "" :password ""}
        values (r/atom initial-values)
        paper-class {:margin-top "10px"
                     :display "flex"
                     :flex-direction "column"
                     :align-items "center"}]
    (fn []
      [container {:component "main" :max-width "xs"}
       [css-baseline]
       [:div {:style paper-class}
        [avatar]
        [typography {:component "h1" :variant "h5"} "Sign up"]
        [:div
         [grid {:container "true" :spacing 2}
          [grid {:item "true" :xs 12 :sm 6}
           [form-group {:id :first-name
                        :label "First name"
                        :type "text"
                        :values values}]]

          [grid {:item "true" :xs 12 :sm 6}
           [form-group {:id :last-name
                        :label "Last name"
                        :type "text"
                        :values values}]]

          [grid {:item "true" :xs 12}
           [form-group {:id :email
                        :label "Email"
                        :type "email"
                        :values values}]]

          [grid {:item "true" :xs 12}
           [form-group {:id :password
                        :label "Password"
                        :type "password"
                        :values values}]]]

         [button {:type "submit"
                  :full-width "true"
                  :variant "contained"
                  :color "primary"
                  :on-click #(rf/dispatch [:sign-up @values])} "Sign up"]

         [grid {:container nil, :justify "flex-end"}
          [grid {:item nil}
           [:a {:href "#log-in"
                :on-click #(rf/dispatch [:set-active-nav :log-in])}
            "Already have an account? Sign in"]]]]]])))



