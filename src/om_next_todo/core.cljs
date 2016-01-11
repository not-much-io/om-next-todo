(ns om-next-todo.core
  (:require [goog.dom :as gdom]
            [om.next :as om :refer-macros [defui]]
            [om.dom :as dom]
            [om-next-todo.parser :as p]
            [om-next-todo.om-ui-components :as ui-comp]
            [om-next-todo.material-ui :as mui]))

(enable-console-print!)

(defonce app-state (atom {:app-title   "Om.Next Todo"
                          :todos [{:title    "Keep an eye on bunny"
                                   :priority 1
                                   :inv      "Wut?"}
                                  {:title    "Get something to eat"
                                   :priority 3}
                                  {:title    "Read books"
                                   :priority 4}
                                  {:title    "Write code"
                                   :priority 2}]}))

(defui App
  static om/IQuery
  (query [this]
    [:app-title :todos])
  Object
  (render [this]
    (println "RootQuery: " (om/get-query this))
    (println "RootProps: " (om/props this))
    (let [{:keys [app-title todos]} (om/props this)]
      (dom/main nil
                (mui/app-bar #js {:title app-title
                                  :zDepth 1} "")
                (ui-comp/todo-list {:todos todos})
                (mui/fab #js {:style #js {:position "fixed"
                                          :bottom "20px"
                                          :right "20px"}}
                         (mui/font-icon #js {:className "material-icons"}
                                        "add"))))))

(def reconciler
  (om/reconciler {:state  app-state
                  :parser p/parser}))

(om/add-root! reconciler
              App
              (gdom/getElement "app"))
