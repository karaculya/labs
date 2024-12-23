package ssau.lr8.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Hibernate;
import ssau.lr8.dao.util.DaoFactory;
import ssau.lr8.model.Album;

import java.io.IOException;
import java.util.List;

@WebServlet("/albums")
public class AlbumServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action != null ? action : "") {
            case "" -> {
                if (request.getParameter("id") != null) {
                    int id = Integer.parseInt(request.getParameter("id"));
                    List<Object[]> numAlbumsAndComp = DaoFactory.getArtistDao().getArtistAlbumAndCompositionCountById(id);
                    request.setAttribute("numAlbumsAndComp", numAlbumsAndComp);
                }
                request.setAttribute("albums", DaoFactory.getAlbumDao().getAllAlbums());
                getServletContext().getRequestDispatcher("/albums.jsp").forward(request, response);
            }
            case "delete" -> {
                if (request.getParameter("id") == null) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Don't' exists parameter id");
                    return;
                }
                DaoFactory.getAlbumDao().deleteAlbum(Integer.parseInt(request.getParameter("id")));
                response.setStatus(HttpServletResponse.SC_OK);
            }
            case "edit" -> {
                if (request.getParameter("id") == null) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Don't' exists parameter id");
                    return;
                }
                Album album = DaoFactory.getAlbumDao().getAlbumById(Integer.parseInt(request.getParameter("id")));
                Hibernate.initialize(album.getArtist());
                request.setAttribute("album", album);
                getServletContext().getRequestDispatcher("/editAlbum.jsp").forward(request, response);
            }
            case "showCompositions" -> {
                if (request.getParameter("id") == null) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Don't' exists parameter id");
                    return;
                }
                response.sendRedirect("compositions?id="
                        + Integer.parseInt(request.getParameter("id")));
            }
            default -> response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action parameter");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "edit" -> {
                String id = request.getParameter("id");
                String name = request.getParameter("name");
                String genre = request.getParameter("genre");
                if (id != null && !id.isEmpty()) {
                    int albumId = Integer.parseInt(id);
                    DaoFactory.getAlbumDao().updateAlbum(albumId, name, genre);
                    response.sendRedirect("albums?id=" + Integer.parseInt(request.getParameter("id")));
                }
            }
            case "save" -> {
                String name = request.getParameter("name");
                String genre = request.getParameter("genre");
                int idArtist = Integer.parseInt(request.getParameter("artistId"));
                DaoFactory.getAlbumDao().createAlbum(idArtist, name, genre);
            }
            default -> response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action parameter");
        }
    }
}
