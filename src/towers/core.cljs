(ns towers.core
  (:require clojure.string))

(enable-console-print!)
(println "### Towers ###")

(defn rgb-to-css [[r g b]]
  (str "rgb(" (int r) "," (int g) "," (int b) ")"))

(defn shade-rgb [rgb delta-percent]
  (let [percent (+ 1 delta-percent)]
    (map #(* percent %) rgb)))

(def tile-types {
  :dirt  {:color [235 220 188] :walkable true}
  :sand  {:color [235 220 188] :walkable true}
  :grass {:color [80 220 80] :walkable true}
  :water {:color [80 120 220] :walkable false}})

(def origin-x 400) ;; TODO: should calculate all dimensions and scale from svg
(def origin-y 50)

(def svg-dom-element
  (js/document.getElementById "svg-goes-here"))

(defn render-svg [svg]
  (set! (.-innerHTML svg-dom-element) svg))

(defn svg-polygon [points color]
  (str
    "<polygon points='"
    (clojure.string/join " " (map #(clojure.string/join "," %) points))
    "' fill='"
    color
    "' />"))

(defn svg-group [& content]
  (str "<g>" (clojure.string/join content) "</g>"))

(defn svg-tile [x y z tile-type]
  (let [tile-size 80
        face-half-width (/ tile-size 2)
        face-half-height (/ face-half-width 2)
        thickness (/ tile-size 5)
        px-x (- origin-x (* x face-half-width) (* y face-half-width -1))
        px-y (+ origin-y (* y face-half-height) (* x face-half-height) (* z thickness -1))
        left (- px-x face-half-width)
        right (+ px-x face-half-width)
        top (- px-y face-half-height)
        bottom (+ px-y face-half-height)
        face-color (:color (tile-type tile-types))
        left-color (shade-rgb face-color -0.15)
        right-color (shade-rgb face-color -0.3)
        ]
    (svg-group
      (svg-polygon
        [[left px-y] [px-x px-y] [px-x (+ bottom thickness)] [left (+ px-y thickness)]]
        (rgb-to-css left-color))
      (svg-polygon
        [[right px-y] [px-x px-y] [px-x (+ bottom thickness)] [right (+ px-y thickness)]]
        (rgb-to-css right-color))
      (svg-polygon
        [[left px-y] [px-x bottom] [right px-y] [px-x top]]
        (rgb-to-css face-color)))))

(render-svg
  (svg-tile 0 0 0 :grass))

;; TODO: if we change to SVG, we could move to a more functional map -> flatten -> render full result scheme
;<svg xmlns="http://www.w3.org/2000/svg" version="1.1" width="100%" height="100%">
;  <g onclick="alert('!');">
;    <polygon points="0,25 50,25 50,60 0,35" fill="rgb(50, 190, 80)"/>
;    <polygon points="100,25 50,25 50,60 100,35" fill="rgb(110, 250, 140)"/>
;    <polygon points="0,25 50,0 100,25 50,50" fill="rgb(80, 220, 110)"/>
;  </g>
;</svg>

;(defn render-world [world]
;  (doseq [row world]
;    (doseq [stack row]
;      (let [tiles (map-indexed #({:z %1 :tile-type %2}) (:stack stack))]
;        (doseq [tile ])
;          (render-tile (:x stack) (:y stack) (:z tile) (:tile-type tile))))))

(def world-size 5)
(def world
  (partition world-size
    (for [x (range world-size) y (range world-size)]
      {:x x
       :y y
       :stack [:sand :dirt :grass :water]})))

;(render-world world)
