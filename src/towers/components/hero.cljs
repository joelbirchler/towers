(ns towers.components.hero
  (:require [towers.components.prism :as prism]
            [towers.render.dimensions :as dimensions]))


(def hero-height (* dimensions/grid-height 2))
(def hero-width (/ dimensions/grid-width 2))
(def hero-length (/ dimensions/grid-length 2))
(def hero-color [230 80 0])

(defn hero [xyz]
  (let [xy (dimensions/to-px-coords xyz)]
    (prism/prism "hero" (:x xy) (:y xy) hero-width hero-length hero-height hero-color)))
