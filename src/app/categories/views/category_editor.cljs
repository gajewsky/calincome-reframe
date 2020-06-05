(ns app.categories.views.category-editor
  (:require [re-frame.core :as rf]
            [reagent.core :as r]
            [app.components.form-group :refer [form-group]]))

(defn category-editor
  []
  (let [category @(rf/subscribe [:category])
        values (r/atom category)
        save (fn [{:keys [id group name description]}]
               (rf/dispatch [:update-category {:id id
                                               :group group
                                               :name name
                                               :description description}]))]
    [:<>
     [form-group {:id :group
                  :label "Group"
                  :type "text"
                  :values values}]
     [form-group {:id :name
                  :label "Name"
                  :type "text"
                  :values values}]
     [form-group {:id :description
                  :label "Description"
                  :type "text"
                  :values values}]
     [:button {:on-click #(save @values)} "Save"]]))

