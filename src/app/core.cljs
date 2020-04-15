(ns app.core
  (:require [reagent.dom :as r]))

(defn app
  []
  [:div "Calincome"])

(defn ^:dev/after-load start
  []
  (r/render [app]
    (.getElementById js/document "app")))

(defn ^:export init
  []
  (start))
