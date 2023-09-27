package org.example.PlaylistDetails;

import javax.swing.*;

public class PlaylistDetails {
    private int UserId;
    private int PlaylistId;
    private String PlaylistName ;
    private int songId;
    private String Songs ;

    @Override
    public String toString() {
        return "PlaylistDetails{" +
                "UserId=" + UserId +
                ", PlaylistId=" + PlaylistId +
                ", PlaylistName='" + PlaylistName + '\'' +
                ", songId=" + songId +
                ", Songs='" + Songs + '\'' +
                '}';
    }

    public PlaylistDetails() {
    }

    public PlaylistDetails(int userId, int playlistId, String playlistName, int songId, String songs) {
        UserId = userId;
        PlaylistId = playlistId;
        PlaylistName = playlistName;
        this.songId = songId;
        Songs = songs;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public int getPlaylistId() {
        return PlaylistId;
    }

    public void setPlaylistId(int playlistId) {
        PlaylistId = playlistId;
    }

    public String getPlaylistName() {
        return PlaylistName;
    }

    public void setPlaylistName(String playlistName) {
        PlaylistName = playlistName;
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public String getSongs() {
        return Songs;
    }

    public void setSongs(String songs) {
        Songs = songs;
    }
}
