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