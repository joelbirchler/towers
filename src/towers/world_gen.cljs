(ns towers.world-gen)


(js/noise.seed (rand))

(defn rand-tile-brightness []
  (- (rand 0.18) 0.09))

(defn stack-at [board x y]
  (filter #(and (= (:x %) x) (= (:y %) y)) board))

(defn summit-at [board x y]
  (inc (apply max
    (map :z (stack-at board x y)))))

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
  (flatten
    (for [x (range size) y (range size)]
      (let [stack-height (calc-stack-height x y)]
        (for [z (range stack-height)]
          {:x x :y y :z z
           :type (calc-tile-type stack-height z)
           :brightness (rand-tile-brightness)})))))
