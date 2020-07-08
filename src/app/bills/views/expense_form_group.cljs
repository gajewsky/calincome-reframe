(ns app.bills.views.expense-form-group
  (:require
    [reagent-material-ui.core.box :refer [box]]
    [reagent-material-ui.core.text-field :refer [text-field]]))

(defn expense-form-group
  [{:keys [field-id label type values expense-id expenses]}]
  (let[input-style {:margin "10px 0px"}
       update (fn [new-value]
                (let [updated-values (assoc values field-id new-value)]
                  (swap! expenses assoc expense-id updated-values)))]
    [text-field {:name field-id
                 :full-width true
                 :id field-id
                 :label label
                 :margin "normal"
                 :required true
                 :type type
                 :style input-style
                 :variant "outlined"
                 :value (field-id values)
                 :on-change #(update (.. % -target -value))}]))
