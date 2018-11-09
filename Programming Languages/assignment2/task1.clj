#!/usr/bin/env clojure

(require '[clojure.java.io :as io]
		 '[clojure.string :as str])

(defn parsedata [filename]
	(let [rdr (io/reader filename) output '()]
	  (for [line (line-seq rdr)
		:let [record (str/split line #";")]]
        (and (= (nth record 0) "201709") (= (re-find #"CSCI" (nth record 3)) "CSCI")
            (conj output (str (nth record 3) " " (nth record 1)))
        )
	  )
	)
)

(let [output (distinct (parsedata "data.csv"))]
    (doseq [item output
        :when (not (= item false))]
            (apply println item))
)
