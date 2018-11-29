#!/usr/bin/env clojure

(require '[clojure.java.io :as io]
		 '[clojure.string :as str])

(defn list-2015 [filename]
	(let [rdr (io/reader filename)]
	  (for [line (line-seq rdr)
		:let [record (str/split line #";")]]
        (and (= (re-find #"2015" (nth record 0)) "2015") (= (re-find #"CSCI" (nth record 3)) "CSCI")
            {:code (nth record 3) :name (nth record 1)}
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

(let [output (sort-by :code (distinct (list-2015 "2017-10-15.txt")))]
    (doseq [item output
        :when (not (= item false))]
            (println (get item :code) "" (get item :name))
    )
)

(println " ")

(let [instructors (get-instructors-2016 "2017-10-15.txt")]
    (doseq [instructor (most-frequent-n 10 instructors)]
        (doseq [val instructor]
            (print (format "%-30s" val)))
        (println " ")
    )
)