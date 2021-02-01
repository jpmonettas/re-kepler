(ns kepler.core
  (:require [reagent.core :as r]
            [reagent.dom :as rdom]
            [clojure.string :as str]
            ["react" :as react]
            ["keplergl.min.js" :default KeplerGl :refer (keplerGlReducer) ]
            ["react-redux" :refer (Provider)]
            ["redux" :refer (combineReducers createStore applyMiddleware compose)]
            ["react-palm/tasks" :refer (taskMiddleware)]
            ))

(def functional-compiler (r/create-compiler {:function-components true}))

(def access-token "")

(def kepler-gl (r/adapt-react-class KeplerGl))

(def provider (r/adapt-react-class Provider))

(defn app []
  (let [reducer (combineReducers #js {:keplerGl keplerGlReducer})
        store   (createStore reducer
                             #js {}
                             (applyMiddleware taskMiddleware))]
    [:div [provider {:store store}
           [kepler-gl {:id                      "foo"
                       :mapbox-api-access-token access-token
                       :width                   500
                       :height                  500}]]]))

(defn ^:dev/before-load stop []
  (js/console.log "Stopping..."))

(defn ^:dev/after-load start []
  (js/console.log "Starting..." )
  (rdom/render [app]
               (.getElementById js/document "app")
               functional-compiler))

(defn ^:export init []
  (start))
