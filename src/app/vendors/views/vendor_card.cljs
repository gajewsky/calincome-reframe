(ns app.vendors.views.vendor-card
  (:require [app.router :as router]
            [re-frame.core :as rf]))

(defn vendor-card
  [{:keys [id name]}]
  [:div
   {:key id}
   [:a {:href (router/path-for :vendor :vendor-id id)} name]

   [:button {:on-click #(when (js/confirm "Are you sure?")
                          (rf/dispatch [:delete-vendor id]))}
    "Delete"]])
