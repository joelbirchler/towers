(ns towers.core
  (:require
    [towers.canvas :as canvas]))

(enable-console-print!)
(println "### Towers ###")

(defn rgb-to-css [[r g b]]
  (str "rgb(" (int r) "," (int g) "," (int b) ")"))

(defn shade-rgb [rgb delta-percent]
  (let [percent (+ 1 delta-percent)]
    (map #(* percent %) rgb)))

(defn draw-tile [{[x y] :pos}]
  (let [scale 70
        face-half-width (/ scale 2)
        face-half-height (/ face-half-width 2)
        thickness (/ scale 5)
        left (- x face-half-width)
        right (+ x face-half-width)
        top (- y face-half-height)
        bottom (+ y face-half-height)
        face-color [235 220 188]
        left-color (shade-rgb face-color -0.15)
        right-color (shade-rgb face-color -0.3)
        ]
    (canvas/draw-polygon
      [[left y] [x bottom] [right y] [x top]]
      (rgb-to-css face-color))
    (canvas/draw-polygon
      [[left y] [x bottom] [x (+ bottom thickness)] [left (+ y thickness)]]
      (rgb-to-css left-color))
    (canvas/draw-polygon
      [[right y] [x bottom] [x (+ bottom thickness)] [right (+ y thickness)]]
      (rgb-to-css right-color))))

(draw-tile {:pos [300 200]})
