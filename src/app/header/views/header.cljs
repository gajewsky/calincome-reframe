(ns app.header.views.header
  (:require
    [app.header.views.nav :refer [nav]]
    ["rebass" :refer [Flex Text Box]]))

(defn header
  []
  [:> Flex
   {:px 2 :align-items "center"}
   [:> Text {:p 2, :font-weight "bold"} "Calincome"]
   [:> Box {:mx "auto"}]
   [nav]])
