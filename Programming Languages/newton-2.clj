;; define a function that computes the polynomial
;; with a = 1.0, b = 0.0, c = -1.0, d = 1.0
(defn f [x] 
    (+(+(+(* -1.0 x) 1.0) (*(* 0.0 x) x)) (*(*(* 1.0 x) x) x))
)

;; define a function that computes the derivative
;; of f(x)
(defn f' [x] 
    (+(+ -1.0 (* 0.0 x)) (*(* 3.0 x) x))
)

;; define the function that improves the guesses.
;; It should use f and f'.
(defn -> [x] 
    (- x (/ (f x) (f' x)))
)

;; same as newton-1.clj except for
;; implements Newton's method using tail recursion with the 
(defn newton [iter-n x0] 
    (loop [i iter-n
         xn x0]
    (if (pos? i)
      (recur (dec i) (-> xn))
      xn ))
)

;;test it with 10 iterations
(let [x (newton 10 0.0)]
  (println (format "[10] x=%.8f, y=%8f" x (f x))))

;; test it with 20 iterations
(let [x (newton 20 0.0)]
  (println (format "[20] x=%.8f, y=%8f" x (f x))))