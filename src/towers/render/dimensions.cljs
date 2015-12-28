(ns towers.render.dimensions)


(def width 800)
(def height 400)

(def origin-x (/ width 2))
(def origin-y (/ height 2))

(def grid-height 16)
(def grid-width 80)
(def grid-length 40)

(defn to-px-coords [tile-coords]
  {:x (- origin-x (* (:x tile-coords) grid-width 0.5) (* (:y tile-coords) grid-width -0.5))
   :y (+ origin-y (* (:y tile-coords) grid-length 0.5) (* (:x tile-coords) grid-length 0.5) (* (:z tile-coords) grid-height -1))})
