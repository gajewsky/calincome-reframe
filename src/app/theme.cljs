(ns app.theme
  (:require ["@rebass/preset" :refer [preset]]))

(def calincome-theme (merge
                       (js->clj preset :keywordize-keys true)
                       {}))


