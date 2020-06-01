(ns app.vendors.views.vendor-editor
  (:require [re-frame.core :as rf]
            [reagent.core :as r]
            [app.components.form-group :refer [form-group]]))

(defn vendor-editor
  []
  (let [vendor @(rf/subscribe [:vendor])
        values (r/atom vendor)
        save (fn [{:keys [id name description category revolut-id]}]
               (rf/dispatch [:update-vendor {:id id
                                             :name name
                                             :description description
                                             :category category
                                             :revolut-id revolut-id }]))]
    [:<>
     [form-group {:id :name
                  :label "Name"
                  :type "text"
                  :values values}]
     [form-group {:id :category
                  :label "Category"
                  :type "text"
                  :values values}]
     [form-group {:id :description
                  :label "Description"
                  :type "text"
                  :values values}]
     [form-group {:id :revolut-id
                  :label "Revolut Id"
                  :type "text"
                  :values values}]
     [:button {:on-click #(save @values)} "Save"]]))

