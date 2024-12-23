package ssau.lr8.servlets;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ssau.lr8.dao.util.DaoFactory;
import ssau.lr8.model.Composition;

import java.io.IOException;
import java.sql.Time;
import java.util.List;

@WebServlet("/compositions")
public class CompositionServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        int id;
        switch (action != null ? action : "") {
            case "" -> {
                if (request.getParameter("id") != null) {
                    int albumId = Integer.parseInt(request.getParameter("id"));
                    request.setAttribute("results",
                            DaoFactory.getAlbumDao().getNameAlbumAndSumDuration(albumId));
                }
                request.setAttribute("compositions",
                        DaoFactory.getCompositionDao().getAllCompositions());
                getServletContext().getRequestDispatcher("/compositions.jsp").forward(request, response);
            }
            case "delete" -> {
                if (request.getParameter("id") == null) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Don't' exists parameter id");
                    return;
                }
                id = Integer.parseInt(request.getParameter("id"));
                DaoFactory.getCompositionDao().deleteComposition(id);
                response.setStatus(HttpServletResponse.SC_OK);
            }
            case "edit" -> {
                if (request.getParameter("id") == null) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Don't' exists parameter id");
                    return;
                }
                id = Integer.parseInt(request.getParameter("id"));
                Composition composition = DaoFactory.getCompositionDao().getCompositionById(id);
                request.setAttribute("composition", composition);
                getServletContext().getRequestDispatcher("/editComposition.jsp").forward(request, response);
            }
            case "autocomplete" -> {
                String name = request.getParameter("name");
                List<String> compNames = DaoFactory.getCompositionDao().getCompositionsNamesLike(name);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(new Gson().toJson(compNames));
            }
            default -> response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action parameter");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "edit" -> {
                System.out.println("edit post");
                String id = request.getParameter("id");
                String name = request.getParameter("name");
                String duration = request.getParameter("duration");
                System.out.println("id = " + id + ", name = " + name + ", duration = " + duration);
                if (id != null && !id.isEmpty()) {
                    int compositionId = Integer.parseInt(id);
                    DaoFactory.getCompositionDao().updateComposition(compositionId, name, Time.valueOf(duration));
                    response.sendRedirect("compositions?album_id=" + Integer.parseInt(request.getParameter("album_id")));
                }
            }
            case "save" -> {
                int id = 0;
                String name = "", duration = "";
                if (request.getParameter("id") != null) id = Integer.parseInt(request.getParameter("id"));
                if (request.getParameter("name") != null) name = request.getParameter("name");
                if (request.getParameter("duration") != null) duration = request.getParameter("duration");
                if (id != 0) DaoFactory.getCompositionDao()
                        .createComposition(id, name, Time.valueOf(duration));
            }
        }
    }
}
