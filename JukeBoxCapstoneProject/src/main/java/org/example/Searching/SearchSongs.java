package org.example.Searching;

import org.example.Song.PathOfSongs;

import java.util.List;

public interface SearchSongs {
    public void searchBySongName(String name);
    public void searchBySongId(int id);
    public void searchBySongGenre(String name);
    public void searchBySongArtist(String name);

}
