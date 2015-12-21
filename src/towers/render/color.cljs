(ns towers.render.color)

(defn rgb-to-css [[r g b]]
  (str "rgb(" (int r) "," (int g) "," (int b) ")"))

(defn shade [rgb delta-percent]
  (let [percent (+ 1 delta-percent)]
    (map #(* percent %) rgb)))
