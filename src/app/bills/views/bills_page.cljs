(ns app.bills.views.bills-page
  (:require [re-frame.core :as rf]
            [app.bills.views.bill-list :refer [bill-list]]
            ["rebass" :refer [Heading]]))

(defn bills-page
  []
  (let [bills @(rf/subscribe [:bills])
        logged-in? @(rf/subscribe [:logged-in?])]

    [:<>
     (when logged-in?
       [:<>
        [:> Heading {:variant "h4"
                        :py 20
                        :font-weight 700}
         "bills"]
        [bill-list bills]])]))
