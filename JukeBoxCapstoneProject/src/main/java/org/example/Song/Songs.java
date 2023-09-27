package org.example.Song;
import java.sql.*;

public class Songs {


    private int S_id;
    private String S_name;
    private String S_genre;
    private String S_artist;
    private String S_album;
    private Time S_duration;
    private String S_Filepath;

    public Songs(int s_id, String s_name, String s_genre, String s_artist, String s_album, Time s_duration, String s_Filepath) {
        S_id = s_id;
        S_name = s_name;
        S_genre = s_genre;
        S_artist = s_artist;
        S_album = s_album;
        S_duration = s_duration;
        S_Filepath = s_Filepath;
    }

    public Songs(String songId, String songName, String artist) {
    }

    public int getS_id() {
        return S_id;
    }

    public void setS_id(int s_id) {
        S_id = s_id;
    }

    public String getS_name() {
        return S_name;
    }

    public void setS_name(String s_name) {
        S_name = s_name;
    }

    public String getS_genre() {
        return S_genre;
    }

    public void setS_genre(String s_genre) {
        S_genre = s_genre;
    }

    public String getS_artist() {
        return S_artist;
    }

    public void setS_artist(String s_artist) {
        S_artist = s_artist;
    }

    public String getS_album() {
        return S_album;
    }

    public void setS_album(String s_album) {
        S_album = s_album;
    }

    public Time getS_duration() {
        return S_duration;
    }

    public void setS_duration(Time s_duration) {
        S_duration = s_duration;
    }

    public String getS_Filepath() {
        return S_Filepath;
    }

    public void setS_Filepath(String s_Filepath) {
        S_Filepath = s_Filepath;
    }

    public Songs(int songId, String songName, String artist) {
    }
}

