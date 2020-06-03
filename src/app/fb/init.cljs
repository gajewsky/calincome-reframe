(ns app.fb.init
  (:require ["firebase/app" :as firebase]
            ["firebase/firestore"]
            ["firebase/database"]
            ["firebase/auth"]))

(defn firebase-init
  []
  (firebase/initializeApp
    #js {:apiKey "AIzaSyB7azm0ig-7IFUXRxFMjX5osJKyq8rIvos"
         :authDomain "calincome-dff65.firebaseapp.com"
         :databaseURL "https://calincome-dff65.firebaseio.com"
         :projectId "calincome-dff65"}))
