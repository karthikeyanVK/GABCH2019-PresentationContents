/**
Save Data with header as csv
**/

import spark.implicits._

var result = sc.textFile("/GAB2019/Result/NasaCleanLogs");

var dataframe = result.toDF();    //result obtained from Exercise 1

//Note: Different ways are available to specify schema for a dataframe
var dataframeWithSchema = (dataframe.withColumn("_tmp", split($"value", " "))
        .withColumn("RequestName", $"_tmp".getItem(0)).withColumn("Date", $"_tmp".getItem(1))
        .withColumn("MethodType", $"_tmp".getItem(2)).withColumn("RequestURL", $"_tmp".getItem(3))
        .withColumn("Protocol", $"_tmp".getItem(4)).withColumn("StatusCode", $"_tmp".getItem(5))
        .withColumn("BytesCount", $"_tmp".getItem(6)).drop("_tmp").drop("value"));

//View the dataframe in command prompt itself. Dataframe will be modified with column names.
dataframeWithSchema.show();

//Save as CSV file with header. If header is not needed, specify header as false.
dataframeWithSchema.coalesce(1).write.format("com.databricks.spark.csv").option("header","true").save("/GAB2019/Result/SchemaDF");
