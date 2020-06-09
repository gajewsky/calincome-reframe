(ns app.utils
  (:require [reagent-material-ui.styles :as styles]
            [clojure.walk :refer [keywordize-keys]]))

(defn make-styles [f]
  (let [mk-fn (styles/make-styles
                (fn [theme]
                  (clj->js (f theme))))]
    (fn [& args]
      (js->clj (apply mk-fn args) :keywordize-keys true))))

(defn index-by-id
  "Transform a coll to a map with :id as a lookup value"
  [coll]
  (->> coll
       (map (juxt :id identity))
       (into {})
       (keywordize-keys)))
