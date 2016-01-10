(ns om-next-todo.om-ui-components
  (:require [goog.dom :as gdom]
            [om.next :as om :refer-macros [defui]]
            [om.dom :as dom]))


(defui ToDoItem
  static om/IQuery
  (query [this]
    [:title :priority])
  Object
  (render [this]
    (comment (println "ToDoItemQ: " (om/get-query ToDoItem))
             (println "ToDoItemP: " (om/props this)))
    (let [props (om/props this)
          title (:title props)
          priority (:priority props)]
      )))

(def todo-item (om/factory ToDoItem))

(defui ToDoList
  static om/IQuery
  (query [this]
    [{:list (om/get-query ToDoItem)}])
  Object
  (render [this]
    (comment (println "ToDoListQ: " (om/get-query ToDoList))
             (println "ToDoListP: " (om/props this)))
    (let [thead (dom/thead nil
                           (dom/tr nil
                                   (dom/th nil
                                           "ToDo")
                                   (dom/th #js {:className "text-center"}
                                           "Priority")
                                   (dom/th nil
                                           "")))
          props (om/props this)
          list (sort-by :priority (:list props))]
      )))

(def todo-list (om/factory ToDoList))
