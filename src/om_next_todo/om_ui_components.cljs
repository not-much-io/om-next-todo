(ns om-next-todo.om-ui-components
  (:require [goog.dom :as gdom]
            [om.next :as om :refer-macros [defui]]
            [om.dom :as dom]
            [om-next-todo.material-ui :as mui]))

(defui ToDoForm
  static om/IQuery
  (query [this]
    '[(todos/add)])
  Object
  (render [this]
    (mui/table-row nil
                   (mui/table-row-column nil
                                         (mui/text-field nil))
                   (mui/table-row-column nil
                                         (mui/text-field nil))
                   (mui/table-row-column nil
                                         (mui/icon-button #js {:iconClassName "material-icons"
                                                               :onClick       (fn [e]
                                                                                )}
                                                          "add")))))

(def todo-form (om/factory ToDoForm))

(defui ToDoItem
  static om/IQuery
  (query [this]
    '[(todos/remove)])
  Object
  (render [this]
    (let [props     (om/props this)
          title     (:title props)
          priority  (:priority props)]
      (mui/table-row nil
                     (mui/table-row-column nil
                                           title)
                     (mui/table-row-column #js {:style #js {:textAlign "center"}}
                                           priority)
                     (mui/table-row-column nil
                                           (mui/icon-button #js {:iconClassName "material-icons"
                                                                 :onClick       (fn [e]
                                                                                  (om/transact! this
                                                                                                `[(todos/remove {:title ~title})]))}
                                                            "remove"))))))

(def todo-item (om/factory ToDoItem))

(defn table-header []
  (mui/table-header
    #js {:displaySelectAll  false
         :adjustForCheckbox false}
    (mui/table-row nil
                   (mui/table-header-column nil
                                            "ToDo")
                   (mui/table-header-column #js {:style
                                                 #js {:textAlign "center"}}
                                            "Priority")
                   (mui/table-header-column nil
                                            ""))))

(defui ToDoList
  static om/IQuery
  (query [this]
    [:todos
     (flatten (om/get-query ToDoItem))])
  Object
  (render [this]
    (let [sorted-list (sort-by :priority (om/props this))]
      (mui/paper #js {:zDepth 1
                      :style  #js {:margin 20}}
                 (mui/table nil
                            (table-header)
                            (mui/table-body nil
                                            (todo-form)
                                            (map todo-item sorted-list)))))))

(def todo-list (om/factory ToDoList))