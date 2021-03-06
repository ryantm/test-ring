(ns test-ring.core
  (:require [ring.adapter.jetty :as jetty]
            [ring.util.codec :as codec]))

(defn wrap-uri-decode [handler]
  (fn [request]
    (handler (assoc request :uri-decoded (codec/url-decode (:uri request))))))

(defn wrap-printuri [handler]
  (fn [request]
    (println (:uri request))
    (handler request)))

(defn handler [request]
  (if (= "/kill" (:uri request))
    (System/exit 0)
    )
  {:status 200
   :headers {"Content-Type" "text/html;charset=utf-8"}
   :body (str "Hello World: " (:uri-decoded request))})

(defn -main
  []
  (jetty/run-jetty (wrap-uri-decode (wrap-printuri handler)) {:port 3001}))
