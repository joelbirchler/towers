(ns towers.canvas)

;; TODO: I don't like the rgb as a str

(def canvas-dom
  (js/document.getElementById "canvas"))

(def canvas-ctx
  (.getContext canvas-dom "2d"))

(defn set-fill-style [css-color]
  (aset canvas-ctx "fillStyle" css-color))

(defn begin-path []
  (.beginPath canvas-ctx))

(defn fill-path []
  (.fill canvas-ctx))

(defn move-to [x y]
  (.moveTo canvas-ctx x y))

(defn line-to [x y]
  (.lineTo canvas-ctx x y))

(defn draw-polygon [points css-color]
  (set-fill-style css-color)
  (begin-path)
  (apply #(move-to %1 %2) (first points))
  (doall (map #(apply line-to %) (rest points)))
  (fill-path))
