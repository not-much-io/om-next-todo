(ns om-next-todo.om-ui-components
  (:require [goog.dom :as gdom]
            [om.next :as om :refer-macros [defui]]
            [om.dom :as dom]
            [om-next-todo.material-ui :as mui]))

(comment
  "Does not render.."

  (defui ToDoForm
    static om/IQuery
    (query [this]
      ['(todos/add!)])
    Object
    (render [this]
      (mui/table-header
        #js {:adjustForCheckbox false
             :displaySelectAll  false}
        (mui/table-row
          nil
          (mui/table-header-col #js {:colSpan 2
                                     :style   #js {:textAlign "center"}}
                                (mui/text-field #js {:defaultValue "new todo.."}))))))

  (def todo-form (om/factory ToDoForm)))

(defui ToDoItem
  static om/IQuery
  (query [this]
    ['(todos/remove!)])
  Object
  (render [this]
    (let [props     (om/props this)
          title     (:title props)]
      (mui/table-row
        nil
        (mui/table-row-col
          nil
          title)
        (mui/table-row-col
          #js {:style
               #js {:textAlign "right"}}
          (mui/icon-button
            #js {:iconClassName "material-icons"
                 :onClick       (fn [_]
                                  (om/transact! this
                                                `[(todos/remove! {:title ~title})]))}
            "close"))))))

(def todo-item (om/factory ToDoItem))

(defn todo-form [c]
  (let [get-title       #(.-value (gdom/getElement "todo-input"))
        trans-todo      (fn []
                          (om/transact! c
                                        `[(todos/add! {:title ~(get-title)})]))]
    (mui/table-header
      #js {:adjustForCheckbox false
           :displaySelectAll  false}
      (mui/table-row
        nil
        (mui/table-header-col #js {:colSpan 2
                                   :style   #js {:textAlign "center"}}
                              (mui/text-field #js {:id "todo-input"
                                                   :hintText "New ToDo.."
                                                   :onEnterKeyDown trans-todo}))))))

(defui ToDoList
  static om/IQuery
  (query [this]
    (into []
          (concat
            [:todos]
            (om/get-query ToDoItem)
            ; (om/get-query ToDoForm)
            '(todos/add!))))
  Object
  (render [this]
    (let [todos (:todos (om/props this))
          thead (todo-form this)
          tbody (mui/table-body nil (map todo-item todos))]
      (mui/table nil
                 thead
                 tbody))))

(def todo-list (om/factory ToDoList))