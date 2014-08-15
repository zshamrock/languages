; 150. Palindromic Numbers
; https://www.4clojure.com/problem/150

(in-ns 'user)

(require '[clojure.test :refer [is]])

(defn palindromic-numbers [n]

  (letfn [(all-nines? [p]
            (every? (fn nine? [d] (= \9 d)) (str p)))

          (first-palidrome-of-length [l]
            (Long/parseLong (str 1 (apply str (repeat (- l 2) 0)) 1)))

          ; one solution which works the same way whether the input 'p' is a palindrome or not
          (generate-next-palindrome [n]
            (if (all-nines? n)
              (first-palidrome-of-length (-> n str count inc))
              
              (let [n-str (str n)
                    n-length (count n-str)
                    odd-n? (odd? n-length)
                    mid-pos (quot n-length 2)
                    mid-digit (Long/parseLong (str (get n-str mid-pos)))
                    left-str (subs n-str 0 mid-pos)
                    reverse-left-str (clojure.string/reverse left-str)
                    right-str (subs n-str (if odd-n? (+ mid-pos 1) mid-pos))
                    left-num (Long/parseLong left-str)
                    reverse-left-num (Long/parseLong reverse-left-str)
                    right-num (Long/parseLong right-str)]
                (if (> reverse-left-num right-num)
                  (Long/parseLong (str left-str (if odd-n? mid-digit "") reverse-left-str))
                  (if (all-nines? left-num)
                    (first-palidrome-of-length (-> left-str count (* 2) inc))
                    (let [range-start (if odd-n? mid-pos (- mid-pos 1))
                          i (first (filter (fn not-nine-at? [pos] (not= (get left-str pos) \9)) (range range-start -1 -1)))
                          ith-digit (Long/parseLong (get left-str i)) 
                          new-left-str (str (subs left-str 0 i) (inc ith-digit) (apply str (repeat (- range-start i) 0)))]
                      (Long/parseLong
                        (if odd-n?
                          (apply str new-left-str (reverse (butlast new-left-str)))
                          (str new-left-str (clojure.string/reverse new-left-str)))))))
                )))]
    (iterate generate-next-palindrome (generate-next-palindrome n))))

(palindromic-numbers 10)
(palindromic-numbers 99999999)
(palindromic-numbers 1234550000)

(first (palindromic-numbers 57989))

(defn- run-all-tests []
  (is (= (take 26 (palindromic-numbers 0))
         [0 1 2 3 4 5 6 7 8 9 
          11 22 33 44 55 66 77 88 99 
          101 111 121 131 141 151 161])) 

  (is (= (take 16 (palindromic-numbers 162))
         [171 181 191 202 
          212 222 232 242 
          252 262 272 282 
          292 303 313 323]))

  (is (= (take 6 (palindromic-numbers 1234550000))
         [1234554321 1234664321 1234774321 
          1234884321 1234994321 1235005321]))

  (is (= (first (palindromic-numbers (* 111111111 111111111)))
         (* 111111111 111111111)))

  (is (= (set (take 199 (palindromic-numbers 0)))
         (set (map #(first (palindromic-numbers %)) (range 0 10000)))))

  (is (= true 
         (apply < (take 6666 (palindromic-numbers 9999999)))))

  (is (= (nth (palindromic-numbers 0) 10101)
         9102019))
  )

(time (run-all-tests))
