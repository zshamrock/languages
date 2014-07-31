(defn phone-mnemonics
  ([phone]
   (phone-mnemonics (str phone) "")
   )

  ([phone mnemonic]
   (let [mappings {\2 "ABC" \3 "DEF" \4 "GHI" \5 "JKL" \6 "MNO" \7 "PQRS" \8 "TUV" \9 "WXYZ"}]
     (doseq [letter (mappings (first phone))]
       (if (= (count phone) 1)
         (println (str letter mnemonic))
         (phone-mnemonics (next phone) (str letter mnemonic))))))

  )

(phone-mnemonics 232)
