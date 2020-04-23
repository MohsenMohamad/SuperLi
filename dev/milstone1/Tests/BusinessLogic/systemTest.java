package BusinessLogic;

import org.junit.Test;

import static org.junit.Assert.*;

public class systemTest {

    @Test
    public void checkEmailExist() {
        system controller = new system();
        controller.Register("Dani@gmail.com","Dd123456");
        String output = controller.CheckEmailExist("Dani@gmail.com");
        assertEquals(output,"Exist");
    }

    @Test
    public void register() {
        system controller = new system();
        String output = controller.Register("Dan@gmail.com","Dd123456");
        assertEquals(output,"Done");
    }

    @Test
    public void Login(){
        system controller = new system();
        controller.Register("Daniel@gmail.com","Dd123456");
        String output = controller.Login("Daniel@gmail.com","Dd123456");
        assertEquals(output,"Done");
    }
}