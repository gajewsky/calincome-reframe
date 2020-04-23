(ns app.bills.views.bills-page
  (:require [re-frame.core :as rf]
            [app.bills.views.bill-list :refer [bill-list]]
            ["@smooth-ui/core-sc" :refer [Typography]]))

(defn bills-page
  []
  (let [bills @(rf/subscribe [:bills])
        logged-in? @(rf/subscribe [:logged-in?])]

    [:<>
     (when logged-in?
       [:<>
        [:> Typography {:variant "h4"
                        :py 20
                        :font-weight 700}
         "bills"]
        [bill-list bills]])]))
