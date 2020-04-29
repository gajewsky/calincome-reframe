(ns app.bills.views.bill-expenses
  (:require [re-frame.core :as rf]
            [reagent.core :as r]
            [app.components.form-group :refer [form-group]]))

(defn bill-expenses
  []
  (fn []
    (let [expenses @(rf/subscribe [:expenses])]
      [:div {:class "cards"}
       (for [{:keys [id] :as initial-values} expenses]
         (let [values (r/atom initial-values)
               save (fn [{:keys [id description value subcategory-id track?]}]
                      (rf/dispatch [:upsert-expense {:id (or id (keyword (str "exp-" (random-uuid))))
                                                     :description description
                                                     :value (js/parseInt value)
                                                     :subcategory-id subcategory-id
                                                     :track? (boolean track?)}])
                      (reset! values initial-values))]

           ^{:key id}

           [:<>
            [form-group {:id :description
                         :label "Description"
                         :type "text"
                         :values values}]
            [form-group {:id :value
                         :label "Value"
                         :type "number"
                         :values values}]
            [form-group {:id :subcategory-id
                         :label "Category"
                         :type "text"
                         :values values}]
            [form-group {:id :track?
                         :label "Track?"
                         :type "text"
                         :values values}]
            [:button {:on-click #(save @values)} "Save"]
            [:button {:on-click #(when (js/confirm "Are you sure?")
                                   (rf/dispatch [:delete-expense id]))}
             "Delete"]]))])))

