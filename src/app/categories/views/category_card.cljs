(ns app.categories.views.category-card
  (:require [app.router :as router]
            [re-frame.core :as rf]))

(defn category-card
  [{:keys [id name]}]
  [:div
   {:key id}
   [:a {:href (router/path-for :category :category-id id)} name]

   [:button {:on-click #(when (js/confirm "Are you sure?")
                          (rf/dispatch [:delete-category id]))}
    "Delete"]])
