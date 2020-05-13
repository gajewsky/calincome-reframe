(ns app.auth.views.profile
  (:require [re-frame.core :as rf]
    [reagent-material-ui.core.button :refer [button]]))

(defn profile
  []
  [button {:color "primary"
           :on-click #(rf/dispatch [:log-out])} "Logout"])

