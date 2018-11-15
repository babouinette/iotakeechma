(ns eff.iota-util
  (:require
    [cljs-iota.api :as iota-api]
    [cljs-iota.core :as iota]
    [cljs-iota.utils :as iota-utils]
    [cljs-iota.valid :as iota-valid]
    [promesa.async-cljs :refer-macros  [async]]
    [promesa.core :as p]
    [reagent.core :as r]
    ))

(def iota-instance  (iota/create-iota  "http://52.232.87.40:80"))

(def comments (r/atom nil ) )

(defn get-address
  "Get the address corresponding from a seed."
  [seed]
  (iota-api/get-new-address iota-instance seed {:total 1} (fn [err res] (println "Address created: " (first res) ) (first res) ))
  )

(def comment-seed  "99OVERJOYEDSTRATEGYDAYTIMEOVERCOATSIERRAHAZARDDRAGONFLYPREHEATEDCHEWINGEXEMPLIFY9")
(def comment-address  (get-address comment-seed))

(defn valid-seed?
  "Returns true if a seed is valid."
  [seed]
  (iota-valid/trytes? iota-instance seed 81)  
  )

(defn to-trytes
  "Encode a message to trytes."
  [message]
  (iota-utils/to-trytes iota-instance message))

(defn from-trytes
  "Decode a message from trytes."
  [trytes]
  (iota-utils/from-trytes iota-instance trytes)
  )

(defn find-transactions
  "Search transactions associated with an address and return a vector of hashes" 
  [address callback]
  (println  "Searching for transactions: ")
  (iota-api/find-transactions iota-instance  {:addresses (list address)} callback))

(defn transfers  [message]
  [{:address comment-address :value 0 :message message}]
  )

(defn message-from-object  [object]
  (as->  object n
    (:signature-message-fragment n )
    (clojure.string/join  ""  (drop-last n) )
    (from-trytes n)
    {:timestamp (:timestamp object ) :comment  n }
    ) )

(defn get-objects-callback  [err vector-of-objects]
  (let  [messages  (map message-from-object vector-of-objects)
         sorted-messages  (reverse  (sort-by first messages) )]
    (reset! comments sorted-messages)
    )) 

(defn find-transactions-callback  [err vector-of-hashes]
  (let  [list-of-hashes  (apply list vector-of-hashes)]
    (prn  "Transaction hashes found" list-of-hashes)
    (iota-api/get-transactions-objects iota-instance list-of-hashes get-objects-callback)
    ))

(defn get-comments  []
  (find-transactions comment-address find-transactions-callback)
  )

(defn send-comment  [message callback]
  (prn  "MESSAGE SENT TO TANGLE")
  (iota-api/send-transfer iota-instance comment-seed 3 14  (transfers message)  callback)
  )
