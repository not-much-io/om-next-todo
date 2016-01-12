(ns om-next-todo.core
  (:require [goog.dom :as gdom]
            [om.next :as om :refer-macros [defui]]
            [om.dom :as dom]
            [om-next-todo.parser :as p]
            [om-next-todo.om-ui-components :as ui-comp]
            [om-next-todo.material-ui :as mui]))

(enable-console-print!)

(def app-state (atom {:app-title   "Om.Next Todo"
                      :todos [{:title    "Keep an eye on bunny"}
                              {:title    "Get something to eat"}
                              {:title    "Read books"}
                              {:title    "Write code"}]}))

(defui App
  Object
  (render [this]
    (let [{:keys [app-title todos]} (om/props this)]
      (dom/main nil
                (mui/app-bar  #js {:zDepth 1
                                   :title app-title})
                (mui/paper    #js {:zDepth 1
                                   :style  #js {:margin 20}}
                              (ui-comp/todo-list {:todos todos}))))))

(def reconciler
  (om/reconciler {:state  app-state
                  :parser p/parser}))

(om/add-root! reconciler
              App
              (gdom/getElement "app"))
