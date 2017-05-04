# servlet001
servlet简单demo学习笔记

1.
严重: Allocate exception for servlet MyServlet
java.lang.ClassNotFoundException: com.demo

原因：web.xml需要完整路径
例如错误代码：
<servlet>
    <servlet-name>MyServlet</servlet-name>
    <servlet-class>com.demo</servlet-class>
</servlet>
我的路径为com.demo.MyServlet，而我只是写了包名。

2.
重启之后tomcat不能启动
Server Tomcat v8.0 Server at localhost failed to start.

解决：去掉注解@WebServlet("/LoginServlet")

3.servlet生命周期
构造方法-init初始化-doget／dopost-destory

4.servlet输出一个html页面(jsp)
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.print("hello servlet");
}

5.获得servlet初始化参数
web.xml:
<context-param>
  <param-name>servlet_name</param-name>
  <param-value>servlet_value</param-value>
</context-param>
MyServlet:
String str = this.getServletContext().getInitParameter("servlet_name");
		System.out.println(str);

6.页面导航
1、请求重定向
resp.sendRedirect("http://www.baidu.com");
2、foward只跳转
request.getRequestDispatcher("hello.html").forward(request, response);
3、include请求包含
request.getRequestDispatcher("hello.html").include(request, response);		

7.获取参数
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println("username:"+username+" password:"+password);
		
8.处理头信息：隐式信息

9.Enumeration获得结果集，只有两个方法。hasMoreElements／nextElement
通过它获取头信息：
		Enumeration<String> enum1 = request.getHeaderNames();
		while (enum1.hasMoreElements()) {
			String string = (String) enum1.nextElement();
			String value = request.getHeader(string);
			System.out.println("name:"+string +"  value:"+value);
		}
头信息如下：
name:host  value:localhost:8080
name:connection  value:keep-alive
name:upgrade-insecure-requests  value:1
name:accept  value:text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8
name:user-agent  value:Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_2) AppleWebKit/602.3.12 (KHTML, like Gecko) Safari/522.0
name:accept-language  value:zh-cn
name:cache-control  value:max-age=0
name:accept-encoding  value:gzip, deflate

10.响应头信息(显示图片,刷新页面时间)
response.setContentType("image/jpeg");	
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("img002.jpg");
		int len = in.available();
		byte[] buffer = new byte[len];
		in.read(buffer);
		OutputStream out = response.getOutputStream();
		out.write(buffer);
		out.flush();
		out.close();	

response.setHeader("refresh", "1");
		PrintWriter out = response.getWriter();
		out.println(new Date().toString());		

11.cookie保存在客户端的键值对
添加cookie
Cookie c = new Cookie("geek99", "www.geek99.com");
		c.setMaxAge(60*60*24*7);
		response.addCookie(c);
使用cookie
Cookie[] cs = request.getCookies();
		for (int i = 0; i < cs.length; i++) {
			System.out.println("key:"+cs[i].getName()+"value:"+cs[i].getValue());
		}

12.持久化
cookie／session
session用户当前状态，浏览器和服务器一对一
1）设置session
HttpSession session = request.getSession();
session.setAttribute("session_name", "session_value");		
2)获取session
HttpSession session = request.getSession();
String value = (String) session.getAttribute("session_name");
System.out.println("name:"+value);
关注区别：session与浏览器有关，浏览器关闭则清楚。
用途：保存登录信息在session

13.使用jdbc，发现提示如下错误
java.lang.ClassNotFoundException: org.gjt.mm.mysql.Driver
网上说解决方案为导入mysql的jar包，导入后依然提示这个错误。通过单元测试，发现没任何问题。
解决方案：(采用将jar包放在WEB-INF/lib目录下)
“如果你在Servlet里面写数据库连接的话应该在Tomcat的server.xml里面指定jdbc jar包的路径”，一下子就想明白了。因为Servlet在Tomcat上运行，需要为Tomcat指定jdbc包的路径，有两种方法：一、将jdbc包拷贝到Tomcat的lib目录下；二、在Tomcat的server.xml文件中指定jdbc包的路径，使得Tomcat在运行servlet时可以查找到com.mysql.jdbc.Driver这个class文件，不然就会提示“ClassNotFoundException”的错误。

14.jsp中el表达式，${UserSession.username}
此处找不到session的数据
原因居然是写错userSession:
Welcome,${userSession.username}

15.共享变量
变量的作用域
ServletContext整个应用程序
HttpSession当前浏览器
HttpServletRequest当前请求
测试一下：
setShareVar
ServletContext ctx = this.getServletContext();
ctx.setAttribute("ctx_name", "ctx_value");
HttpSession session = request.getSession();
session.setAttribute("session_name", "session_value");
request.setAttribute("request_name", "request_value");
getShareVar
ServletContext ctx = this.getServletContext();
String ctxValue = (String) ctx.getAttribute("ctx_name");
HttpSession session = request.getSession();
String sessionValue = (String)session.getAttribute("session_name");
String requestValue = (String)request.getAttribute("request_name");
结果：
ctxValue:ctx_value
sessionValue:session_value
requestValue:null
如果在getShareVar中加入
request.getRequestDispatcher("getShareVar").forward(request, response);
则结果：
ctxValue:ctx_value
sessionValue:session_value
requestValue:request_value
如果切换浏览器则：
ctxValue:ctx_value
sessionValue:null
requestValue:null

16.Filter过滤 ，客户端和服务端请求资源之间，起到过滤作用
session管理
		