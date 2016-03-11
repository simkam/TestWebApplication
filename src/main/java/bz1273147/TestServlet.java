package bz1273147;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author <a href="mailto:msimka@redhat.com">Martin Simka</a>
 */
@WebServlet(name = "TestServlet", urlPatterns = {"/TestServlet"})
public class TestServlet extends HttpServlet {

    @EJB
    private TestBean bean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletResponse.setContentType("text/plain");
        PrintWriter out = httpServletResponse.getWriter();

        String action = request.getParameter("action");
        out.println("Action: " + action);
        if(action != null) {
            if (action.equalsIgnoreCase("new")) {
                String version = request.getParameter("version");
                String text = request.getParameter("text");
                out.println("Version: " + version);
                out.println("Text: " + text);
                if(version == null) {
                    throw new ServletException("version is null");
                }
                if(text == null) {
                    throw new ServletException("text is null");
                }
                int id = bean.saveNewEntity(Integer.parseInt(version), text);
                out.println("saved new entity with version: " + version + ", text: " + text + ", id: " + id);

            } else if(action.equalsIgnoreCase("findAll")) {
                List<TestEntity> all = bean.findAll();
                if(all.isEmpty()) {
                    out.println("no entities found");
                } else {
                    for (TestEntity e : all) {
                        out.println("id: " + e.getId() + ", version: " + e.getVersion() + ", text: " + e.getText());
                    }
                }
            } else if(action.equalsIgnoreCase("findById")) {
                String id = request.getParameter("id");
                if(id == null) {
                    throw new ServletException("id is null");
                }
                out.println("id: " + id);
                TestEntity entity = bean.findById(Integer.parseInt(id));
                out.println("id: " + entity.getId() + ", version: " + entity.getVersion() + ", text: " + entity.getText());
            } else if(action.equalsIgnoreCase("changeVersion")) {
                String id = request.getParameter("id");
                if(id == null) {
                    throw new ServletException("id is null");
                }
                String version = request.getParameter("version");
                if(version == null) {
                    throw new ServletException("version is null");
                }
                out.println("id: " + id);
                out.println("version: " + version);
                bean.changeVersion(Integer.parseInt(id), Integer.parseInt(version));
            } else if(action.equalsIgnoreCase("changeText")) {
                String id = request.getParameter("id");
                if(id == null) {
                    throw new ServletException("id is null");
                }
                String text = request.getParameter("text");
                if(text == null) {
                    throw new ServletException("text is null");
                }
                out.println("id: " + id);
                out.println("text: " + text);
                bean.changeText(Integer.parseInt(id), text);
            }
        }
    }
}
