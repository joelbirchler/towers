(ns towers.core
  (:require
    [towers.world-gen :as world-gen]
    [towers.components.world :as board]
    [towers.render.dimensions :as dimensions]
    [reagent.core :as reagent]))


(enable-console-print!)
(println "### Towers ###")

;; TODO:
;; 5. Think about some sort of functional reactive-style or flux-style or rails-style pub/sub event flow
;;    - I think maybe I want to use core.async
;;    - https://github.com/pointslope/remit
;;    - https://yobriefca.se/blog/2014/06/04/publish-and-subscribe-with-core-dot-asyncs-pub-and-sub/


(def board (world-gen/generate-board 5))
(def hero (reagent/atom (world-gen/coords-on-top-of board 4 0)))
(def game-dom-element (js/document.getElementById "game"))

(defn game []
  [:svg
   {:xmlns "http://www.w3.org/2000/svg" :width dimensions/width :height dimensions/height}
   (board/world board @hero)])

(defn main []
  (reagent/render-component [game] game-dom-element))
