(ns runner.core)

(defstruct desc :id :name :tasks)
(defstruct job :id :input :desc_id)
(defstruct result :id :status :output)

(defstruct _status :success :warning :error)

(def status (struct-map _status
	:success "Success"
	:warning "Warning"
	:error "Error"))

(defn executeTasks [id tasks]
	(try 
		(struct-map result 
			:id id
			:status (status :success)
			:output (doall tasks))
		(catch Exception e  
			(struct-map result 
				:id id
				:status (status :error)
				:output nil))))

(defn execute [job]
	(let [tasks (map #(%) (job :tasks))]
		(executeTasks (job :id) tasks)))



	

