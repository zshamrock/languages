(ns bowling-kata.core-test
  (:use midje.sweet)
  (:require [bowling-kata.core :as core]))

(facts "bowling score"
       (core/rolls (repeat 20 0)) => 0 ; no rolls
       (core/rolls [1 4 4 5 6 4 5 5 10 0 1 7 3 6 4 10 2 8 6]) => 133 ; sample game
       (core/rolls [4 6 7 3 2 8 5 5 6 4 9 1 5 5 3 7 8 2 8 2 7]) => 160 ; all spares
       (core/rolls (repeat 12 10)) => 300 ; perfect game
       )
