; 177. Balancing Brackets
; https://www.4clojure.com/problem/177
(in-ns 'user)

(require '[clojure.test :refer [is]])

(defn balanced? [c]
  (let [open-brackets "([{"
        close-brackets ")]}"
        brackets-mapping (into {} (map (fn [open-bracket close-bracket] [close-bracket open-bracket]) open-brackets close-brackets))
        ]
    (loop [brackets []
           code c]

      (let [ch (first code)]
        (cond 
          (not (seq code))
          (empty? brackets)

          (some #{ch} "[({") ; open bracket
          (recur (conj brackets ch) (rest code))

          (some #{ch} "])}") ; close bracket
          (when (= (get brackets-mapping ch) (peek brackets))
            (recur (pop brackets) (rest code))
            )

          :default
          (recur brackets (rest code))
          )))))

(defn- run-all-tests []
  (is (balanced? "This string has no brackets."))
  (is (balanced? "class Test {
            public static void main(String[] args) {
              System.out.println(\"Hello world.\");
            }}"))
  (is (not (balanced? "(start, end]")))
  (is (not (balanced? "())")))
  (is (not (balanced? "[ { ] } ")))
  (is (balanced? "([]([(()){()}(()(()))(([[]]({}()))())]((((()()))))))"))
  (is (not (balanced? "([]([(()){()}(()(()))(([[]]({}([)))())]((((()()))))))")))
  (is (not (balanced? "[")))
  )

(run-all-tests)
