//Create a new dataframe with column name.
var jsonDataFrame = Seq((0,"Male","Yes"), (1, "Female", "No")).toDF("total_bill","gender","smoker");
jsonDataFrame.show();

//Delete a column
val deletedColumn = jsonDataFrame.drop(jsonDataFrame(("smoker")))
deletedColumn.show();

//Delete multiple columns
var multipleCols = Seq((0,"Male","Yes"), (1, "Female", "No")).toDF("total_bill","gender","smoker");
var multipleColumnDeletion = multipleCols.drop("smoker", "gender");
multipleColumnDeletion.show();

//Add a column to the existing dataframe
var addDF = deletedColumn.withColumn("AddedNew", jsonDataFrame("total_bill"))
addDF.show();

//rename column of existing dataframe
addDF = addDF.withColumnRenamed("AddedNew","Renamed")
addDF.show();

//Read csv file
var dataframe = spark.read.option("header",false).option("delimiter"," ").format("csv").load("/GAB2019/Result/NasaCleanLogs");

//Print first n rows of dataframe
dataframe.take(10).foreach(println)

//Read json file
val dataload = spark.read.format("json").load("/Data/Spark/Resources/People.json")
dataload.show()

//Get the sum of specific column
val sumColumn = dataload.agg(sum("age").as("sumColumn"))
sumColumn.show();

Reference Links: https://spark.apache.org/docs/2.2.0/api/scala/index.html#package 
