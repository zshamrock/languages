(in-ns 'user)

; defrecord-s
(defrecord Book [title author])

(let [reading (map->Book {:title "Волонтеры вечности" :author "Макс Фрай"})
      read (->Book "Чужак" "Макс Фрай")
      books [reading read]
      titles (map :title (filter (fn [book] (= (:author book) "Макс Фрай")) books))]
  (println titles)
  (assert (= ["Волонтеры вечности" "Чужак"] titles))
  )
