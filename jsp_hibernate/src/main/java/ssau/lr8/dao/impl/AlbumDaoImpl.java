package ssau.lr8.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import ssau.lr8.dao.AlbumDao;
import ssau.lr8.dao.util.HibernateUtil;
import ssau.lr8.model.Album;
import ssau.lr8.model.Artist;

import java.util.List;

public class AlbumDaoImpl implements AlbumDao {

    @Override
    public void createAlbum(int artistId, String name, String genre) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Artist artist = session.get(Artist.class, artistId);
            Album album = new Album(artist, name, genre);
            session.save(album);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println("Failed to create album");
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Album> getAllAlbums() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Album", Album.class).list();
        } catch (HibernateException e) {
            System.out.println("Albums not found");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteAlbum(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.delete(session.get(Album.class, id));
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println("Failed to delete album");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateAlbum(Integer id, String name, String genre) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Album album = session.get(Album.class, id);
            if (album != null) {
                if (!album.getName().equals(name)) album.setName(name);
                if (!album.getGenre().equals(genre)) album.setGenre(genre);
                session.update(album);
                session.getTransaction().commit();
            }
        }
    }

    @Override
    public Album getAlbumById(Integer id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Album a JOIN FETCH a.artist WHERE a.id = :id", Album.class)
                    .setParameter("id", id)
                    .uniqueResult();
        } catch (HibernateException e) {
            System.out.println("Album not found");
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Object[]> getNameAlbumAndSumDuration(Integer id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "SELECT alb.name, sum(comp.duration) FROM Album alb " +
                                    "JOIN Composition comp ON alb.id =:album_id " +
                                    "WHERE alb.id =:album_id GROUP BY alb.name", Object[].class)
                    .setParameter("album_id", id)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}