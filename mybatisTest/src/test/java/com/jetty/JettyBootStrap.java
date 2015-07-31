package com.jetty;


//import org.eclipse.jetty.server.Connector;
//import org.eclipse.jetty.server.Server;
//import org.eclipse.jetty.server.nio.SelectChannelConnector;
//import org.eclipse.jetty.webapp.WebAppClassLoader;
//import org.eclipse.jetty.webapp.WebAppContext;
import org.springframework.util.Log4jConfigurer;

/**
 * Jetty嵌入式服务器引导程序
 * @see 本类用于启动Jetty嵌入式服务器
 * @see 本类部分整理自https://github.com/springside/springside4/wiki/Jetty,感谢江南白衣
 * @create Oct 5, 2013 12:08:55 PM
 * @author 玄玉<http://blog.csdn.net/jadyer>
 */
public class JettyBootStrap {
	private static final int PORT = 8088;
	private static final String CONTEXT_PATH = "/jadyer";
	private static final String WEBAPP_PATH = "F:/Tool/Code/JavaSE/MyJettyDemo/WebRoot";
	//等价于String CLASS_PATH = "F:/Tool/Code/JavaSE/MyJettyDemo/WebRoot/WEB-INF/classes"
	private static final String CLASS_PATH = JettyBootStrap.class.getResource("/").getPath();
	
	/**
	 * 创建用于开发运行调试的JettyServer
	 * @param port        访问服务器的端口
	 * @param contextPath 访问服务器的地址
	 * @param webAppPath  Web应用的目录(需指向到WebRoot目录下)
	 */
//	private static Server createServer(int port, String contextPath, String webAppPath){
//		Server server = new Server();
//		//设置在JVM退出时关闭Jetty的钩子
//		//这样就可以在整个功能测试时启动一次Jetty,然后让它在JVM退出时自动关闭
//		server.setStopAtShutdown(true);
//		SelectChannelConnector connector = new SelectChannelConnector();
//		connector.setPort(port);
//		//解决Windows下重复启动Jetty不报告端口冲突的问题
//		//在Windows下有个Windows + Sun的connector实现的问题,reuseAddress=true时重复启动同一个端口的Jetty不会报错
//		//所以必须设为false,代价是若上次退出不干净(比如有TIME_WAIT),会导致新的Jetty不能启动,但权衡之下还是应该设为False
//		connector.setReuseAddress(false);
//		server.setConnectors(new Connector[]{connector});
//		//为了设置reuseAddress=true所以创建Connector,否则直接new Server(port)即可,通过查看Server源码发现,二者是等效的
//		//不过使用Connector的好处是可以让Jetty监听多个端口,此时创建多个绑定不同端口的Connector即可,最后一起setConnectors
//		//Server server = new Server(port);
//		//server.setStopAtShutdown(true);
//		WebAppContext context = new WebAppContext(webAppPath, contextPath);
//		//解决Windows环境下Lock静态文件的问题
//		//Jetty运行时会锁住js、css等文件,导致不能修改保存,解决办法是修改webdefault.xml中的useFileMappedBuffer=false
//		//这里试了很多遍都没成功,后来注释掉这里的setDefaultsDescriptor(),也删除项目中的webdefault-windows.xml
//		//再重启应用,竟然可以修改保存了,后来又反复重启又试了几次竟然都可以修改保存,很是奇怪
//		//context.setDefaultsDescriptor("F:/Tool/Code/JavaSE/MyJettyDemo/test/webdefault-windows.xml");
//		server.setHandler(context);
//		return server;
//	}
//	
//	
//	/**
//	 * 快速重新启动Application
//	 * @see 通常用Main函数启动JettyServer后,若改动项目的代码,那就需要停止再启动Jetty
//	 * @see 虽免去了Tomcat重新打包几十兆的消耗，但比起PHP完全不用重启来说还是慢,特别是关闭,启动一个新的JVM,消耗不小
//	 * @see 所以我们可以在Main()中捕捉到回车后调用此函数,即可重新载入应用(包括Spring配置文件)
//	 * @param server    当前运行的JettyServer实例
//	 * @param classPath 当前运行的Web应用的classpath
//	 */
//	private static /*synchronized*/ void reloadContext(Server server, String classPath) throws Exception{
//		WebAppContext context = (WebAppContext)server.getHandler();
//		System.out.println("Application reloading..开始");
//		context.stop();
//		WebAppClassLoader classLoader = new WebAppClassLoader(context);
//		classLoader.addClassPath(classPath);
//		context.setClassLoader(classLoader);
//		//根据给定的配置文件初始化日志配置(否则应用重载后日志输出组件就会失效)
//		Log4jConfigurer.initLogging(classPath + "/log4j.properties");
//		context.start();
//		System.out.println("Application reloading..完毕");
//	}
	
	
//	/**
//	 * 解决JSPTaglib的tld文件不能找到的问题
//	 * @see 设置除jstl-*.jar外其他含tld文件的jar包的名称,jar名称不需要版本号,如sitemesh、shiro-web
//	 * @see 这是Jetty一个很麻烦的设计,详细介绍见https://github.com/springside/springside4/wiki/Jetty
//	 * @see 如果tld文件在当前的classloader中则可顺利载入,若在parent的classloader里就会因为安全原因而拒绝扫描
//	 */
//	@SuppressWarnings("unused")
//	private static void setTldJarNames(Server server, String... jarNames){
//		WebAppContext context = (WebAppContext)server.getHandler();
//		//这里用的是com.google.common.collect.Lists
//		List<String> jarNameExprssions = Lists.newArrayList(".*/jstl-[^/]*\\.jar$", ".*/.*taglibs[^/]*\\.jar$");
//		for(String jarName : jarNames){
//			jarNameExprssions.add(".*/" + jarName + "-[^/]*\\.jar$");
//		}
//		//这里用的是org.apache.commons.lang3.StringUtils
//		context.setAttribute("org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern", StringUtils.join(jarNameExprssions, '|'));
//	}
	
	
//	public static void main(String[] args) {
//		Server server = createServer(PORT, CONTEXT_PATH, WEBAPP_PATH);
//		//setTldJarNames(server, new String[]{"sitemesh", "spring-webmvc", "shiro-web"});
//		try {
//			//启动Jetty
//			server.start();
//			System.out.println("Server running at http://127.0.0.1:" + PORT + CONTEXT_PATH);
//			System.out.println("Hit Enter to reload the application quickly");
//			//等待用户键入回车重载应用
//			while(true){
//				char c = (char)System.in.read();
//				if(c == '\n'){
//					reloadContext(server, CLASS_PATH);
//				}
//			}
//		} catch (Exception e) {
//			System.out.println("Jetty启动失败,堆栈轨迹如下");
//			e.printStackTrace();
//			System.exit(-1);
//		}
//	}
}