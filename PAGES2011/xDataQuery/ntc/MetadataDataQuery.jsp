<html>
<head>
<%@ page 
import = "java.io.*"
import = "java.lang.*"
import = "java.sql.*"
%>
<title>
Test PROGRESS DB Connection
</title>
</head>
<body>
<h1>Test PROGRESS DB Connection</h1>
<% 
String url = "jdbc:jdbcprogress:T:cpg-newport:9500:mismon";//LIVE
String user = "newport";
String password = "newport";
ResultSet results;
PreparedStatement sql;



try
{
	
	
Class.forName("com.progress.sql.jdbc.JdbcProgressDriver"); 

Connection dbconn = DriverManager.getConnection(url, user, password);
System.out.println(url);

sql = dbconn.prepareStatement("SELECT * FROM _file");
results = sql.executeQuery();
while(results.next())
{
System.out.println(results.getString(1));
System.out.println(results.getString(2));
}


				 
				       dbconn.close();
}

catch (SQLException s)
{
	System.out.println("SQLException: "+s.getMessage());
	}
catch (ClassNotFoundException err)
{
		System.out.println("ClassNotFoundException: "+err.getMessage());
		}
	
%>
</body> 
</html>
