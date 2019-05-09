/**
Filter rows based on condition
**/

//Note: Can also use existing csvDataFrame obtained from previous exercise.
var csvDataFrame = spark.read.option("header",true).csv("/GAB2019/Result/SchemaDF");

//Filter rows only with value "404"
val filteredData = csvDataFrame.filter(csvDataFrame("StatusCode") === 404)
//display the rows of filtered rows that satisfy the condition "404"
filteredData.show()

//To filter rows based on conditions applied on one or more columns.d//OR condition
val filteredData = csvDataFrame.filter(csvDataFrame("StatusCode") === 404 || csvDataFrame("StatusCode") === 304)
//display the rows of filtered rows that satisfy the condition "404" that including 304
filteredData.show()

filteredData.coalesce(1).write.format("com.databricks.spark.csv").option("header","true").save("/GAB2019/Result/ReducedRows");
