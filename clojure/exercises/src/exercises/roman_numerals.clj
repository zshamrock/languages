(ns exercises.roman-numerals
    (:use [clojure.contrib.def :only (defvar-)]))

(defvar- max-supported-decimal 3999)

(defvar- roman-numerals-sets
    [{:1 "I" :5 "V" :10 "X"} {:1 "X" :5 "L" :10 "C"} {:1 "C" :5 "D" :10 "M"}]
    "Array of roman numerals per decimal position in the number, i.e. 1-10 (1st), 10-100 (2nd), 100-1000 (3rd).
    Each of the set has its own represtation of 1, 5 and 10 based on the decimal position.")

(defn to 
    "Convert decimal number into roman representation. Supported numbers are from 1 till 3999 inclusive."
    [number]
    (when 
        (or (< number 0) (> number max-supported-decimal)) 
            (println (str "Supported decimal numbers are from 1 till " max-supported-decimal ". You have provided " number "."))
            (System/exit 1))

    (defvar- roman-literal (atom ""))
    (loop [n number            
            i 0]
            (when (not= n 0)
                (let [remainder (mod n 10)
                    roman-numerals (roman-numerals-sets i)]
                    (swap! roman-literal #(str %2 %1)
                        (cond 
                            (= remainder 4) (str (roman-numerals :1) (roman-numerals :5))
                            (= remainder 9) (str (roman-numerals :1) (roman-numerals :10))
                            (= remainder 5) (roman-numerals :5)
                            (< remainder 5) (apply str (repeat remainder (roman-numerals :1)))
                            (> remainder 5) (str (roman-numerals :5) (apply str (repeat (- remainder 5) (roman-numerals :1))))
                            :else ""))
                    (recur (int (/ n 10)) (inc i))))
            )            
    @roman-literal
        )
