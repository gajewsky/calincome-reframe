(ns app.utils
  (:require [reagent-material-ui.styles :as styles]))

(defn make-styles [f]
  (let [mk-fn (styles/make-styles
                (fn [theme]
                  (clj->js (f theme))))]
    (fn [& args]
      (js->clj (apply mk-fn args) :keywordize-keys true))))
