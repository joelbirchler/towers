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

(defn tile-to-px-coords [x y z]
  [(- origin-x (* x tile-width 0.5) (* y tile-width -0.5))
   (+ origin-y (* y tile-height 0.5) (* x tile-height 0.5) (* z tile-thickness -1))])

(defn render-svg [svg]
  (set! (.-innerHTML svg-dom-element) svg))

(defn svg-circle [x y r color]
  (str
    "<circle cx='"
    x
    "' cy='"
    y
    "' r='"
    r
    "' fill='"
    color
    "'/>"))

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
(defn svg-tile [x y z tile]
  (let [px-coords (tile-to-px-coords x y z)
        px-x (first px-coords)
        px-y (second px-coords)
        tile-type (first tile)
        tile-brightness (second tile)
        base-color (:color (tile-type tile/types))
        face-color (color/shade base-color tile-brightness)
        ]
    (svg-prism px-x px-y tile-width tile-height tile-thickness face-color)))

(defn svg-hero [x y z]
  (let [px-coords (tile-to-px-coords x y z)
        px-x (first px-coords)
        px-y (second px-coords)
        color [230 80 0]]
    (svg-prism px-x px-y hero-width hero-height hero-thickness color)))

(defn svg-tile-stack [x y stack]
  (map-indexed
    (fn [z tile] (svg-tile x y z tile))
    stack))

(defn map-2d [my-fn my-list]
  (for [row my-list]
    (for [cell row]
      (my-fn cell))))

(defn svg-world [board hero]
  (let [hero-x (nth hero 0)
        hero-y (nth hero 1)
        hero-z (nth hero 2)]
    (map-2d
      (fn [cell]
        (let [x (:x cell)
              y (:y cell)
              stack (:stack cell)
              tiles (svg-tile-stack x y stack)]
          (if (and (= hero-y y) (= hero-x x))
            (concat tiles [(svg-hero x y hero-z)])
            tiles)))
      board)))