# servlet001
servlet最简单demo

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
		out.print("<h1>hello servlet</h1>");
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