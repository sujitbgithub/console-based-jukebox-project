package org.example.Song;

public class PathOfSongs {
    private String S_Filepath;

    public String getS_Filepath() {
        return S_Filepath;
    }

    public PathOfSongs(String s_Filepath) {
        S_Filepath = s_Filepath;
    }

    @Override
    public String toString() {
        return "PathOfSongs{" +
                "S_Filepath='" + S_Filepath + '\'' +
                '}';
    }

    public void setS_Filepath(String s_Filepath) {
        S_Filepath = s_Filepath;
    }
}
