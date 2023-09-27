package UserTest;

import org.example.Song.SongsOperations;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Usertest {
    public class UserAuthenticationOperations {
        UserAuthenticationOperations ob = null;


        @BeforeEach
        public void setUp() throws SQLException {
            ob = new UserAuthenticationOperations();
        }

        @AfterEach
        public void tearDown() {
            ob = null;
        }

        @Test
        public void loginUserTest() throws SQLException {
            assertEquals(true, ob.loginUser(1, 123));
        }

        private int loginUser(int i, int i1) {
            return i;
        }
        public void loginUserTest3() throws SQLException {
            assertEquals(false, ob.loginUser(1, 000));
        }

        public void loginUserTest2() throws SQLException {
            assertEquals(true, ob.loginUser(2, 000));
        }
    }
}
