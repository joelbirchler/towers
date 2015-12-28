(ns towers.components.tile
  (:require [towers.components.prism :as prism]
            [towers.render.dimensions :as dimensions]
            [towers.render.color :as color]))


(def types {
  :dirt  {:color [139 68 18]   :walkable true}
  :grass {:color [80 220 80]   :walkable true}
  :sand  {:color [235 220 188] :walkable true}
  :water {:color [80 120 220]  :walkable false}})


(defn tile [xy tile]
  (let [tile-type (first tile)
        tile-brightness (second tile)
        base-color (:color (tile-type types))
        face-color (color/shade base-color tile-brightness)]
    (prism/prism
      (str "tile-" (:x xy) "-" (:y xy))
      (:x xy) (:y xy)
      dimensions/grid-width
      dimensions/grid-length
      dimensions/grid-height
      face-color)))