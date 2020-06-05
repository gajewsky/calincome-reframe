(ns app.categories.views.category-list
  (:require [app.categories.views.category-card :refer [category-card]]))

(defn category-list
  [categories]
  [:div {:class "cards"}
   (for [category categories]
     ^{:key (:id category)}
     [category-card category])])

