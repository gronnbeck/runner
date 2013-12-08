(ns runner.core)

(defstruct desc :id :name :tasks)
(defstruct job :id :input :desc_id)
(defstruct result :id :status :output)

(defn execute [job]
	(let [output (map #(%) (job :tasks))]
		(try 
			(struct-map result 
				:id (job :id)
				:status "Success" 
				:output (doall output))
		(catch Exception e  
			(struct-map result 
				:id (job :id)
				:status "Error" 
				:output nil)))))

	

