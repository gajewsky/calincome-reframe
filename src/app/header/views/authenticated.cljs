(ns app.header.views.authenticated
  (:require [re-frame.core :as rf]
            [app.router :as router]
            ["rebass" :refer [Link Image Button]]))

(defn authenticated
  []
  [:<>
  [:> Button {:on-click #(rf/dispatch [:log-out])
           :ml 2
           :pb 10
           :variant "outline"
           :sx {:cursor "pointer"}} "Logout"]
   [:> Link {:variant "nav"
             :href (router/path-for :profile)
             :on-click #(rf/dispatch [:set-active-nav :profile])
             :cursor "pointer"}
    [:> Image
     {:src "https://placeimg.com/300/300/animals"
      :sx {:width 48, :height 48, :border-radius 9999}}]]])

