package org.example.Playlist;
import org.example.Song.Songs;
import org.example.Song.SongsOperations;
import org.example.User.UserAuthentication;
import org.example.User.UserAuthenticationOperation;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

import static org.example.DatabaseConnection.DBConnection.con;

public class PlaylistOperations {


        Scanner sc=new Scanner(System.in);
        public Playlist playListObj=new Playlist();
        SongsOperations songlist;

    {
        try {
            songlist = new SongsOperations();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    UserAuthentication userObjInPl=new UserAuthentication();
    UserAuthenticationOperation userAOObj=new UserAuthenticationOperation();



        public PlaylistOperations() throws SQLException {
        }

        public void createPlaylist() throws SQLException {
           // userAOObj.loginUser();
            System.out.println("*******************************");
            System.out.println("    Create New PlayList     ");
            System.out.println("******************************");

            System.out.println("Enter PlayList Name");
            playListObj.setPlaylist_name(sc.next());
            //Add songs in Playlist
            String q = "insert into Playlist (userId,PlaylistName) values(?,?)";
            PreparedStatement ps = con.prepareStatement(q);
            ps.setInt(1, userAOObj.ob.getUser_id());
            ps.setString(2, playListObj.getPlaylist_name());
            ps.executeUpdate();
            System.out.println("**********************************************************");
            System.out.println("*    Your Playlist Has Been Created Successfully     *");
            System.out.println("**********************************************************");
            System.out.println();

        }
        public ArrayList<Playlist> displayAllUserPlaylist() throws SQLException {
            ArrayList<Playlist> displayPlaylist=new ArrayList<>();
            //userAOObj.loginUser();
            try {
                String query = "select * from Playlist where userId=" + userAOObj.ob.getUser_id() + "";
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(query);
                System.out.println("**********************************************************");
                System.out.println("Available Playlists:");
                System.out.println("**********************************************************");
                System.out.println("************************************************************************************");
                System.out.println("|    USER  ID    |         PLAYLIST ID        |       PLAYLIST NAME       | ");
                System.out.println("************************************************************************************");
                while (rs.next()) {
                    int userId = rs.getInt(1);
                    int PlaylistId = rs.getInt(2);
                    String PlaylistName = rs.getString(3);
                    Playlist displayObjOfP = new Playlist(userId, PlaylistId, PlaylistName);
                    displayPlaylist.add(displayObjOfP);
                    //System.out.println(displayPlaylist);
                    //System.out.println(rs.getInt(1) + "    " + rs.getInt(2) + "     " + rs.getString(3));
                    System.out.println("  " + String.format("%1$-12s", userId) + "  |  " +
                            String.format("%1$-25s", PlaylistId) + "  |  " +
                            String.format("%1$-15s", PlaylistName) + "  |  ");
                    System.out.println("********************************************************************************");

                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            return displayPlaylist;
        }


//        public void deleteSongsInPlaylist() throws SQLException {
//
//            System.out.println("Enter Your PlaylistId");
//            playListObj.setPlaylist_id(sc.nextInt());
//
//                    System.out.println("Enter Your SongId which You want to delete");
//                    songsObjinPl.setS_id(sc.nextInt());
//                    String del = "delete from song where S_id="+"'"+songsObjinPl.getS_id()+"'"+" and Playlist_id="+playListObj.getPlaylist_id();
//                    Statement st= con.createStatement();
//                    st.executeUpdate(del);
//                    System.out.println("* Your Song Has Been Deleted Successfully *");
//
//
//
//            }


        }



