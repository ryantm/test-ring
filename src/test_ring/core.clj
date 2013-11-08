(ns test-ring.core
  (:require [ring.adapter.jetty :as jetty])
)

(defn wrap-printuri [handler]
  (fn [request]
    (println (:uri request))
    (handler request)
    )
  )

(defn handler [request]
  (if (= "/kill" (:uri request))
    (System/exit 0)
    )
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (str "Hello World: " (:uri request))
} )

(defn -main
  []
  (jetty/run-jetty (wrap-printuri handler) {:port 3001})
)

