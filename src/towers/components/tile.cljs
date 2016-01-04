(ns towers.components.tile
  (:require [towers.components.prism :as prism]
            [towers.render.dimensions :as dimensions]
            [towers.render.color :as color]))


(def types {
  :dirt  {:color [139 68 18]   }
  :grass {:color [80 220 80]   }
  :sand  {:color [235 220 188] }
  :water {:color [80 120 220]  }})


(defn tile [tile-data walkable?]
  (let [xy (dimensions/to-px-coords tile-data)
        base-color (:color ((:type tile-data) types))
        face-color (color/shade base-color (if walkable? 1.0 (:brightness tile-data)))]
    (prism/prism
      (str "tile-" (:x tile-data) "-" (:y tile-data) "-" (:z tile-data))
      (:x xy) (:y xy)
      dimensions/grid-width
      dimensions/grid-length
      dimensions/grid-height
      face-color)))
