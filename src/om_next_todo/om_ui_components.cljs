(ns om-next-todo.om-ui-components
  (:require [goog.dom :as gdom]
            [om.next :as om :refer-macros [defui]]
            [om.dom :as dom]
            [om-next-todo.react-bootstrap :as rbs]))

(defui ToDoItem
  static om/IQuery
  (query [this]
    [:title :priority])
  Object
  (render [this]
    (println "ToDoItemQ: " (om/get-query ToDoItem))
    (println "ToDoItemP: " (om/props this))
    (let [props     (om/props this)
          title     (:title props)
          priority  (:priority props)]
      (dom/tr nil
              (dom/td nil title)
              (dom/td nil priority)
              (dom/td nil
                      (rbs/Button #js {:bsStyle "warning"} "Delete"))))))

(def todo-item (om/factory ToDoItem {:key-fn :title}))

(defui ToDoList
  static om/IQuery
  (query [this]
    [:name
     {:list (om/get-query ToDoItem)}])
  Object
  (render [this]
    (println "ToDoListQ: " (om/get-query ToDoList))
    (println "ToDoListP: " (om/props this))
    (let [thead (dom/thead nil
                           (dom/tr nil
                                   (dom/th nil
                                           "ToDo")
                                   (dom/th nil
                                           "Priority")
                                   (dom/th nil
                                           "")))
          props  (om/props this)
          list   (sort-by :priority (:list props))
          name   (:name props)]
      (rbs/Panel #js {:header name
                      :eventKey name
                      :collapsible true}
                 (rbs/Table
                   #js {:condensed true}
                   thead
                   (dom/tbody nil
                              (map todo-item list)))))))

(def todo-list (om/factory ToDoList))