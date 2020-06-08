(ns app.nav.events
  (:require [re-frame.core :refer [reg-event-db reg-event-fx reg-fx path]]
            [app.router :as router]))

(def nav-interceptors [(path :nav)])

(reg-fx
 :navigate-to
 (fn [{:keys [path]}]
   (router/set-token! path)))

(reg-event-fx
 :route-changed
 nav-interceptors
 (fn [{nav :db} [_ {:keys [handler route-params]}]]
   (let [nav (assoc nav :active-page handler)]
     (case handler
       :incomes
       {:db nav
        :dispatch [:fetch-incomes]}

       :income
       {:db (assoc nav :active-income (keyword (:income-id route-params)))}

       :vendor
       {:db (assoc nav :active-vendor (keyword (:vendor-id route-params)))}

       :bill
       {:db (assoc nav :active-bill (keyword (:bill-id route-params)))}

       :category
       {:db (assoc nav :active-category (keyword (:category-id route-params)))}

       {:db (dissoc nav
                    :active-income
                    :active-vendor
                    :active-category
                    :active-bill)}))))

(reg-event-db
 :set-active-nav
 (fn [db [_ active-nav]]
   (assoc-in db [:nav :active-nav] active-nav)))

(reg-event-db
 :set-active-page
 (fn [db [_ active-page]]
   (assoc-in db [:nav :active-page] active-page)))
