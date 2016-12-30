# researchPracticeOfUniversity
version1.0
this is my university research practice，“android of study traditional Chinese medicine" (这是我大学科研实践的作品,基于中医药知识的学习系统)

### 使用：
 一、启动服务端
1.必须用myEclipse 
（因为EclipseEE与myEclipse两软件在服务端项目的文件上设计不同，不可直接互相导入，否者会   报错）
 配上tomcat（7.0）
 2.导入版本newMedinice11.0中的server2项目
3.run as（运行）

二、数据库
导入版本newMedinice11.0中sql文件至Mysql

 三、启动客户端
    1、确保数据库导入好，不然可能会出现界面显示问题
    2、配上android环境，导入到已搭建android开发环境的MyEclipse中
   3、run as （运行）
   4、如果上述都成功，则可以自己注册账户，登陆使用。
   5、需要登陆成功才可使用，本组是这样设计的
 
### 特别：
 一、可以在本地（自己PC），局域网(校园网)中使用进行
    1、如果要进行局域网（校园网）测试，修改客户端com.jxutcm.business.util包中的ConfigurationFile
    中ip为校园网ip（校园网ip会变化，变化后更改为对应的校园网ip，再重新编译！！！）
    2、同理要进行本地（自己电脑）测试，修改客户端com.jxutcm.business.util包中的ConfigurationFile中ip为http://10.0.2.2:8080/server/Servlet（默认配置为本地测试）
     
 二、可以在本地（自己PC），局域网(校园网)中下载客户端文件
     1、逻辑设计是：在服务端webRoot文件夹下有几个jsp文件，它具备浏览器访问该jsp地址就会有自动下载（地址如：http://127.0.0.1:8080/server/downloadbs.jsp），如果需测试该功能则修改相应路径（downloadbd.jsp有用）
   
### 其他：
 一、左右滑动功能其实是别人（似乎是外国）写的静态库，即    com.jeremyfeninstein.slidingmenu.lib**包，我看过有几个使用过这个静态库。该库经过稍微      修改后配入系统。
 
 二、除一所述外，其他所有功能，代码都是本组设计、编写、实现。
 
 三、导入后可能因我的编写java的字符集环境两者不同，造成注释有中文乱码问题，最简单的方法     对照txt的java文件手动修改吧
 
 四、完美在android 4.0以上版本运行
 
 五、数据库中数据不是本组的所做的内容，所以进行了删减
