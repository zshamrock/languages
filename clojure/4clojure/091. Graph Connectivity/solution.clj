; 91. Graph Connectivity 
; https://www.4clojure.com/problem/91

(in-ns 'user)

(require '[clojure.test :refer [is]])

(defn connected? [graph]
  ; do transform input graph into the one where managing connections between nodes is much easier: {:x [:y :z], :y [:x], :z [:x]}
  (let [g (apply merge-with #(vec (concat %1 %2)) 
                 (map #(conj {} [(first %) [(second %)]] [(second %) [(first %)]]) graph))]
    (let [start-node (rand-nth (keys g)) ; start from any node, doesn't matter
          visited  (atom #{}) 
          ]
      (letfn [(collect-all-connected-nodes [node]
                (doseq [adjacent-node (get g node)]
                  (when-not (contains? @visited adjacent-node)
                    (swap! visited conj adjacent-node)
                    (collect-all-connected-nodes adjacent-node))
                  ))
              ]
        (collect-all-connected-nodes start-node))
      (= (count @visited) (count (keys g))))
    )
  )

(is (= {:e  [:b], :d  [:c :a], :a  [:x :b :d], :b  [:c :a :e], :c  [:b :d], :y  [:x], :x  [:y :a]} 
       (connected? #{[:a :b]  [:b :c]  [:c :d]
                     [:x :y]  [:d :a]  [:b :e]  [:x :a]})))

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
