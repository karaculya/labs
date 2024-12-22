package ssau.lr8.dao;

import ssau.lr8.model.Artist;

import java.util.List;

public interface ArtistDao {
    void createArtist(String name);

    Artist getArtistById(Integer id);

    List<Artist> getAllArtists();

    void deleteArtist(Integer id);

    void updateArtist(Integer id, String name);

    List<Object[]> getArtistAlbumAndCompositionCountById(Integer id);
}
