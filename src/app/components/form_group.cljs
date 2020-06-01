(ns app.components.form-group
  (:require
    [reagent-material-ui.core.box :refer [box]]
    [reagent-material-ui.core.text-field :refer [text-field]]))

(defn form-group
  [{:keys [id label type values]}]
  (let[input-style {:margin "10px 0px"}]
    [text-field {:name id
                 :full-width true
                 :id id
                 :label label
                 :margin "normal"
                 :required true
                 :type type
                 :style input-style
                 :variant "outlined"
                 :value (id @values)
                 :on-change #(swap! values assoc id (.. % -target -value))}]))
