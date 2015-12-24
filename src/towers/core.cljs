(ns towers.core
  (:require
    [towers.world :as world]
    [towers.render.svg :as svg]))


(enable-console-print!)
(println "### Towers ###")

;; TODO: Add a hero
;; x. Create a function to drop a hero on top of any stack
;; x. Draw a hero twice as tall as the tiles
;; 2. Stop using arrays for coords-- keys in maps would be cleaner
;; 3. clean
;;    - world should describe both the "board" of "tiles" and the "hero" positions
;;    - think about breaking modules up more like react components (but don't necessarily use react)
;;    - no need to advertise svg all over the place


(def board
  (world/generate-board 5))

(def hero
  (world/coords-on-top-of board 4 0))

(svg/render-svg
  (svg/svg-world board hero))

