(require '[cljs.build.api :as b])

(b/watch "src"
  {:main 'towers.core
   :output-to "out/towers.js"
   :output-dir "out"})
