(ns runner.core-test
  (:require [clojure.test :refer :all]
            [runner.core :refer :all]))

(def desc_with_println (struct-map job_desc 
			:id 1230213 								;; should be a unique id
			:name "This is a job name" 	;; self explanatory
			:tasks (list #(println 1)) 							;; list of tasks
			))

;; Learning how to access structures in clojure
(deftest struct_test
	(testing "Testing if id is set to correct value"
		(is (= (desc_with_println :id) 1230213)))
	(testing "Testing if name is set to correct value"
		(is (= (desc_with_println :name) "This is a job name"))))

(deftest execute_test
	(testing "Execute task without input successfully" 
		(let [result (execute desc_with_println)]
			(is (= (result :output) (list nil)))
			(is (= (result :status) "Success")))))






