package ssau.lr8;

import ssau.lr8.dao.impl.AlbumDaoImpl;
import ssau.lr8.dao.impl.ArtistDaoImpl;
import ssau.lr8.dao.impl.CompositionDaoImpl;

import java.sql.Time;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ArtistDaoImpl artistDao = new ArtistDaoImpl();
//
//        artistDao.createArtist("Bones");
//
//        AlbumDaoImpl albumDao = new AlbumDaoImpl();
//        albumDao.createAlbum(1, "Burden", "Rap");
//
//        CompositionDaoImpl compositionDao = new CompositionDaoImpl();
//        compositionDao.createComposition(1, "SafeAndSound", new Time(0,2,6));

//        System.out.println(compositionDao.getCompositionsNamesLike("SafeAndSound"));
//        List list = Collections.singletonList(albumDao.getNameAlbumAndSumDuration(1));
//        for (Object o : list) {
//            System.out.println(o.toString());
//        }

//        List list = Collections.singletonList(artistDao.getArtistAlbumAndCompositionCountById(1));
//        for (Object o : list) {
//            System.out.println(o.toString());
//        }
    }
}