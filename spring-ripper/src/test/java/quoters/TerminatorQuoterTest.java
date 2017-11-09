package quoters;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

public class TerminatorQuoterTest {

    ApplicationContext context;

    @Before
    public void setUp() {
        context = new ClassPathXmlApplicationContext("application-context.xml");
    }

    @Test
    public void sayQuote() throws Exception {
        Quoter bean = context.getBean(Quoter.class);
        bean.sayQuote();
    }

    @Test
    public void propertyAppContext() throws Exception {
        PropertyFileApplicationContext propertiesContext = new PropertyFileApplicationContext("context.properties");
        Quoter bean = propertiesContext.getBean(Quoter.class);
        bean.sayQuote();
    }

}