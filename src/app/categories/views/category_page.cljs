(ns app.categories.views.category-page
  (:require [re-frame.core :as rf]
            [app.categories.views.category-list :refer [category-list]]
            [reagent-material-ui.core.typography :refer [typography]]
            [reagent-material-ui.core.button :refer [button]]))

(defn category-page
  []
  (let [categories @(rf/subscribe [:categories])
        logged-in? @(rf/subscribe [:logged-in?])
        save #(rf/dispatch [:create-category])]

    [:<>
     (when logged-in?
       [:<>
        [button {:on-click #(save)
                 :color "secondary"
                 :variant "contained"} "Add new"]
        [typography {:variant "h4"} "categories"]
        [category-list categories]])]))

