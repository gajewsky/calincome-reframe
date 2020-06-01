(ns app.vendors.views.vendor-list
  (:require [app.vendors.views.vendor-card :refer [vendor-card]]))

(defn vendor-list
  [vendors]
  [:div {:class "cards"}
   (for [vendor vendors]
     ^{:key (:id vendor)}
     [vendor-card vendor])])

