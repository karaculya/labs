package ssau.lr8.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import ssau.lr8.dao.ArtistDao;
import ssau.lr8.dao.util.HibernateUtil;
import ssau.lr8.model.Artist;

import java.util.List;

public class ArtistDaoImpl implements ArtistDao {
    @Override
    public void createArtist(String name) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            if (!name.isEmpty()) {
                session.beginTransaction();
                Artist artist = new Artist(name);
                session.persist(artist);
            }
        }
    }

    @Override
    public Artist getArtistById(Integer id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            return session.get(Artist.class, id);
        }
    }

    @Override
    public List<Artist> getAllArtists() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Artist", Artist.class).list();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteArtist(Integer id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.delete(session.get(Artist.class, id));
            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateArtist(Integer id, String name) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Artist artist = session.get(Artist.class, id);
            if (artist != null) {
                if (!artist.getName().equals(name)) artist.setName(name);
                session.update(artist);
                session.getTransaction().commit();
            }
        }
    }

    @Override
    public List<Object[]> getArtistAlbumAndCompositionCountById(Integer id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                    "SELECT art.name, COUNT(DISTINCT al.id) AS Number_Of_Albums, \n" +
                            "COUNT(DISTINCT comp.id) AS Number_Of_Compositions\n" +
                            "FROM Artist art\n" +
                            "LEFT JOIN Album al ON art.id = al.artist.id\n" +
                            "LEFT JOIN Composition comp ON comp.album.id = al.id where art.id=:id \n" +
                            "GROUP BY art.name", Object[].class)
                    .setParameter("id", id)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
