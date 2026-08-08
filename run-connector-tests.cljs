#!/usr/bin/env nbb
;; nbb --classpath "src:test:../connector/src" run-connector-tests.cljs
(require '[clojure.test :as t] 'google-chat.connector-test)
(let [{:keys [fail error]} (t/run-tests 'google-chat.connector-test)]
  (js/process.exit (if (pos? (+ fail error)) 1 0)))
