<%@page import="java.io.OutputStream"%> 
<%@page import="java.io.PrintWriter"%> 
<%@page import="java.io.FileNotFoundException"%> 
<%@page import="java.io.File"%> 
<%@page import="java.io.FileInputStream"%> 
<%@ page contentType="text/html; charset=gb2312"%> 
<% 
//打开指定文件的流信息 
String fileName = "newMedicine.apk"; 
String filepath="C:\\"; 
System.out.println(filepath); 
FileInputStream fs = null; 
try { 
fs = new FileInputStream(filepath  + fileName); 
}catch(FileNotFoundException e) { 
e.printStackTrace(); 
return; 
} 
//设置响应头和保存文件名 
response.reset(); 
response.setContentType("application/vnd.ms-excel"); 
response.setHeader("Content-Disposition", "inline; filename=\"" + fileName + "\""); 
out.clear();
out = pageContext.pushBody();
//写出流信息 
int b = 0; 
try { 
OutputStream ops = response.getOutputStream(); 
while((b=fs.read())!=-1) { 
ops.write(b); 
} 
fs.close(); 
}catch(Exception e) { 

System.out.println("下载文件失败!"); 
} 
out.clear(); 
out = pageContext.pushBody(); 
%> 