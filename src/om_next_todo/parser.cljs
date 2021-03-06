(ns om-next-todo.parser
  (:require [om.next :as om]))

;; Reads

(defmulti read om/dispatch)

(defmethod read :default
  [{:keys [state]} key _]
  {:value (key @state)})

;; Mutations

(defmulti mutate om/dispatch)

(defmethod mutate 'todos/add!
  [{:keys [state]} _ {:keys [title]}]
  {:value  {:keys [:todos]}
   :action (fn []
             (swap! state update-in [:todos] conj {:title title}))})

(defmethod mutate 'todos/remove!
  [{:keys [state]} _ {:keys [title]}]
  {:value {:keys [:todos]}
   :action (fn []
             (swap! state update-in [:todos]
                    (fn [curr-todos]
                      (filter #(not= (:title %) title) curr-todos))))})

;; Parser

(def parser (om/parser {:read read :mutate mutate}))