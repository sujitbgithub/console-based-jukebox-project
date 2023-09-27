package org.example.Song;
import org.example.DatabaseConnection.DBConnection;
import org.example.Main.Main;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static javax.sound.sampled.AudioSystem.getAudioInputStream;
import static org.example.DatabaseConnection.DBConnection.con;

public class SongsOperations  {
    private int currentSongIndex=0;
    Main main=new Main();
    DBConnection db=new DBConnection();
    Connection con= db.getConnection();
    private String songId;
    private String songName;
    private String artist;
    public Songs insertionObj = new Songs(songId, songName, artist);
    Scanner sc = new Scanner(System.in);
    public static ArrayList<PathOfSongs> allSongsList= new ArrayList<PathOfSongs>();
    String filePath;
    Clip clip;
    //Connection con;
    private boolean shuffle;
    private boolean repeat;

    public void play() {
      //  so.displayAllSong();
        System.out.println("Playing: " +allSongsList.get(currentSongIndex));

    }

    public void nextSong(ArrayList<PathOfSongs> allSongsList) throws UnsupportedAudioFileException, IOException, LineUnavailableException, SQLException {
        // Check if the next song is available

        if (currentSongIndex < allSongsList.size()-1) {
           // System.out.println(currentSongIndex);
            // Stop the current song
            clip.stop();
            // Increment the current index to get the next song
            currentSongIndex++;
            //System.out.println(currentSongIndex);
            // Get the next song's file path
            filePath = allSongsList.get(currentSongIndex).getS_Filepath();
            // Create a File object with the file path
           // System.out.println(filePath);
            File file = new File(filePath);
            // Get an audio input stream for the file
            AudioInputStream audioStream = getAudioInputStream(file);
            // Get a clip object for the audio stream
            clip = AudioSystem.getClip();
            // Open the clip with the audio stream
            clip.open(audioStream);
            // Start playing the next song
            clip.start();
            // Print a message to indicate that the next song is playing
            System.out.println("Playing next song ");
        } else {
            System.out.println("No more songs in the playlist");
        }
    }
    public void repeatSong() throws UnsupportedAudioFileException, IOException, LineUnavailableException, SQLException {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.setFramePosition(0);
            clip.start();
        }
    }


    private void reset(int currentSongIndex) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        currentSongIndex = 0;
        play();
    }

    public void shuffle() {
        shuffle = !shuffle;
        System.out.println("Shuffle mode: " + shuffle);
    }
//    public SongsOperations() throws SQLException {
//    }
    public SongsOperations() throws SQLException, ClassNotFoundException {
        allSongsList = new ArrayList<PathOfSongs>();
    }


    public void insertNewSong() throws SQLException {
        System.out.println("*******************************");
        System.out.println("Enter Details of Song");
        System.out.println("*******************************");
        System.out.println("Enter Song Id");
        insertionObj.setS_id(sc.nextInt());
        System.out.println("Enter Song Name");
        sc.nextLine();
        insertionObj.setS_name(sc.nextLine());
        System.out.println("Enter Song Genre");
        insertionObj.setS_genre(sc.next());
        System.out.println("Enter Artist Name");
        sc.nextLine();
        insertionObj.setS_artist(sc.nextLine());
        System.out.println("Enter Song Duration");
        insertionObj.setS_duration(Time.valueOf(sc.next()));
        System.out.println("Enter The Song Path");
        insertionObj.setS_Filepath(sc.next());
        System.out.println("********************************");
        System.out.println("* Song Inserted Successfully *");
        System.out.println("*********************************");

        String query = "insert into Song values(?,?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, insertionObj.getS_id());
        ps.setString(2, insertionObj.getS_name());
        ps.setString(3, insertionObj.getS_genre());
        ps.setString(4, insertionObj.getS_artist());
        ps.setString(5,insertionObj.getS_album());
        ps.setTime(5, insertionObj.getS_duration());
        ps.setString(6, insertionObj.getS_Filepath());
        ps.executeUpdate();


    }
    public ArrayList<Songs> displayAllSong() {
        ArrayList<Songs> normalDisplayList = new ArrayList<>();

        try {
            String query = "select * from Song";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            System.out.println("************************************************************************************************");
            System.out.println("                                 Available Songs:                                                      ");
            System.out.println("************************************************************************************************");
            System.out.println("    Song  ID    |          Song Name          |       Genre       |         Artist         |              Album                |    Duration    |");
            System.out.println("***************************************************************************************************************************************************");

            while (rs.next()) {
                int S_id = rs.getInt(1);
                String S_name = rs.getString(2);
                String S_genre = rs.getString(3);
                String S_artist = rs.getString(4);
                String S_album=rs.getString(5);
                Time S_duration = rs.getTime(6);
                String S_Filepath = rs.getString(7);
                Songs displayObj = new Songs(S_id, S_name, S_genre, S_artist,S_album, S_duration, S_Filepath);
                normalDisplayList.add(displayObj);
//                System.out.println(rs.getString(2));
                System.out.println("  " + String.format("%1$-12s", S_id) + "  |  " +
                        String.format("%1$-25s", S_name) + "  |  " +
                        String.format("%1$-15s", S_genre) + "  |  " +
                        String.format("%1$-20s", S_artist) + "  |  " +
                        String.format("%1$-31s", S_album) + "  |  " +
                        String.format("%1$-12s", S_duration) + "  |");
                System.out.println("************************************************************************************************************************************************");
            }
            System.out.println("************************************************************************************************");

        } catch (Exception e) {
            System.out.println(e);
        }

        return normalDisplayList;

    }

    public List<PathOfSongs> fetchPathOfSongs() throws SQLException {
        try {
            String query = "select S_Filepath from Song";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {

                String S_Filepath = rs.getString(1);
                //System.out.println(rs.getString(1));
                PathOfSongs fetchPathObj = new PathOfSongs(S_Filepath);
                allSongsList.add(fetchPathObj);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return allSongsList;
    }
    public void playAllSongs(ArrayList<PathOfSongs> allSongsList) throws UnsupportedAudioFileException, IOException, LineUnavailableException, SQLException, ClassNotFoundException {
boolean ask=true;
            while(ask){
                for (PathOfSongs s : allSongsList) {
                    int option =100;

                System.out.println("1->Play  2->Pause   3->Resume  4->Quit  5-> Repeat  6->Next  7->Exit Player");
                    System.out.println("Enter Your Choice: ");
                int choice=sc.nextInt();
            switch (choice)
            {
                case  1 :
                    {
                        filePath = s.getS_Filepath();
                        //System.out.println();
                        File file = new File(filePath);
                        AudioInputStream audioStream = getAudioInputStream(file);
                        clip = AudioSystem.getClip();
                        clip.open(audioStream);
                    clip.start();
                        System.out.println("Song is Playing :");
                    break;
                }
                case 2:{
                    System.out.println("Song is paused");
                clip.stop();
                    break;
                }
                case 3:{
                    System.out.println("Resuming song");
                    clip.start();
                    break;
                }
                case 4:{
                    System.out.println("Stopped Playing song");
                    clip.close();
                    break;
                }
                case 5:{
                    System.out.println("Repeating song");
                    repeatSong();
                    break;
                }
                case 6:{
                    nextSong(allSongsList);
                   // currentSongIndex++;
                    break;
                }
                case 7:{
                    System.out.println("Player Exited");
                    main.mainMenu();
                    break;
                }
                default:
                    System.out.println("Invalid choice");
                    break;
            }

            }
        }
    }
    public void playSelectedSong() throws SQLException, ClassNotFoundException {
        SongsOperations playSS=new SongsOperations();
        playSS.displayAllSong();
        playSS.fetchPathOfSongs();
        int songIdtobeplayed;
        System.out.println("Enter the Song Id:");
        songIdtobeplayed=sc.nextInt();
        List<Songs> songs = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("SELECT S_Filepath FROM song WHERE S_id = ?");
            stmt.setInt(1, songIdtobeplayed);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int songId = rs.getInt("S_id");
                String songName = rs.getString("S_name");
                String artist = rs.getString("S_artist");
               // String S_Filepath= rs.getString("S_Filepath");
                songs.add(new Songs(songId, songName, artist));
                System.out.println("Song Details");
                System.out.println("ID: "+songId+"  Name: "+songName+"  Artist: "+artist);
                filePath = rs.getString("S_Filepath");
                //System.out.println();
                File file = new File(filePath);
                AudioInputStream audioStream = getAudioInputStream(file);
                clip = AudioSystem.getClip();
                clip.open(audioStream);
                clip.start();
                System.out.println("Song is Playing :");


            }
        } catch (SQLException | UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        SongsOperations s=new SongsOperations();
        s.displayAllSong();
    }
}
