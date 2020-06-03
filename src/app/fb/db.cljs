(ns app.fb.db
  (:require ["firebase/app" :refer [database]]
            [clojure.string :as str]
            ; [ale.state :as state]
            ))

(defn db-ref
  [path]
  (.ref (database) (str/join "/" path)))

(defn db-save!
  [path value]
  (.set (db-ref path) value))

; (defn db-subscribe
;   [path]
;   (.on (db-ref path)
;        "value"
;        (fn [snapshot]
;          (reset! state/beers (js->clj (.val snapshot) :keywordize-keys true)))))
