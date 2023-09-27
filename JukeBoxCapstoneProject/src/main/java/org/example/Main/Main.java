package org.example.Main;

import org.example.Playlist.PlaylistOperations;
import org.example.Searching.SearchOperations;
import org.example.Song.SongsOperations;
import org.example.User.UserAuthenticationOperation;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import static org.example.Song.SongsOperations.allSongsList;

public class Main {

    public void mainMenu() throws SQLException, ClassNotFoundException, UnsupportedAudioFileException, LineUnavailableException, IOException {
        UserAuthenticationOperation user = new UserAuthenticationOperation();
        SongsOperations songsOperations = new SongsOperations();
        PlaylistOperations objOfPl = new PlaylistOperations();
        SearchOperations search = new SearchOperations();
        Scanner zero = new Scanner(System.in);
        System.out.println("******************************************");
        System.out.println("*****************Welcome******************");
        System.out.println("******************************************");
        int x = 100;
        while (x == 100) {
            System.out.println("Do you want to Login Or Register?");
            System.out.println("Press 1 for Login and 2 for Registration:");
            int choice = zero.nextInt();
            switch (choice) {
                case 1: {
                    if (user.loginUser() == true) {
                        while (true) {
                            System.out.println("********************" +
                                    "\n1. Show All Songs " +
                                    "\n2. Play All Songs " +
                                    "\n3. Search Song " +
                                    "\n4. Create a Playlist " +
                                    "\n5. Show All playlists " +
                                    "\n6. View Playlist " +
                                    "\n7. Play Playlist  " +
                                    "\n8. Exit " +
                                    "\n********************");
                            System.out.println("Enter Your choice:");
                            int ch = zero.nextInt();
                            switch (ch) {
                                case 1:
                                    songsOperations.displayAllSong();
                                    break;
                                case 2:
                                    songsOperations.fetchPathOfSongs();
                                    songsOperations.playAllSongs(allSongsList);
                                    break;
                                case 3: {
                                    System.out.println("Search Songs:");
                                    int x1 = 0;
                                    while (x1 == 0) {
                                        System.out.println("1. Song ID  2. Song Name 3. Genre 4. Artist");
                                        int cho = zero.nextInt();
                                        switch (cho) {
                                            case 1:
                                                System.out.println("Enter the ID of song");
                                                int idSong = zero.nextInt();
                                                search.searchBySongId(idSong);
                                                break;
                                            case 2:
                                                System.out.println("Enter Song Name:");
                                                String name = zero.next();
                                                search.searchBySongName(name);
                                                break;
                                            case 3:
                                                System.out.println("Enter Genre:");
                                                String genre = zero.next();
                                                search.searchBySongGenre(genre);
                                                break;
                                            case 4:
                                                System.out.println("Enter Artist Name:");
                                                String artist = zero.next();
                                                search.searchBySongArtist(artist);
                                                break;
                                            default:
                                                System.out.println("Invalid choice");
                                                break;

                                        }
                                        System.out.println("Return to Main menu?Press 0 to continue Press 1 to Main Menu ");
                                        x1 = zero.nextInt();
                                    }
                                }

                                break;
                                case 4:
                                    objOfPl.createPlaylist();
                                    break;
                                case 5:
                                    objOfPl.displayAllUserPlaylist();
                                    break;
                                case 8:
                                    System.out.println("Press 0 to exit and 1 to continue");
                                    int exit=zero.nextInt();
                                    if(exit==0) {
                                        System.exit(exit);
                                    }

                            }
                        }
                    }
                    break;
                }

                case 2: {
                    user.RegisterUser();
                }

            }

        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException, UnsupportedAudioFileException, LineUnavailableException, IOException {
Main main=new Main();
main.mainMenu();

    }
}