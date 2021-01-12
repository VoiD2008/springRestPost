import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org._void.client.SyncRestClient;

public class AppMain {

  public static void main(String[] args) {

    //Startup spring context
    AbstractApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml", AppMain.class);

    //Invoke the REST clients synchronously
    SyncRestClient client = applicationContext.getBean("syncRestClient", SyncRestClient.class);
    client.getJsonData();

  }
}
