(ns towers.core
  (:require [clojure.browser.repl :as repl]))

(enable-console-print!)
(println "### Towers ###")

;; TODO: Move this to a canvas module
;; TODO: I don't like the rgb as a str

(def canvas (js/document.getElementById "canvas"))
(def canvas-ctx (.getContext canvas "2d"))
(def canvas-width (.-width canvas))
(def canvas-height (.-height canvas))

(defn set-fill-style [rgb-str]
  (aset canvas-ctx "fillStyle" rgb-str))

(defn begin-path []
  (.beginPath canvas-ctx))

(defn fill-path []
  (.fill canvas-ctx))

(defn move-to [x y]
  (.moveTo canvas-ctx x y))

(defn line-to [x y]
  (.lineTo canvas-ctx x y))

(defn draw-polygon [points rgb-str]
  (set-fill-style rgb-str)
  (begin-path)
  (apply #(move-to %1 %2) (first points))
  (doall (map #(apply line-to %) (rest points)))
  (fill-path))

(defn draw-tile [{[x y] :pos}]
  (let [scale 70
        face-half-width (/ scale 2)
        face-half-height (/ face-half-width 2)
        thickness (/ scale 5)
        left (- x face-half-width)
        right (+ x face-half-width)
        top (- y face-half-height)
        bottom (+ y face-half-height)]
    (draw-polygon [[left y] [x bottom] [right y] [x top]] "salmon")
    (draw-polygon [[left y] [x bottom] [x (+ bottom thickness)] [left (+ y thickness)]] "yellow")
    (draw-polygon [[right y] [x bottom] [x (+ bottom thickness)] [right (+ y thickness)]] "red")))

(draw-tile {:pos [300 200]})
