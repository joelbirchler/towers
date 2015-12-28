(ns towers.components.world
  (:require [towers.render.dimensions :as dimensions]
            [towers.components.tile :as tile]
            [towers.components.hero :as hero]))


(defn tile-stack [x y stack]
  (map-indexed
    (fn [stack-index tile] (tile/tile (dimensions/to-px-coords {:x x :y y :z stack-index}) tile))
    stack))

(defn world [board hero]
  (for [row board]
    (for [cell row]
      (let [x     (:x cell)
            y     (:y cell)
            stack (:stack cell)
            tiles (tile-stack x y stack)]
        (if (and (= (:y hero) y) (= (:x hero) x))
          (concat tiles [(hero/hero (dimensions/to-px-coords hero))])
          tiles)))))