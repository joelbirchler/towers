(ns towers.render.svg
  (:require
    clojure.string
    [towers.render.color :as color]
    [towers.tile :as tile]))


(def svg-dom-element
  (js/document.getElementById "svg-goes-here"))

(def svg-width
  (.-width (.getBoundingClientRect svg-dom-element)))

(def svg-height
  (.-height (.getBoundingClientRect svg-dom-element)))

(def origin-x
  (/ svg-width 2))

(def origin-y
  (/ svg-height 2))

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

(defn svg-tile [x y z tile]
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
        tile-type (first tile)
        tile-brightness (second tile)
        base-color (:color (tile-type tile/types))
        face-color (color/shade base-color tile-brightness)
        left-color (color/shade face-color -0.15)
        right-color (color/shade face-color -0.3)
        ]
    (svg-group
      (svg-polygon
        [[left px-y] [px-x px-y] [px-x (+ bottom thickness)] [left (+ px-y thickness)]]
        (color/rgb-to-css left-color))
      (svg-polygon
        [[right px-y] [px-x px-y] [px-x (+ bottom thickness)] [right (+ px-y thickness)]]
        (color/rgb-to-css right-color))
      (svg-polygon
        [[left px-y] [px-x bottom] [right px-y] [px-x top]]
        (color/rgb-to-css face-color)))))

(defn svg-tile-stack [x y stack]
  (map-indexed
    (fn [z tile] (svg-tile x y z tile))
    stack))

(defn svg-world [world]
  (for [row world]
    (for [cell row]
      (svg-tile-stack (:x cell) (:y cell) (:stack cell)))))