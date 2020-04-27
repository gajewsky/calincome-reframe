(ns app.nav.views.nav-item
  (:require ["@material-ui/core" :refer [Box]]))

(defn nav-item
  [{:keys [id href name dispatch active-nav]}]
  [:> Box {:key id
           :as "a"
           :href href
           :on-click dispatch
           :ml 2
           :pb 10
           :border-bottom (when (= active-nav id) "2px solid #102A43")}
   name])

