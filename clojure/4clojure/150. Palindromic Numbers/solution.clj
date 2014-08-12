; 150. Palindromic Numbers
; https://www.4clojure.com/problem/150

(in-ns 'user)

(require '[clojure.test :refer [is]])

(defn palindromic-numbers [n]

  (letfn [(palindromic? [m]
            (let [s (str m)]
              (= (apply str (reverse s)) s)))

          ; TODO: first palindrome lookup must be improved, otherwise one of the test fails on timeout, because it does 10000 iterative lookups
          (find-next-palindrome-iterative [n]
            (first (filter palindromic? (iterate inc n))))

          (last-palindrome? [p]
            (every? (fn is-nine? [d] (= \9 d)) (str p)))

          (first-palidrome-of-length [l]
            (Long/parseLong (str 1 (apply str (repeat (- l 2) 0)) 1)))

          (generate-next-palindrome [p]
            (if (last-palindrome? p)
              (first-palidrome-of-length (-> p str count inc))
              (let [palindrome-str (str p)
                    palindrome-length (count palindrome-str)
                    odd-palindrome? (odd? palindrome-length)
                    i (quot palindrome-length 2)
                    ith-digit (Long/parseLong (str (get palindrome-str i)))]
                (if-not (= ith-digit 9)
                  (if odd-palindrome?
                    (Long/parseLong (str (subs palindrome-str 0 i) (inc ith-digit) (subs palindrome-str (+ i 1))))
                    (Long/parseLong (str (subs palindrome-str 0 (- i 1)) (inc ith-digit) (inc ith-digit) (subs palindrome-str (+ i 1)))))
                  (let [j (first (filter (fn [index] (not= (get palindrome-str index) \9)) (range (+ i 1) palindrome-length)))
                        k (dec (- palindrome-length j))
                        j-digit (Long/parseLong (str (get palindrome-str j)))
                        k-digit (Long/parseLong (str (get palindrome-str k)))]
                    (assert (= j-digit k-digit))
                    (Long/parseLong (str (subs palindrome-str 0 k) (inc k-digit) (apply str (repeat (- j k 1) "0")) (inc j-digit) (subs palindrome-str (+ j 1)))))
                  ))))]
    (let [next-palindrome (find-next-palindrome-iterative n)]
      (iterate generate-next-palindrome next-palindrome)
      )
    )
  )

(palindromic-numbers 10)
(palindromic-numbers 99999999)
(palindromic-numbers 1234550000)

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
