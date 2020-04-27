
(ns app.components.form-group
  (:require ["@material-ui/core" :refer [FormGroup TextField]]))

(defn form-group
  [{:keys [id label type values]}]
  [:> FormGroup
   [:> TextField { :id id
              :type type
              :label label
              :variant "outlined"
              :value (id @values)
              :on-change #(swap! values assoc id (.. % -target -value))}]])
