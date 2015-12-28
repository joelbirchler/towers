(ns towers.components.board
  (:require [towers.render.dimensions :as dimensions]
            [towers.components.tile :as tile]
            [towers.components.hero :as hero]))



(defn tile-stack [x y stack]
  (map-indexed
    (fn [stack-index tile] (tile/tile (dimensions/to-px-coords {:x x :y y :z stack-index}) tile))
    stack))

(defn map-2d [my-fn my-list]
  (for [row my-list]
    (for [cell row]
      (my-fn cell))))

;; TODO: separate board from hero better?
(defn world [board hero]
  (map-2d
    (fn [cell]
      (let [x     (:x cell)
            y     (:y cell)
            stack (:stack cell)
            tiles (tile-stack x y stack)]
        (if (and (= (:y hero) y) (= (:x hero) x))
          (concat tiles [(hero/hero (dimensions/to-px-coords hero))])
          tiles)))
    board))