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
    (comment (println "ToDoItemQ: " (om/get-query ToDoItem))
             (println "ToDoItemP: " (om/props this)))
    (let [props     (om/props this)
          title     (:title props)
          priority  (:priority props)]
      (dom/tr nil
              (dom/td nil title)
              (dom/td #js {:className "text-center"} priority)
              (dom/td nil
                      (rbs/Button #js {:bsStyle "warning"} "Delete"))))))

(def todo-item (om/factory ToDoItem))


(defui ToDoList
  static om/IQuery
  (query [this]
    [{:list (om/get-query ToDoItem)}])
  Object
  (render [this]
    (println "ToDoListQ: " (om/get-query ToDoList))
    (println "ToDoListP: " (om/props this))
    (let [thead (dom/thead nil
                           (dom/tr nil
                                   (dom/th nil
                                           "ToDo")
                                   (dom/th #js {:className "text-center"}
                                           "Priority")
                                   (dom/th nil
                                           "")))
          props  (om/props this)
          list   (sort-by :priority (:list props))]
      (rbs/Grid nil
                (rbs/Panel nil
                           (rbs/Table
                             nil
                             thead
                             (dom/tbody nil
                                        (map todo-item list))))))))

(def todo-list (om/factory ToDoList))

(defui ToDoTab
  static om/IQuery
  (query [this]
    [:name
     (om/get-query ToDoList)])
  Object
  (render [this]
    (comment (println "ToDoTabQ:" (om/get-query ToDoTab))
             (println "ToDoTabP:" (om/props this)))
    (let [name (:name (om/props this))]
      (rbs/Tab #js {:eventKey name
                    :title    name}
               (rbs/Grid nil
                         (todo-list (om/props this)))))))

(def todo-tab (om/factory ToDoTab))