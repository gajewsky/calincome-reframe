(ns app.header.views.nav
  (:require [re-frame.core :as rf]
            [app.header.views.public :refer [public]]
            [app.header.views.authenticated :refer [authenticated]]))

(defn nav
  []
  (let [user @(rf/subscribe [:logged-in?])]
    (if user
      [authenticated]
      [public])))
