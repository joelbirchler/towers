(ns towers.components.tile
  (:require [towers.components.prism :as prism]
            [towers.render.color :as color]))


(def types {
  :dirt  {:color [139 68 18]   :walkable true}
  :grass {:color [80 220 80]   :walkable true}
  :sand  {:color [235 220 188] :walkable true}
  :water {:color [80 120 220]  :walkable false}})

;; TODO: remove bad magic numbers-- they shouldn't go here anyway
(def svg-width 800)
(def svg-height 400)

(def origin-x
  (/ svg-width 2))

(def origin-y
  (/ svg-height 2))

(def tile-thickness 16)
(def tile-width 80)
(def tile-height 40)

(defn to-px-coords [tile-coords]
  {:x (- origin-x (* (:x tile-coords) tile-width 0.5) (* (:y tile-coords) tile-width -0.5))
   :y (+ origin-y (* (:y tile-coords) tile-height 0.5) (* (:x tile-coords) tile-height 0.5) (* (:z tile-coords) tile-thickness -1))})

(defn tile [tile-coords tile]
  (let [px-coords (to-px-coords tile-coords)
        tile-type (first tile)
        tile-brightness (second tile)
        base-color (:color (tile-type types))
        face-color (color/shade base-color tile-brightness)]
    (prism/prism (str "tile-" tile-coords) (:x px-coords) (:y px-coords) tile-width tile-height tile-thickness face-color)))