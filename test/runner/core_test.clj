(ns runner.core-test
  (:require [clojure.test :refer :all]
            [runner.core :refer :all]))

(def desc_with_println 
	(struct-map desc 
			:id 1230213 								 ;; should be a unique id
			:name "This is a job name" 	 ;; self explanatory
			:tasks (list #(println 1)))) ;; list of tasks

(def desc_with_calcs
	(struct-map desc 
		:id 144444
		:name "This is a calculation"
		:tasks (list #(+ 1 2) #(+ 1 2) #(+ 1 2))))

(def desc_with_exception 
	(struct-map desc
		:id 121212
		:name "This job should fail"
		:tasks (list #(throw (Exception. "This is an exception")))))

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
			(is (= (result :status) "Success"))))
	
	(testing "Testing that the correct value is outputted"
		(is (= ((execute desc_with_calcs) :output) (list 3 3 3))))

	(testing "Execute task that throws an exception"
		(let [result (execute desc_with_exception)]
			(is (= (result :output) nil))
			(is (= (result :status) "Error")))))




