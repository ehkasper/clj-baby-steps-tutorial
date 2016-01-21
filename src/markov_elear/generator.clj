(ns markov-elear.generator)

(require '[clojure.set :as set])

(def example "And the Golden Grouse And the Pobble who")

(def words (clojure.string/split example #" "))

(def word-transitions (partition-all 3 1 words))

(reduce (fn [r t] (merge-with set/union r
                               (let [[a b c] t]
                                 {[a b] (if c #{c} #{})})))
          {}
          word-transitions)

(defn word-chain [word-transitions]
  (reduce (fn [r t] (merge-with set/union r
                               (let [[a b c] t]
                                 {[a b] (if c #{c} #{})})))
          {}
          word-transitions))
