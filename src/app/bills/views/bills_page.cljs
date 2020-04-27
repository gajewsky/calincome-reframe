(ns app.bills.views.bills-page
  (:require [re-frame.core :as rf]
            [app.bills.views.bill-list :refer [bill-list]]
            ["@material-ui/core" :refer [Typography]]))

(defn bills-page
  []
  (let [bills @(rf/subscribe [:bills])
        logged-in? @(rf/subscribe [:logged-in?])]

    [:<>
     (when logged-in?
       [:<>
        [:> Typography {:variant "h4"}
         "bills"]
        [bill-list bills]])]))
