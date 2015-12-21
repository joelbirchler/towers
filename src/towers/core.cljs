(ns towers.core
  (:require
    [towers.world :as world]
    [towers.render.svg :as svg]))


(enable-console-print!)
(println "### Towers ###")

(def my-world (world/generate-world 5))
(svg/render-svg (svg/svg-world my-world))