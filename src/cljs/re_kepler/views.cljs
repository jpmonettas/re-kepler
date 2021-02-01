(ns re-kepler.views
  (:require
   [re-frame.core :as re-frame]
   [reagent.core :as reagent]
   [re-kepler.subs :as subs]
   ))



(def access-token "")

;; (defn kepler-gl [props]
;;   [js/window.KeplerGl
;;    {:id "foo"
;;     :mapbox-api-access-token access-token
;;     :width 500
;;     :height 500}])

;; (defn main-panel []
;;   (let [name (re-frame/subscribe [::subs/name])]
;;     [:div
;;      [:h1 "Hello from " @name]
;;      [:f> kepler-gl]
;;      ]))


(def kepler-gl (reagent/adapt-react-class js/window.KeplerGl))

(defn kepler-element [props]
  (reagent/as-element [kepler-gl {:id                      "foo"
                                  :mapbox-api-access-token access-token
                                  :width                   500
                                  :height                  500}]))
(defn main-panel []
  (let [name (re-frame/subscribe [::subs/name])]
    [:div
     [:h1 "Hello from " @name]
     [:> kepler-element]]))
