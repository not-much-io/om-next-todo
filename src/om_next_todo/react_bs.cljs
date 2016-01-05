(ns om-next-todo.react-bs
  (:require cljsjs.react-bootstrap))

(def all-components nil)

(defn fact [comp]
  (js/React.createFactory comp))

(def bsNavBar         (fact js/ReactBootstrap.Navbar))
(def bsNavBarBrand    (fact js/ReactBootstrap.NavBrand))
(def bsNav            (fact js/ReactBootstrap.Nav))

(def bsPageHeader     (fact js/ReactBootstrap.PageHeader))
(def bsButtonToolbar  (fact js/ReactBootstrap.ButtonToolbar))
(def bsButton         (fact js/ReactBootstrap.Button))
(def bsPanel          (fact js/ReactBootstrap.Panel))
(def bsAccordion      (fact js/ReactBootstrap.Accordion))
(def bsProgressBar    (fact js/ReactBootstrap.ProgressBar))
(def bsInput          (fact js/ReactBootstrap.Input))