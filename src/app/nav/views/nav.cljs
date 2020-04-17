(ns app.nav.views.nav
  (:require [app.nav.views.public :refer [public]]
            [app.nav.views.authenticated :refer [authenticated]]))

(defn nav
  []
  (let [user false]
    (if user
      [authenticated]
      [public])))
