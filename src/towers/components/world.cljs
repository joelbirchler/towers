(ns towers.components.world
  (:require [towers.render.dimensions :as dimensions]
            [towers.components.tile :as tile]
            [towers.components.hero :as hero]))


(defn distance [xyz1 xyz2]
  (max
    (Math/abs (- (:x xyz2) (:x xyz1)))
    (Math/abs (- (:y xyz2) (:y xyz1)))
    (Math/abs (- (:z xyz2) (:z xyz1)))))

(defn in-walking-range? [hero xyz]
  (>= 1 (distance hero xyz)))

;; TODO: AHA! Hero sitting on top of tile should have same xyz as tile!
;; This will make changes in several areas!

(defn same-xyz? [& args]
  (apply =
    (map #(select-keys % [:x :y :z]) args)))

(defn on-top-of? [xyz1 xyz2]
  (same-xyz? xyz2 (update-in xyz1 [:z] inc)))

(defn world [board hero]
  (mapcat
    (fn [tile-data]
      (let [tile (tile/tile tile-data (in-walking-range? hero tile-data))]
        (if (on-top-of? tile-data hero)
          [tile (hero/hero hero)]
          [tile])))
    board))
