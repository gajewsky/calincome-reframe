(ns app.router
  (:require [bidi.bidi :as bidi]
            [pushy.core :as pushy]
            [re-frame.core :as rf]))

(def routes ["/" {""              :dashboard
                  "dashboard"     {"" :dashboard [:recipe-id] :recipe}
                  "incomes/"      {"" :incomes [:income-id] :income}
                  "bills/"        {"" :bills [:bill-id] :bill}
                  "categories/"   {"" :categories [:category-id] :category}
                  "vendors/"      {"" :vendors [:vendor-id] :vendor}
                  "profile"       :profile
                  "sign-up"       :sign-up
                  "log-in"        :log-in}])

(def history
  (let [dispatch #(rf/dispatch [:route-changed %])
        match #(bidi/match-route routes %)]
    (pushy/pushy dispatch match)))

(defn start!
  []
  (pushy/start! history))

(def path-for
  (partial bidi/path-for routes))

(defn set-token!
  [token]
  (pushy/set-token! history token))
