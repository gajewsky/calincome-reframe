(ns app.core
  (:require [reagent.dom :as r]
            [re-frame.core :as rf]
            [app.db]
            [app.theme :refer [calincome-theme]]
            ["@smooth-ui/core-sc" :refer [Normalize ThemeProvider Button]]))

(defn app
  []
  [:<>
   [:> Normalize]
   [:> ThemeProvider {:theme calincome-theme}
    [:> Button "hello!"]]])

(defn ^:dev/after-load start
  []
  (rf/dispatch-sync [:initialize-db])
  (r/render [app]
    (.getElementById js/document "app")))

(defn ^:export init
  []
  (start))
