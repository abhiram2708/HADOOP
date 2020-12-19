USE kalyan;

DROP TABLE IF EXISTS test;

CREATE TABLE IF NOT EXISTS test ( name string , id int ) 
PARTITIONED BY ( course string , year int)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY '\t'
LINES TERMINATED BY '\n'
STORED AS TEXTFILE;

LOAD DATA LOCAL INPATH '${env:HOME}/work/hive_inputs/student_cse_1.txt' OVERWRITE INTO TABLE test PARTITION(course = 'cse', year = 1);

LOAD DATA LOCAL INPATH '${env:HOME}/work/hive_inputs/student_cse_2.txt' OVERWRITE INTO TABLE test PARTITION(course = 'cse', year = 2);

SELECT * FROM test WHERE course = 'cse';
