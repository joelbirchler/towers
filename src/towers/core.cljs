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

(defn draw-tile-px [{[x y] :pos px :px face-color :color}]
  (let [face-half-width (/ px 2)
        face-half-height (/ face-half-width 2)
        thickness (/ px 5)
        left (- x face-half-width)
        right (+ x face-half-width)
        top (- y face-half-height)
        bottom (+ y face-half-height)
        left-color (shade-rgb face-color -0.15)
        right-color (shade-rgb face-color -0.3)
        ]
    (canvas/draw-polygon
      [[left y] [x y] [x (+ bottom thickness)] [left (+ y thickness)]]
      (rgb-to-css left-color))
    (canvas/draw-polygon
      [[right y] [x y] [x (+ bottom thickness)] [right (+ y thickness)]]
      (rgb-to-css right-color))
    (canvas/draw-polygon
      [[left y] [x bottom] [right y] [x top]]
      (rgb-to-css face-color))))


(def tile-types {
  :dirt  {:color [235 220 188] :walkable true}
  :sand  {:color [235 220 188] :walkable true}
  :grass {:color [80 220 80] :walkable true}
  :water {:color [80 120 220] :walkable false}})


(def origin-x 400) ;; TODO: should calculate all dimensions and scale from canvas
(def origin-y 50)

;; TODO: less magic numbers, but also don't repeat calc in draw-tile-px
(defn render-tile [x y z tile-type]
  (draw-tile-px {
    :pos [
      (- origin-x (* x 40) (* y -40))
      (+ origin-y (* y 20) (* x 20) (* z -16))]
    :px 80
    :color (:color (tile-type tile-types))}))


;; TODO: if we change to SVG, we could move to a more functional map -> flatten -> render full result scheme
;<svg xmlns="http://www.w3.org/2000/svg" version="1.1" width="100%" height="100%">
;  <g onclick="alert('!');">
;    <polygon points="0,25 50,25 50,60 0,35" fill="rgb(50, 190, 80)"/>
;    <polygon points="100,25 50,25 50,60 100,35" fill="rgb(110, 250, 140)"/>
;    <polygon points="0,25 50,0 100,25 50,50" fill="rgb(80, 220, 110)"/>
;  </g>
;</svg>

(defn render-world [world]
  (doseq [row world]
    (doseq [stack row]
      (let [tiles (map-indexed #({:z %1 :tile-type %2}) (:stack stack))]
        (doseq [tile ])
          (render-tile (:x stack) (:y stack) (:z tile) (:tile-type tile))))))

(def world-size 5)
(def world
  (partition world-size
    (for [x (range world-size) y (range world-size)]
      {:x x
       :y y
       :stack [:sand :dirt :grass :water]})))

(render-world world)
