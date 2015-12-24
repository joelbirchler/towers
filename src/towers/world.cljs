(ns towers.world)


(js/noise.seed (rand))

(defn rand-tile-brightness []
  (- (rand 0.18) 0.09))

(defn stack-at [tiles x y]
  (:stack
    (nth (nth tiles x) y)))

(defn coords-on-top-of [tiles x y]
  {:x x
   :y y
   :z (count (stack-at tiles x y))})

(defn calc-tile-type [stack-height index]
  (if (= (- stack-height 1) index) :grass :dirt))

(defn calc-stack-height [x y]
  (-> (js/noise.simplex2 (/ x 5) (/ y 5))
      (+ 1)
      (* 3)
      js/Math.ceil))

(defn generate-stack [height]
  (let [types (map #(calc-tile-type height %) (range height))
        brightness (map rand-tile-brightness (range height))]
    (map vector types brightness)))

(defn generate-board [size]
  (partition size
    (for [x (range size) y (range size)]
      {:x x
       :y y
       :stack (generate-stack (calc-stack-height x y))})))