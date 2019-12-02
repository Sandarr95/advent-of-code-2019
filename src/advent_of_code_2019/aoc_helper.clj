(ns advent-of-code-2019.aoc-helper
  (:require [clojure.data.csv :as csv]
            [clojure.edn :as edn]))

(defn inspect [i]
  (println i)
  i)

(defn get-input [file]
  (csv/read-csv (slurp (str "input/" file ".csv"))))

(defn list-of-numbers [input]
  (map edn/read-string input))