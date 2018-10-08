(ns how-far-is-it.core)

(def R 6371)

(defn median [first second] (/ (Math/abs (- first second)) 2))

(defn sin-square [value] (Math/pow (Math/sin value) 2))

(def london {:lan 51.510 :long -0.118})
(def inverness {:lan 57.478 :long -4.225})
(def new-york {:lan 40.730 :long -73.935})
(def manchester {:lan 53.484 :long -2.245})

(def cities
  {:london london
   :inverness inverness})

(def transport
  " transport in km/h "
  {:walking 5
   :cycling 25
   :driving 50
   :flying 257})

(defn distance [from to]
       (let [flan (:lan from)
             tlan (:lan to)
             flong (:long from)
             tlong (:long to)
             a (+ (sin-square (median flan tlan)) (* (Math/cos flan) (Math/cos tlan) (sin-square (median flong tlong))))
             c (* 2 (Math/atan2 (Math/sqrt a) (Math/sqrt (- 1 a))))]
             (/ (* c R) 6.23)))

(defn pretty-print-time [value]
  (let [days (int (quot value 24))
        hours (int (mod value 24))
        rest (- value (int value))
        minutes (int (* 60 rest))]
       (str "it will take you " days " days, " hours " hours, " minutes " minutes.")))

(defn travel-time
  [from to how]
      (let [speed (how transport 10)]
           (println (pretty-print-time (/ (distance from to) speed)))))

