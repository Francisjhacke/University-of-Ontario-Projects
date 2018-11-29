#!/usr/bin/env clojure

(require '[clojure.java.io :as io]
		 '[clojure.string :as str])

(defn get-csci-courses [filename]
	(let [rdr (io/reader filename)]
	  (for [line (line-seq rdr)
		:let [record (str/split line #";")]]
        (and (= (nth record 0) "201709") (= (re-find #"CSCI" (nth record 3)) "CSCI")
            {:code (nth record 3) :name (nth record 1)}
        )
	  )
	)
)

(let [courses (sort-by :code (distinct (get-csci-courses "2017-10-15.txt")))]
    (doseq [item courses
        :when (not (= item false))]
            (println (get item :code) "" (get item :name))
    )
)
