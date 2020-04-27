(ns app.theme
  (:require ["@material-ui/core/styles" :refer [createMuiTheme]]))

(def calincome-theme
                       (js->clj createMuiTheme :keywordize-keys true)
)


