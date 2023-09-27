package org.example.Searching;

import com.sun.jdi.connect.spi.Connection;
import org.example.Main.Main;
import org.example.Song.Songs;
import org.example.Song.SongsOperations;
import org.example.UserDefinedExp.SongNotFoundException;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

import static javax.sound.sampled.AudioSystem.getAudioInputStream;
import static org.example.DatabaseConnection.DBConnection.con;

public class SearchOperations implements SearchSongs {
    Clip clip;
    SongsOperations son=new SongsOperations();
    boolean playongSong=false;

    public SearchOperations() throws SQLException, ClassNotFoundException {
    }

    public void repeatSong(String filepath) throws UnsupportedAudioFileException, IOException, LineUnavailableException, SQLException {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.setFramePosition(0);
            clip.start();
        }
    }
    public void pauseSong(String filepath)
    {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }
    public void resumeSong(String s_Filepath)
    {
        if (clip != null && clip.isRunning()) {
            clip.start();
        }
    }
    public void stopSong(String s_Filepath){
        if (clip != null && clip.isRunning()) {
            clip.close();
        }
    }
    Scanner scn=new Scanner(System.in);
    String S_Filepath = null;

    public void searchBySongName(String name) {
        List<Songs> songs = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("SELECT * FROM song WHERE S_name = ?");
            stmt.setString(1, name);
            rs = stmt.executeQuery();
            System.out.println("Song Details");
            while (rs.next()) {
                int songId = rs.getInt("S_id");
                String songName = rs.getString("S_name");
                String artist = rs.getString("S_artist");
                String filePath=rs.getString("S_Filepath");

                songs.add(new Songs(songId, songName, artist));
                System.out.println("+--------+---------+--------+");
                System.out.println("|  ID    |  Name   | Artist |");
                System.out.println("+--------+---------+--------+");
                System.out.println("|"+songId+"|"+songName+"|"+artist+" |");
                System.out.println("+--------+---------+--------+");
                playSong(filePath);
                System.out.println("Song is Playing :");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void searchBySongId(int id) {
        List<Songs> songs = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("SELECT * FROM song WHERE S_id = ?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            System.out.println("Song Details");
            while (rs.next()) {
                int songId = rs.getInt("S_id");
                String songName = rs.getString("S_name");
                String artist = rs.getString("S_artist");
                String filepath=rs.getString("S_Filepath");
                songs.add(new Songs(songId, songName, artist));
                System.out.println("+--------+---------+--------+");
                System.out.println("|  ID    |  Name   | Artist |");
                System.out.println("+--------+---------+--------+");
                System.out.println("|"+songId+"|"+songName+"|"+artist+" |");
                System.out.println("+--------+---------+--------+");
                playSong(filepath);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void searchBySongGenre(String name) {
        List<Songs> songs = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("SELECT * FROM song WHERE S_genre = ?");
            stmt.setString(1, name);
            rs = stmt.executeQuery();
            System.out.println("Song Details");
            while (rs.next()) {
                int songId = rs.getInt("S_id");
                String songName = rs.getString("S_name");
                String artist = rs.getString("S_artist");
                String filepath=rs.getString("S_Filepath");
                System.out.println("+--------+---------+--------+");
                System.out.println("|  ID    |  Name   | Artist |");
                System.out.println("+--------+---------+--------+");
                System.out.println("|"+songId+"|"+songName+"|"+artist+" |");
                System.out.println("+--------+---------+--------+");
                playSong(filepath);
            }
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public void playSong(String filePath) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();

            boolean playingSong = true;
            while (playingSong) {
                System.out.println("1 -> Pause   2 -> Resume  3 -> Quit  4 -> Repeat 5 -> Exit Player");
                int choiceOfSong = scn.nextInt();
                switch (choiceOfSong) {
                    case 1:
                        clip.stop();
                        break;
                    case 2:
                        clip.start();
                        break;
                    case 3:
                        clip.stop();
                        clip.close();
                        playingSong = false;

                        break;
                    case 4:
                        clip.loop(Clip.LOOP_CONTINUOUSLY);
                        break;
                    case 5:
                        
                        Main main=new Main();
                        main.mainMenu();

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void searchBySongArtist(String name) {
        List<Songs> songs = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("SELECT * FROM song WHERE S_artist = ?");
            stmt.setString(1, name);
            rs = stmt.executeQuery();
            System.out.println("Song Details");
            while (rs.next()) {
                int songId = rs.getInt("S_id");
                String songName = rs.getString("S_name");
                String artist = rs.getString("S_artist");
                String filepath=rs.getString("S_Filepath");
                songs.add(new Songs(songId, songName, artist));

                System.out.println("+--------+---------+--------+");
                System.out.println("|  ID    |  Name   | Artist |");
                System.out.println("+--------+---------+--------+");
                System.out.println("|"+songId+"|"+songName+"|"+artist+" |");
                System.out.println("+--------+---------+--------+");
                playSong(filepath);
            }
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

public static void main(String[] args) throws SQLException, ClassNotFoundException, UnsupportedAudioFileException, LineUnavailableException, IOException {
    SongsOperations song=new SongsOperations();
    SearchOperations ser=new SearchOperations();
    song.displayAllSong();
    ser.searchBySongName("Thunder");
    Scanner sc=new Scanner(System.in);
    //String name=sc.nextLine();
    //ser.searchBySongArtist("Imagine Dragons");
//    PlayMenuForSearch pm=new PlayMenuForSearch();
//    pm.PlayMenu(ser.S_Filepath);
//    System.out.println(allSongsList);
//    song.playAllSongs(allSongsList);

}
}
