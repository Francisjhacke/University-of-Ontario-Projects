#!/usr/bin/env clojure

(require '[clojure.java.io :as io]
		 '[clojure.string :as str])

(defn list-2015 [filename]
	(let [rdr (io/reader filename) output '()]
	  (for [line (line-seq rdr)
		:let [record (str/split line #";")]]
        (and (= (re-find #"2015" (nth record 0)) "2015") (= (re-find #"CSCI" (nth record 3)) "CSCI")
            (conj output (str (nth record 3) " " (nth record 1)))
        )
	  )
	)
)

(defn get-instructors-2016 [filename]
	(let [rdr (io/reader filename)]
	  (for [line (line-seq rdr)
		:let [record (str/split line #";")]
        :when (and (not (= (nth record 19) "TBA"))
                (and (= (re-find #"2016" (nth record 0)) "2016") 
                (= (re-find #"Lecture" (nth record 18)) "Lecture")))]
        (nth record 19)
	  )
	)
)

(defn most-frequent-n [n items]
  (->> items
    frequencies
    (sort-by val)
    reverse
    (take n))
)

(let [output (distinct (list-2015 "data.csv"))]
    (doseq [item output
        :when (not (= item false))]
            (apply println item)))

(println " ")

(let [instructors (get-instructors-2016 "data.csv")]
    (doseq [instructor (most-frequent-n 10 instructors)]
        (doseq [val instructor]
            (print (format "%-30s" val)))
        (println " ")
    )
)