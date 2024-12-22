package ssau.lr8.dao.util;

import ssau.lr8.dao.AlbumDao;
import ssau.lr8.dao.ArtistDao;
import ssau.lr8.dao.CompositionDao;
import ssau.lr8.dao.impl.AlbumDaoImpl;
import ssau.lr8.dao.impl.ArtistDaoImpl;
import ssau.lr8.dao.impl.CompositionDaoImpl;

public class DaoFactory {
    private static ArtistDao artistDao = null;
    private static AlbumDao albumDao = null;
    private static CompositionDao compositionDao = null;
    private static DaoFactory instance = null;

    public static synchronized DaoFactory getInstance() {
        if (instance == null) instance = new DaoFactory();
        return instance;
    }

    public static ArtistDao getArtistDao() {
        if (artistDao == null) artistDao = new ArtistDaoImpl();
        return artistDao;
    }

    public static AlbumDao getAlbumDao() {
        if (albumDao == null) albumDao = new AlbumDaoImpl();
        return albumDao;
    }

    public static CompositionDao getCompositionDao() {
        if (compositionDao == null) compositionDao = new CompositionDaoImpl();
        return compositionDao;
    }
}
