; 91. Graph Connectivity 
; https://www.4clojure.com/problem/91

(in-ns 'user)

(require '[clojure.test :refer [is]])

(defn connected? [graph]
  (let [g (apply merge-with #(vec (concat %1 %2)) 
                 (map #(conj {} [(first %) [(second %)]] [(second %) [(first %)]]) graph))]
    g
    )
  )

(defn- run-all-tests  []
  (is (= true (connected? #{[:a :a]})))

  (is (= true (connected? #{[:a :b]})))

  (is (= false (connected? #{[1 2]  [2 3]  [3 1]
                              [4 5]  [5 6]  [6 4]})))

  (is (= true (connected? #{[1 2]  [2 3]  [3 1]
                             [4 5]  [5 6]  [6 4]  [3 4]})))

  (is (= false (connected? #{[:a :b]  [:b :c]  [:c :d]
                              [:x :y]  [:d :a]  [:b :e]})))

  (is (= true (connected? #{[:a :b]  [:b :c]  [:c :d]
                             [:x :y]  [:d :a]  [:b :e]  [:x :a]})))
  )

(run-all-tests)
