(ns app.header.views.authenticated
  (:require [re-frame.core :as rf]
            [app.router :as router]
            [reagent-material-ui.core.link :refer [link]]
            [reagent-material-ui.core.avatar :refer [avatar]]
            [reagent-material-ui.core.button :refer [button]]))

(defn authenticated
  []
  (let [current-user-id (:uid @(rf/subscribe [:current-user]))
        add-bill #(rf/dispatch [:create-bill current-user-id])
        add-income #(rf/dispatch [:create-income current-user-id])
        button-style {:color "white"
                      :border "2px solid white"
                      :border-radius "10px"}]
    [:<>
     [button {:on-click #(add-bill)
              :variant "outlined"
              :style button-style} "ADD EXPENSE"]
     [button {:on-click #(add-income)
              :variant "outlined"
              :style button-style} "ADD INCOME"]
     [link {:href (router/path-for :profile)
            :on-click #(rf/dispatch [:set-active-nav :profile])
            :cursor "pointer"}
      [avatar
       {:src "https://placeimg.com/300/300/animals"}]]]))

