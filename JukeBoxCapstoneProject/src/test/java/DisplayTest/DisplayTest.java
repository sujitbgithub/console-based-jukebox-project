package DisplayTest;

import org.example.Song.SongsOperations;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import org.example.Song.SongsOperations;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DisplayTest {
        SongsOperations obj=null;


        @BeforeEach
        public void setUp1() throws SQLException, ClassNotFoundException {
            obj=new SongsOperations();
        }
        @AfterEach
        public void tearDown1()
        {
            obj=null;
        }

        @Test
        public void displayAllSongTest()
        {
            assertEquals(8,obj.displayAllSong().size());       ;
        }



    }

