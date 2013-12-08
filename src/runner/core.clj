(ns runner.core)

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))

(defstruct job_desc :id :name :tasks)
(defstruct job :id :input :job_desc_id)
(defstruct result :id :status :output)

(defn execute [job]
	(struct-map result 
		:id (job :id)
		:status "Success"
		:output (map #(%) (job :tasks)))) 

