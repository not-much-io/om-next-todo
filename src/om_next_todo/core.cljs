(ns om-next-todo.core
  (:require [goog.dom :as gdom]
            [om.next :as om :refer-macros [defui]]
            [om.dom :as dom]
            [om-next-todo.parser :as p]
            [om-next-todo.react-bs :as rbs]))

(enable-console-print!)

(defonce app-state (atom {:app-title "Om.Next Todo"
                          :todos     [{:title    "Keep an eye on bunny"
                                       :priority 1}
                                      {:title    "Get something to eat"
                                       :priority 3}
                                      {:title    "Read books"
                                       :priority 4}
                                      {:title    "Write code"
                                       :priority 2}]}))

(defui Root
  Object
  (render [this]
    (rbs/bsNavBar nil
                  (rbs/bsNavBarBrand nil
                                     (dom/a nil "Om.Next ToDo"))
                  (rbs/bsNav nil "test"))))

(def reconciler
  (om/reconciler {:state  app-state
                  :parser p/parser}))

(om/add-root! reconciler
              Root
              (gdom/getElement "app"))
