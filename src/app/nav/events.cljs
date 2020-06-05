(ns app.nav.events
  (:require [re-frame.core :refer [reg-event-db reg-fx]]
            [app.router :as router]))

(reg-fx
 :navigate-to
 (fn [{:keys [path]}]
   (router/set-token! path)))

(reg-event-db
  :route-changed
  (fn [db [_ {:keys [handler route-params]}]]
    (-> db
        (assoc-in [:nav :active-page] handler)
        (assoc-in [:nav :active-income] (keyword (:income-id route-params)))
        (assoc-in [:nav :active-vendor] (keyword (:vendor-id route-params)))
        (assoc-in [:nav :active-category] (keyword (:category-id route-params)))
        (assoc-in [:nav :active-bill] (keyword (:bill-id route-params))))))

(reg-event-db
 :set-active-nav
 (fn [db [_ active-nav]]
   (assoc-in db [:nav :active-nav] active-nav)))

(reg-event-db
 :set-active-page
 (fn [db [_ active-page]]
   (assoc-in db [:nav :active-page] active-page)))
