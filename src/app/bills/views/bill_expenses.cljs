(ns app.bills.views.bill-expenses
  (:require [re-frame.core :as rf]
            [reagent.core :as r]
            [app.bills.views.expense-form-group :refer [expense-form-group]]
            [nano-id.core :refer [nano-id]]))

(defn bill-expenses
  [expenses]
  (fn []
    (let [bill-id (:id @(rf/subscribe [:bill]))
          initial-values {:id nil :description "" :value 0 :subcategory-id "" :track? false}
          values (r/atom initial-values)
          add-exp (fn [{:keys [id description value subcategory-id track?]}]
                    (let [uniq-id (or id (keyword (nano-id 10)))]
                      (swap! expenses assoc uniq-id {:id (name uniq-id)
                                                     :bill-id bill-id                                                    :description description
                                                     :value (js/parseInt value)
                                                     :subcategory-id subcategory-id
                                                     :track? (boolean track?)})
                      (reset! values initial-values)))

          remove-exp (fn [expense-id] (swap! expenses dissoc expense-id))]

      [:div {:class "cards"}
       [:button {:on-click #(add-exp @values)} "Add"]
       (for [expense @expenses]
         (let [values (val expense)
               id (key expense)]

           ^{:key id}

           [:<>
            [expense-form-group {:field-id :description
                                 :label "Description"
                                 :type "text"
                                 :values values
                                 :expense-id id
                                 :expenses expenses}]
            [expense-form-group {:field-id :value
                                 :label "Value"
                                 :type "number"
                                 :values values
                                 :expense-id id
                                 :expenses expenses}]
            [expense-form-group {:field-id :subcategory-id
                                 :label "Category"
                                 :type "text"
                                 :values values
                                 :expense-id id
                                 :expenses expenses}]
            [expense-form-group {:field-id :track?
                                 :label "Track?"
                                 :type "text"
                                 :values values
                                 :expense-id id
                                 :expenses expenses}]
            [:button {:on-click #(when (js/confirm "Are you sure?")
                                   (remove-exp id))}
             "Delete"]]))])))

