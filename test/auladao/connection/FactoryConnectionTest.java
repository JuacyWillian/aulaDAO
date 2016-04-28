package auladao.connection;

import java.sql.Connection;
import junit.framework.TestCase;
import org.junit.Test;

public class FactoryConnectionTest extends TestCase{
    
    @Test
    public void testCreate() {
        System.out.print("testing FactoryConnection.create()");
        FactoryConnection fabrica = FactoryConnection.getInstance();
        Connection result = fabrica.create();
        assertNotNull(" --> fail", result);
        System.out.println(" --> sucessfull");
    }
}
