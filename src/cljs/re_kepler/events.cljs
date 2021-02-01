(ns re-kepler.events
  (:require
   [re-frame.core :as re-frame]
   [re-kepler.db :as db]
   ))

(re-frame/reg-event-db
 ::initialize-db
 (fn [_ _]
   db/default-db))
