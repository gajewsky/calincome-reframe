(ns app.utils
  (:require [reagent-material-ui.styles :as styles]))

(defn make-styles [f]
  (let [mk-fn (styles/make-styles
                (fn [theme]
                  (clj->js (f theme))))]
    (fn [& args]
      (js->clj (apply mk-fn args) :keywordize-keys true))))

(defn index-by
  "Transform a coll to a map with a given key as a lookup value"
  [key coll]
  (->> coll
       (map (juxt key identity))
       (into {})))
