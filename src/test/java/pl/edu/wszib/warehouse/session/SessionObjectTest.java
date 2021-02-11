package pl.edu.wszib.warehouse.session;


import org.junit.Assert;
import org.junit.Test;

public class SessionObjectTest {

    @Test
    public void getInfoTest(){
        SessionObject sessionObject = new SessionObject();
        String info = "abc";

        Assert.assertNull(sessionObject.getInfo());
        sessionObject.setInfo(info);
        Assert.assertEquals(sessionObject.getInfo(), info);
        Assert.assertNull(sessionObject.getInfo());
    }

}
