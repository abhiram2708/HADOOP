A = LOAD '/home/hadoop/pig_inputs/multiquery_A';
B = FILTER A BY $1 == 'banana';
C = FILTER A BY $1 != 'banana';
STORE B INTO '/home/hadoop/pig_inputs/output_b';
STORE C INTO '/home/hadoop/pig_inputs/output_c';
