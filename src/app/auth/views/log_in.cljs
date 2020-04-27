(ns app.auth.views.log-in
  (:require [reagent.core :as r]
            [re-frame.core :as rf]
            [app.components.form-group :refer [form-group]]
            ["rebass" :refer [Flex Box Button]]))

(defn log-in
  []
  (let [initial-values {:email "" :password ""}
        values (r/atom initial-values)]
    (fn []
      [:> Box {:as "form" :py 3 }

       [:> Flex
        {:mx (- 2) :mb 3}
        [:> Box {:width (/ 1 2) :px 2}
         [form-group {:id :email
                      :label "Email"
                      :type "email"
                      :values values}]
         [form-group {:id :password
                      :label "Password"
                      :type "password"
                      :values values}]]

        [:> Box {:display "flex"
                 :justify-content "space-between"}
         [:> Box {:py 1
                  :pr 2}
          [:a {:href "#sign-up"
               :on-click #(rf/dispatch [:set-active-nav :sign-up])}
           "Need an account? Register!"]]
         [:> Box
          [:> Button {:on-click #(rf/dispatch [:log-in @values])}
           "Log in"]]]]])))
