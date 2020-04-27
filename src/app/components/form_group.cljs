(ns app.components.form-group
  (:require
    ["rebass" :refer [Box]]
    ["@rebass/forms" :refer [Label Input]]))

(defn form-group
  [{:keys [id label type values]}]
  [:> Box
   {:width (/ 1 2) :px 2}
   [:> Label {:html-for id} label]
   [:> Input {:name id
              :control true
              :type type
              :value (id @values)
              :on-change #(swap! values assoc id (.. % -target -value))}]])
