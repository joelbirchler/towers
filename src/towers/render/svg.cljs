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

(def tile-thickness 16)
(def tile-width 80)
(def tile-height 40)

(def hero-thickness 32)
(def hero-width 40)
(def hero-height 20)

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

(defn svg-prism [px-x px-y width height thickness face-color]
  (let [half-width (/ width 2)
        half-height (/ height 2)
        left (- px-x half-width)
        right (+ px-x half-width)
        edge-top (- px-y thickness)
        face-top (- edge-top half-height)
        face-bottom (+ edge-top half-height)
        bottom (+ px-y half-height)
        left-color (color/shade face-color -0.15)
        right-color (color/shade face-color -0.3)]
    (svg-group
      (svg-polygon
        [[left px-y] [left edge-top] [px-x edge-top] [px-x bottom]]
        (color/rgb-to-css left-color))
      (svg-polygon
        [[right px-y] [right edge-top] [px-x edge-top] [px-x bottom]]
        (color/rgb-to-css right-color))
      (svg-polygon
        [[left edge-top] [px-x face-top] [right edge-top] [px-x face-bottom]]
        (color/rgb-to-css face-color)))))


;; TODO: This game specific stuff could go elsewhere (just need a better name)
(defn tile-to-px-coords [tile-coords]
  {:x (- origin-x (* (:x tile-coords) tile-width 0.5) (* (:y tile-coords) tile-width -0.5))
   :y (+ origin-y (* (:y tile-coords) tile-height 0.5) (* (:x tile-coords) tile-height 0.5) (* (:z tile-coords) tile-thickness -1))})

(defn svg-tile [tile-coords tile]
  (let [px-coords (tile-to-px-coords tile-coords)
        tile-type (first tile)
        tile-brightness (second tile)
        base-color (:color (tile-type tile/types))
        face-color (color/shade base-color tile-brightness)]
    (svg-prism (:x px-coords) (:y px-coords) tile-width tile-height tile-thickness face-color)))

(defn svg-hero [tile-coords]
  (let [px-coords (tile-to-px-coords tile-coords)
        color [230 80 0]]
    (svg-prism (:x px-coords) (:y px-coords) hero-width hero-height hero-thickness color)))

(defn svg-tile-stack [x y stack]
  (map-indexed
    (fn [stack-index tile] (svg-tile {:x x :y y :z stack-index} tile))
    stack))

(defn map-2d [my-fn my-list]
  (for [row my-list]
    (for [cell row]
      (my-fn cell))))

(defn svg-world [board hero]
  (map-2d
    (fn [cell]
      (let [x (:x cell)
            y (:y cell)
            stack (:stack cell)
            tiles (svg-tile-stack x y stack)]
        (if (and (= (:y hero) y) (= (:x hero) x))
          (concat tiles [(svg-hero hero)])
          tiles)))
    board))