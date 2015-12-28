(ns towers.components.prism
  (:require [towers.render.color :as color]))


(defn prism [key x y width height thickness face-color]
  (let [half-width (/ width 2)
        half-height (/ height 2)
        left (- x half-width)
        right (+ x half-width)
        edge-top (- y thickness)
        face-top (- edge-top half-height)
        face-bottom (+ edge-top half-height)
        bottom (+ y half-height)
        left-color (color/shade face-color -0.15)
        right-color (color/shade face-color -0.3)]
    [:g
     {:key key}
     [:polygon
      {:key "left"
       :points [[left y] [left edge-top] [x edge-top] [x bottom]]
       :fill (color/rgb-to-css left-color)}]
     [:polygon
      {:key "right"
       :points [[right y] [right edge-top] [x edge-top] [x bottom]]
       :fill (color/rgb-to-css right-color)}]
     [:polygon
      {:key "face"
       :points [[left edge-top] [x face-top] [right edge-top] [x face-bottom]]
       :fill (color/rgb-to-css face-color)}]]))