(ns app.nav.views.nav-item
  (:require ["rebass" :refer [Link]]))

(defn nav-item
  [{:keys [id href name dispatch active-nav]}]
  [:> Link {:key id
           :variant "nav"
           :href href
           :on-click dispatch
           :ml 2
           :pb 10
           :border-bottom (when (= active-nav id) "2px solid #102A43")}
   name])

