(ns app.nav.views.nav
  (:require [re-frame.core :as rf]
            [app.nav.views.public :refer [public]]
            [app.nav.views.authenticated :refer [authenticated]]))

(defn nav
  []
  (let [user @(rf/subscribe [:logged-in?])]
    (if user
      [authenticated]
      [public])))
