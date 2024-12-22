package ssau.lr8.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import ssau.lr8.dao.CompositionDao;
import ssau.lr8.dao.util.HibernateUtil;
import ssau.lr8.model.Album;
import ssau.lr8.model.Composition;

import java.sql.Time;
import java.util.List;

public class CompositionDaoImpl implements CompositionDao {
    @Override
    public void createComposition(int albumId, String name, Time duration) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            if (!name.isEmpty()) {
                session.beginTransaction();
                Album album = session.get(Album.class, albumId);
                Composition composition = new Composition(album, duration, name);
                session.persist(composition);
            }
        }
    }

    @Override
    public List<Composition> getAllCompositions() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Composition", Composition.class).list();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteComposition(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.createQuery("delete Composition where id = :param")
                    .setParameter("param", id)
                    .executeUpdate();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateComposition(Integer id, String name, Time duration) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Composition composition = session.get(Composition.class, id);
            if (composition != null) {
                if (!composition.getName().equals(name)) composition.setName(name);
                if (!composition.getDuration().equals(duration)) composition.setDuration(duration);
                session.update(composition);
                session.getTransaction().commit();
            }
        }
    }

    @Override
    public Composition getCompositionById(Integer id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            return session.get(Composition.class, id);
        }
    }

    @Override
    public List<String> getCompositionsNamesLike(String name) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<String> compNames = session.
                    createQuery("SELECT c.name FROM Composition c WHERE LOWER(c.name) " +
                            "LIKE LOWER(:name)", String.class)
                    .setParameter("name", "%" + name + "%").list();
            return compNames;
        }
    }
}