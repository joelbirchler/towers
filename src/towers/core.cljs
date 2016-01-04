(ns towers.core
  (:require
    [towers.world-gen :as world-gen]
    [towers.components.world :as board]
    [towers.render.dimensions :as dimensions]
    [reagent.core :as reagent]))


(enable-console-print!)
(println "### Towers ###")


(def board (world-gen/generate-board 3))
(def hero (reagent/atom {:x 0 :y 0 :z (world-gen/summit-at board 0 0)}))
(def game-dom-element (js/document.getElementById "game"))

(defn game []
  [:svg
   {:xmlns "http://www.w3.org/2000/svg" :width dimensions/width :height dimensions/height}
   (board/world board @hero)])

(defn main []
  (reagent/render-component [game] game-dom-element))
