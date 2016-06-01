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
        if (action.equals("createProcedure")) {
            bean.createProcedure();
            out.println("Procedure created");
            return;
        }
        if (action.equals("createEntity")) {
            int createdId = bean.createEntity();
            out.println("Entity created");
            out.println("ID: " + createdId);
            return;
        }
        if(action.equals("test")) {
            String test = bean.test(1);
            out.println("Test ok!");
            out.print("text: " + test);
            return;
        }
    }
}
