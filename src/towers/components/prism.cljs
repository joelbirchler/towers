(ns towers.components.prism
  (:require [towers.render.color :as color]))


(defn prism [key px-x px-y width height thickness face-color]
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
    [:g
     {:key key}
     [:polygon
      {:key "left"
       :points [[left px-y] [left edge-top] [px-x edge-top] [px-x bottom]]
       :fill (color/rgb-to-css left-color)}]
     [:polygon
      {:key "right"
       :points [[right px-y] [right edge-top] [px-x edge-top] [px-x bottom]]
       :fill (color/rgb-to-css right-color)}]
     [:polygon
      {:key "face"
       :points [[left edge-top] [px-x face-top] [right edge-top] [px-x face-bottom]]
       :fill (color/rgb-to-css face-color)}]]))