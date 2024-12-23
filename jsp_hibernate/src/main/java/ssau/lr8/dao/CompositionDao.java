package ssau.lr8.dao;

import ssau.lr8.model.Composition;

import java.sql.Time;
import java.util.List;

public interface CompositionDao {
    void createComposition(int albumId, String name, Time duration);
    List<Composition> getAllCompositions();
    List<Composition> getAllCompositionsByAlbumId(int albumId);
    void deleteComposition(int id);
    void updateComposition(Integer id, String name, Time duration) ;
    Composition getCompositionById(Integer id);
    List<String> getCompositionsNamesLike(String name);
}
