(ns advent-of-code-2019.02-01
  (:require [advent-of-code-2019.aoc-helper :refer [inspect get-input list-of-numbers]]))

(def input
  (-> "02"
    get-input
    first
    list-of-numbers
    vec))

(def input01
  (-> input
    (assoc 1 12)
    (assoc 2 2)))

(def input02
  (-> input
    (assoc 1 22)
    (assoc 2 54)))

(def ops {1 +
          2 *})

(defn safe-pcs [state pc]
  (let [max-pc (count state)]
    [(min max-pc pc) (min max-pc (+ 4 pc))]))

(defn run-step [state pc]
  (let [[pc1 pc2] (safe-pcs state pc)
        [op idx1 idx2 idx3] (subvec state pc1 pc2)]
    (if-let [op-fn (ops op)]
      (assoc state idx3 (op-fn (nth state idx1) (nth state idx2)))
      (reduced state))))

(defn run-machine [input]
  (reduce run-step input (map (partial * 4) (range))))

(defn run-machine-with-args [input arg1 arg2]
  (-> input
    (assoc 1 arg1)
    (assoc 2 arg2)
    run-machine))

(defn brute-force [machine]
  (let [results (for [x (range 100) y (range 100)] (run-machine-with-args machine x y))]
    (filter (comp (partial = 19690720) first) results)))

(defn -main []
  (inspect (run-machine input01))
  (inspect (brute-force input02)))