(ns app.theme
  (:require ["@smooth-ui/core-sc" :refer [theme]]))

(def calincome-theme (merge
                       (js->clj theme :keywordize-keys true)
                       {:primary "#312e99"}))


