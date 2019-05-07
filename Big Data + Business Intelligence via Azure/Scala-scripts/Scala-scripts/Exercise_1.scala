/**
Clean & Save refined data as text file
**/

//Read a file
var input = sc.textFile("/GAB2019/Data/NASA_Access_Log");

//Define a method to clean the data
def editData(line: String):String = {
    var str = line;
    str = str.replace(" -","");
    str = str.replace(" ["," ");
    str = str.replace("] "," ");
    str = str.replace("\"","");
   return str
}

//Clean data by sending each line of the content to editData method
var result = input.map(line => editData(line));

//To write as single part, use coalesce(1).
result.coalesce(1).saveAsTextFile("/GAB2019/Result/NasaCleanLogs");
