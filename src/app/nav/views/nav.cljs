(ns app.nav.views.nav
  (:require [re-frame.core :as rf]
            [app.nav.views.authenticated :refer [authenticated]]))

(defn nav
  []
  (let [user @(rf/subscribe [:logged-in?])]
    (when user
      [authenticated])))
