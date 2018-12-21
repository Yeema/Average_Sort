# Average_Sort
  1. Goal : calculate the average of occurrences and sort them by multiple reducers
  2. Contains 2 jobs
      * Job 1:  Calculate Average
        * customized Partitioner, data type
      * Job 2:  Sort the average outputs from Job1
        * customized Partitioner, data type, key class
## Calculate average value of each word
  * The input contains many word-value pairs 
  * Speedup by Combiner 
  * Word only start by [a-z]
  
## Job1
  1. TextInputFormat
      * Converts every row of the source file 
      * LongWritable key represents the offset of the record 
      * Text value represents the entire record itself
      ![image](https://github.com/Yeema/Average_Sort/blob/master/螢幕快照%202018-12-21%20下午4.56.04.png)
  2. Use KeyValueTextInputFormat
      * Extended version of TextInputFormat 
      * Split the record with a fixed delimiter (Default: “\t”)
      * <Text, Text> pair
  3. Components
      * Mapper 
      * Partitioner
      * Combiner: merge result of same keys in map stage
      * Reducer: parse int from arg[3]
      * Customized Value Class
      * SumCountPair: <sum, count> 
      * Data type
      * Average: Double
      ![image](https://github.com/Yeema/Average_Sort/blob/master/螢幕快照%202018-12-21%20下午4.58.17.png)
## Job2
  1. Sort
      * Input is the key-value output from Job1
      * Order by average values (from big to small)
      * With words in same average, order by lexicographically in ascending order (java string compare)
      * Support multiple reducers(2~5)
      ![image](https://github.com/Yeema/Average_Sort/blob/master/螢幕快照%202018-12-21%20下午5.01.10.png)
  2. Sort by customized key class
      * Implement WritableComparable
      * Override compare function 
      * Mapper output : <SortPair, NullWritable>
  3. Total order sort
      * Assign ranges for each reducer in Paritioner
  4. Components
      * Mapper
      * Partitioner
      * Reducer 
  5. Customized Key Class
      * SortPair <word, average>
      * implements WritableComparable
      * function comapreTo: return negative value for ascending order
  6. Execution (in Average_Sort/)
      * make clean
        
        make
        
        sh execute.sh {iter_num, defualt=3}


