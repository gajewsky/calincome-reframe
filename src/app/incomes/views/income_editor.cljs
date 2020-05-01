(ns app.incomes.views.income-editor
  (:require [re-frame.core :as rf]
            [reagent.core :as r]
            [app.components.form-group :refer [form-group]]))

(defn income-editor
  []
  (let [income @(rf/subscribe [:income])
        values (r/atom income)
        save (fn [{:keys [id value inc-category user-id date]}]
               (rf/dispatch [:update-income {:id id
                                             :value value
                                             :inc-category inc-category
                                             :user-id user-id
                                             :date date }]))]
    [:<>
     [form-group {:id :value
                  :label "Value"
                  :type "text"
                  :values values}]
     [form-group {:id :inc-category
                  :label "Category"
                  :type "text"
                  :values values}]
     [form-group {:id :user-id
                  :label "User"
                  :type "text"
                  :values values}]
     [form-group {:id :date
                  :label "Date"
                  :type "text"
                  :values values}]
     [:button {:on-click #(save @values)} "Save"] ]))

