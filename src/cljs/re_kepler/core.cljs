(ns re-kepler.core
  (:require
   [reagent.dom :as rdom]
   [re-frame.core :as re-frame]
   [reagent.core :as r]
   [re-kepler.events :as events]
   [re-kepler.views :as views]
   [re-kepler.config :as config]))

(def combineReducers js/window.combineReducers)
(def createStore js/window.createStore)
(def applyMiddleware js/window.applyMiddleware )
(def taskMiddleware js/window.taskMiddleware)
(def kepler-gl-reducer js/window.keplerGlReducer)
(def KeplerGl js/window.KeplerGl)

(defn setup-redux []
  (js/console.log "combineReducers" combineReducers)
  (js/console.log "createStore" createStore)
  (js/console.log "applyMiddleware" applyMiddleware)
  (js/console.log "taskMiddleware" taskMiddleware)

  (js/console.log "KeplerGlReducer" kepler-gl-reducer)  
  
  (let [reducer (combineReducers #js {:keplerGl kepler-gl-reducer})
        store (createStore reducer
                           #js {}
                           (applyMiddleware taskMiddleware))]
    (println "Store created" store)
    store))

(defn dev-setup []
  (when config/debug?
    (println "dev mode")))

(defn ^:dev/after-load mount-root []
  (re-frame/clear-subscription-cache!)
  (let [root-el (.getElementById js/document "app")]
    (rdom/unmount-component-at-node root-el)
    (rdom/render [views/main-panel]
                 root-el
                 (r/create-compiler {:function-components true}))))

(defn init []
  (re-frame/dispatch-sync [::events/initialize-db])
  (dev-setup)
  (setup-redux)
  (mount-root))
