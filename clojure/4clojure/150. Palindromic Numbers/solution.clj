; 150. Palindromic Numbers
; https://www.4clojure.com/problem/150

(in-ns 'user)

(require '[clojure.test :refer [is]])

(defn palindromic-numbers [n]

  (letfn [(palindromic? [m]
            (let [s (str m)]
              (= (apply str (reverse s)) s))
            )

          (find-first-palindrome [n]
            (first (filter palindromic? (iterate inc n)))  
            )

          (last-palindrome? [p]
            (every? (fn is-nine? [d] (= \9 d)) (str p))
            )

          (first-palidrome-of-length [l]
            (if (= l 2)
              11
              (Long/parseLong (str 1 (apply str (repeat (- l 2) 0)) 1))
              )
            )
          ]
    (let [next-palindrome (find-first-palindrome n)]
      (is (last-palindrome? 9999))
      (is (last-palindrome? 9))
      (is (not (last-palindrome? 91)))
      (is (= (first-palidrome-of-length 2) 11))
      (is (= (first-palidrome-of-length 3) 101))
      (is (= (first-palidrome-of-length 5) 10001))

      next-palindrome
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
         (set (map #(first (__ %)) (range 0 10000)))))

  (is (= true 
         (apply < (take 6666 (palindromic-numbers 9999999)))))

  (is (= (nth (palindromic-numbers 0) 10101)
         9102019))
  )

(run-all-tests)
