 (ns app.fb.firebase
  (:require ["firebase/app" :as fb]
            ["firebase/firestore"]
            ["firebase/database"]
            ["firebase/auth"]))

(def OPTS
    #js {:apiKey "AIzaSyB7azm0ig-7IFUXRxFMjX5osJKyq8rIvos"
         :authDomain "calincome-dff65.firebaseapp.com"
         :databaseURL "https://calincome-dff65.firebaseio.com"
         :projectId "calincome-dff65"})

(defonce FB (fb/initializeApp OPTS))

(def fs-db (.firestore fb))

(defn db [firebase]
  (.firestore firebase))

(def auth (.auth fb))
