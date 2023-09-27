package org.example.UserDefinedExp;

public class SongNotFoundException extends Exception {
        public SongNotFoundException(String message) {
            super("Song Unavailable");
        }

}
