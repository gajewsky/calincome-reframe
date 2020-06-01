(ns app.vendors.views.vendor-card
  (:require [app.router :as router]))

(defn vendor-card
  [{:keys [id name]}]
  [:div
   {:key id}
   [:a {:href (router/path-for :vendor :vendor-id id)} name]])


