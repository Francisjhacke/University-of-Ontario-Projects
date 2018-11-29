#!/usr/bin/env clojure

(require '[clojure.java.io :as io]
		 '[clojure.string :as str])

(defn get-instructor-details [filename]
	(let [rdr (io/reader filename)]
	  (for [line (line-seq rdr)
		:let [record (str/split line #";")]
        :when (and (not (= (nth record 19) "TBA")) (not (= (nth record 19) "Trent University")))
        :when (= (re-find #"Lecture" (nth record 18)) "Lecture")]
            {
                :instructor (nth record 19) 
                :starthour (nth record 10)
                :startmin (nth record 11)
                :endhour (nth record 12)
                :endmin (nth record 13)
                :day (nth record 14)
                :room (nth record 15)
                :semester (nth record 0)
            }
	  )
	)
)

(defn compare-times [details]
    (for [first details
          second details
          :when (= (get first :room) (get second :room))
          :when (not= (get first :instructor) (get second :instructor))
          :when (= (get first :endhour) (get second :starthour))
          :when (= (get first :endmin) (get second :startmin))
          :when (= (get first :day) (get second :day))
          :when (= (get first :semester) (get second :semester))]
        {:one (get first :instructor) :two (get second :instructor)}
    )	
)


(let [details (get-instructor-details "2017-10-15.txt")]
    (let [matches (compare-times details)]
        (print (count matches))
    )
)