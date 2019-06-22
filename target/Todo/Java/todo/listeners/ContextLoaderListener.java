package todo.listeners;

import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import todo.context.ApplicationContext;

@WebListener
public class ContextLoaderListener implements ServletContextListener {
  static ApplicationContext applicationContext;
  
  public static ApplicationContext getApplicationContext() {
    return applicationContext;
  }
  
  @Override
  public void contextInitialized(ServletContextEvent event) {
	  try {
		  applicationContext = new ApplicationContext();
		  
		  // classpath에서 읽어옴
		  String resource = "todo/dao/mybatis-config.xml";
		  InputStream inputStream = Resources.getResourceAsStream(resource);
		  
		  SqlSessionFactory sqlSessionFactory =
				  new SqlSessionFactoryBuilder().build(inputStream);
				  
		  // SqlSessionFactory는 우리 프레임워크에서 만든 클래스가 아니므로
		  // 자동 객체 생성이 어려우므로 외부에서 객체를 생성 후 objTable에
		  // 전달한다. 그리고 setSqlSessionFactory가 자동으로 호출되어
		  // factory 객체를 MySqlTodoDao 객체에 주입되도록 한다.
		  
		  // 객체 전달
		  applicationContext.addBean("sqlSessionFacotry", sqlSessionFactory);
		  ServletContext sc = event.getServletContext();
//		  String propertiesPath =
//				  sc.getRealPath(sc.getInitParameter("contextConfigLocation"));
//		  applicationContext.prepareObjectsByProperties(propertiesPath);
		  applicationContext.prepareObjectsByAnnotation("");
		  applicationContext.injectDependency();
	  } catch(Throwable e) {
		  e.printStackTrace();
	  }
  }
  
  @Override
  public void contextDestroyed(ServletContextEvent event) {}
}
