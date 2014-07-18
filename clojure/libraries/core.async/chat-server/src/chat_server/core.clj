(ns chat-server.core
  (:require [clojure.core.async :as async :refer :all])
  )

(chan)


(chan 10)


(let [c (chan)]
  (close! c)
  )

(def c (chan 10))

(>!! c "Monday")
(>!! c "Tuesday")

(let [c (chan 10)]
  (>!! c "hello") 
  (assert (= "hello" (<!! c)))
  (close! c))  

(<!! c)


(let [c (chan)]
  (go (>! c "hello"))
  (assert (= "hello" (<!! (go (<! c)))))
  (close! c)
  )

(let [c (chan)]
  (thread (>!! c "hello"))
  (assert (= "hello" (<!! c)))
  (close! c)
  )

(defn read-from [ch]
  (<!! c)
  )


(let [c1 (chan)
      c2 (chan)]
  (thread (while true 
            (let [[v ch] (alts!! [c1 c2])]
              (println "Read" v "from" ch))))
  (>!! c1 "hi")
  (>!! c2 "there"))

(let [c1 (chan)
      c2 (chan)]
  (go (while true
        (let [[v ch] (alts! [c1 c2])]
          (println "Read" v "from" ch))))

  (go (>! c1 "hi"))
  (go (>! c2 "there")))

(<!! tc)

(let [n 1000
      cs (repeatedly n chan)
      begin (System/currentTimeMillis)]
  (doseq [c cs] (go (>! c "hi")))
  (dotimes [i n]
    (let [[v c] (alts!! cs)]
      (assert (= "hi" v))))
  (println "Read" n "msgs in" (- (System/currentTimeMillis) begin) "ms"))


(let [t (timeout 100)
      begin (System/currentTimeMillis)]
  (<!! t)
  (println "Waited" (- (System/currentTimeMillis) begin)))


(let [c (chan)
      begin (System/currentTimeMillis)]
  (alts!! [c (timeout 100)])
  (println "Gave up after" (- (System/currentTimeMillis) begin)))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
