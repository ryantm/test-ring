(ns test-ring.core-test
  (:require [clojure.test :refer :all]
            [test-ring.core :refer :all]))

(deftest wrap-uri-decode1
  (testing "wrap-uri-decode decoding an encoded space"
    (is (= "a b" ((wrap-uri-decode :uri-decoded) {:uri "a%20b"})))))
