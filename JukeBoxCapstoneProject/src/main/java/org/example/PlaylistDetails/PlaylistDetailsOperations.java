package org.example.PlaylistDetails;

import org.example.Playlist.Playlist;
import org.example.Playlist.PlaylistOperations;
import org.example.Song.SongsOperations;
import org.example.User.UserAuthenticationOperation;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static org.example.DatabaseConnection.DBConnection.con;

public class PlaylistDetailsOperations {
    Scanner scan=new Scanner(System.in);
    int songIdforPlaylistinsertion;
    UserAuthenticationOperation userAOObj=new UserAuthenticationOperation();
    Playlist plObjInPLD=new Playlist();
    PlaylistOperations POInPLD;
    SongsOperations insertObjForPlaylist;

    {
        try {
            POInPLD = new PlaylistOperations();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            insertObjForPlaylist = new SongsOperations();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertSongsInPlaylist() throws SQLException {

        System.out.println("Add Songs to Playlist");
        insertObjForPlaylist.displayAllSong();

        System.out.println("Enter the Song ID:");
        insertObjForPlaylist.insertionObj.setS_id(scan.nextInt());
        System.out.println("Enter the Playlist ID:");
        POInPLD.playListObj.setPlaylist_id(scan.nextInt());
        System.out.println(userAOObj.ob.getUser_id());
        System.out.println(POInPLD.playListObj.getPlaylist_id());
        String query = "insert into PlaylistDetails(UserId,PlaylistId,PlaylistName,songId,Songs) values(" + userAOObj.ob.getUser_id() + "," + POInPLD.playListObj.getPlaylist_id() + ",'" + POInPLD.playListObj.getPlaylist_name() + "'," + insertObjForPlaylist.insertionObj.getS_id() + ",'" + insertObjForPlaylist.insertionObj.getS_name() + "')";
        Statement stmt = con.createStatement();
        int rs = stmt.executeUpdate(query);
//        ps.setInt(1,plObjInPLD.getUser_id());
//        ps.setInt(2,POInPLD.playListObj.getPlaylist_id());;
//        ps.setString(3,POInPLD.playListObj.getPlaylist_name());
//        ps.setInt(4,insertObjForPlaylist.insertionObj.getS_id());
//        ps.setString(5,insertObjForPlaylist.insertionObj.getS_name());
//        ps.executeUpdate();
    }

    public static void main(String[] args) {

    }
}
