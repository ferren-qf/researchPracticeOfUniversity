<%@page language="java" contentType="application/x-msdownload" pageEncoding="gb2312"%>  
<%  
//得到文件名字和路径
// String filename="001.jpg";
String filename="newMedicine.apk";
// String filename  =  request.getParameter("filename");
String filepath="E:\\workMyEclipse\\workspace1210\\newMedicine\\bin\\";
// String filepath  =  "D:\\tomcat\\apache-tomcat-7.0.64\\webapps\\file\\upload\\";

//设置响应头和下载保存的文件名
response.setContentType("APPLICATION/OCTET-STREAM");
response.setHeader("Content-Disposition", "attachment;  filename=\""  +  filename  +  "\"");  
out.clear();
out = pageContext.pushBody();
//打开指定文件的流信息
java.io.FileInputStream  fileInputStream  =  new  java.io.FileInputStream(filepath  +  filename);  
 java.io.OutputStream outp = null;  
  java.io.FileInputStream in = null;  
  try  
  {  
  outp = response.getOutputStream();  
  in = new java.io.FileInputStream(filepath  +  filename);  
  
  byte[] b = new byte[1024];  
  int i = 0;  
  
  while((i = in.read(b)) > 0)  
  {  
  outp.write(b, 0, i);  
  }  
outp.flush();  
//要加以下两句话，否则会报错  
//java.lang.IllegalStateException: getOutputStream() has already been called for //this response    
out.clear();  
out = pageContext.pushBody();  
}  
  catch(Exception e)  
  {  
  System.out.println("Error!");  
//   e.printStackTrace();  
  }  
  finally  
  {  
  if(in != null)  
  {  
  in.close();  
in = null;  
}  
}
%>