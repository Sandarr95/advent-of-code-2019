(ns advent-of-code-2019.01-01
  (:require [clojure.data.csv :as csv]
            [clojure.edn :as edn]))

(defn inspect [i]
  (print i)
  i)

(defn get-input [file]
  (let [parsed-csv (csv/read-csv (slurp file))]
    (map (comp edn/read-string first) parsed-csv)))

(defn fuel-for-mass [mass]
  (-> mass
    (/ 3)
    (- 2)
    int))

(defn -main []
  (->> (get-input "input/01_01.csv")
    (map fuel-for-mass)
    (reduce +)
    inspect))