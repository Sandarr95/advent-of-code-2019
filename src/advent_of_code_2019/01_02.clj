(ns advent-of-code-2019.01-02
  (:require [clojure.data.csv :as csv]
            [clojure.edn :as edn]))

(defn inspect [i]
  (println i)
  i)

(defn get-input [file]
  (let [parsed-csv (csv/read-csv (slurp file))]
    (map (comp edn/read-string first) parsed-csv)))

(defn fuel-for-mass [mass]
  (-> mass
    (/ 3)
    (- 2)
    int))

(defn fuel-for-mass-and-fuel [masses]
  (loop [masses masses calculated []]
    (let [new-masses (filter pos? (map fuel-for-mass masses))
          new-calculated (conj calculated masses)]
      (if (empty? new-masses)
        (mapcat identity (rest new-calculated))
        (recur (inspect new-masses) new-calculated)))))

(defn -main []
  (->> (get-input "input/01_01.csv")
    fuel-for-mass-and-fuel
    (reduce +)
    inspect))
