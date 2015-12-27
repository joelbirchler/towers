(ns towers.components.hero
  (:require [towers.components.prism :as prism]
            [towers.components.tile :as tile]))


(def hero-thickness 32)
(def hero-width 40)
(def hero-height 20)

(defn hero [tile-coords]
  (let [px-coords (tile/to-px-coords tile-coords)
        color [230 80 0]]
    (prism/prism "hero" (:x px-coords) (:y px-coords) hero-width hero-height hero-thickness color)))