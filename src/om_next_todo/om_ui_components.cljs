(ns om-next-todo.om-ui-components
  (:require [goog.dom :as gdom]
            [om.next :as om :refer-macros [defui]]
            [om.dom :as dom]
            [om-next-todo.material-ui :as mui]))


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
      (mui/table-row nil
                     (mui/table-row-column nil
                                           title)
                     (mui/table-row-column #js {:style #js {:textAlign "center"}}
                                           priority)
                     (mui/table-row-column nil
                                           (mui/icon-button #js {:iconClassName "material-icons"}
                                                            "remove"))))))

(def todo-item (om/factory ToDoItem))

(defui ToDoList
  static om/IQuery
  (query [this]
    [{:todos (om/get-query ToDoItem)}])
  Object
  (render [this]
    (comment (println "ToDoListQ: " (om/get-query ToDoList))
             (println "ToDoListP: " (om/props this)))
    (let [thead (mui/table-header #js {:displaySelectAll false
                                       :adjustForCheckbox false}
                                  (mui/table-row nil
                                                 (mui/table-header-column nil
                                                                          "ToDo")
                                                 (mui/table-header-column #js {:style #js {:textAlign "center"}}
                                                                          "Priority")
                                                 (mui/table-header-column nil
                                                                          "")))
          list (sort-by :priority (om/props this))]
      (mui/paper #js {:zDepth 1
                      :style #js {:margin 20}}
                 (mui/table nil
                            thead
                            (mui/table-body nil
                                            (map todo-item list)))))))

(def todo-list (om/factory ToDoList))
