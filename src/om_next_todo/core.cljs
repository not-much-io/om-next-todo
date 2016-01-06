(ns om-next-todo.core
  (:require [goog.dom :as gdom]
            [om.next :as om :refer-macros [defui]]
            [om.dom :as dom]
            [om-next-todo.parser :as p]
            [om-next-todo.react-bootstrap :as rbs]
            [om-next-todo.om-ui-components :as ui-comp]))

(enable-console-print!)

(def app-state (atom {:app-title   "Om.Next Todo"
                      :todos/lists [{:name "Main"
                                     :list [{:title    "Keep an eye on bunny"
                                             :priority 1
                                             :inv      "Wut?"}
                                            {:title    "Get something to eat"
                                             :priority 3}
                                            {:title    "Read books"
                                             :priority 4}
                                            {:title    "Write code"
                                             :priority 2}]}
                                    {:name "Secondary"
                                     :list [{:title "Eat"
                                             :priority 2}
                                            {:title "Sleep"
                                             :priority 1}
                                            {:title "Jog"
                                             :priority 3}]}]}))

(defui Root
  static om/IQuery
  (query [this]
    [:app-title
     {:todos/lists (om/get-query ui-comp/ToDoList)}])
  Object
  (render [this]
    (println "RootQ: " (om/get-query this))
    (println "RootP: " (om/props this))
    (let [{:keys [todos/lists app-title]} (om/props this)]
      (dom/main nil
                (rbs/Navbar nil
                            (rbs/NavBrand nil
                                          (dom/a nil
                                                 app-title)))
                (rbs/Grid nil
                          (rbs/PanelGroup nil
                                          (map ui-comp/todo-list lists)))))))

(def reconciler
  (om/reconciler {:state  app-state
                  :parser p/parser}))

(om/add-root! reconciler
              Root
              (gdom/getElement "app"))
