(ns app.header.views.header
  (:require ["@material-ui/core" :refer [AppBar Toolbar IconButton MenuIcon Typography Button ]]))

(defn header
  []
  [:> AppBar
   {:position "static"}
   [:> Toolbar
    [:> IconButton
     {:edge "start"
      :color "inherit"
      :aria-label "menu"}
     "dupa"]
    [:> Typography
     {:variant "h6"}
     "News"]
    [:> Button {:color "inherit"} "Login"]]]

  )

