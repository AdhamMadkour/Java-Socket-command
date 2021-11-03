import org.junit.jupiter.api.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    @org.junit.jupiter.api.Test
    void Testi() throws UnknownHostException {
        assertAll(() -> assertEquals(InetAddress.getLocalHost().getHostName(), Client.main("WHO")),
                () -> assertEquals("msg", Client.main("SEND msg")));
    }
}