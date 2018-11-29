(ns csci3055u_a3.core (:require [cheshire.core :refer :all]))


(defn get-max [grades]
  (reduce max (vec
    (for [student (get grades :students)]
      (get student :grade)
    )))
)

(defn get-min [grades]
  (reduce min (vec
    (for [student (get grades :students)]
      (get student :grade)
    )))
)

(defn get-avg [grades]
  (/ (reduce + (vec
    (for [student (get grades :students)]
      (get student :grade)
    ))) (count (get grades :students)))
)

(defn get-best-student [grades]
  (for [student (get grades :students) :when (== (get-max grades) (get student :grade))]
    (get student :name)
  )
)

(defn main [fileName]
  (let [grades (parse-string (slurp fileName) true)]
    (println (get grades :title))
    (println "Range:" (format "%.2f" (get-min grades)) "to" (format "%.2f" (get-max grades)))
    (println "Average:" (format "%.2f" (get-avg grades)))
    (print "Best student: ")
    (apply print (get-best-student grades))
    (println "")
  )
)