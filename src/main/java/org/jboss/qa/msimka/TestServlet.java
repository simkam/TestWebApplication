package org.jboss.qa.msimka;

import javax.annotation.Resource;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Martin Simka
 */
@WebServlet(name = "TestServlet", urlPatterns = {"/TestServlet"})
public class TestServlet extends HttpServlet {

    @Resource(mappedName = "java:/MyMail")
    private Session session;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Store store = session.getStore();
            store.connect();
            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);
                Message msg = inbox.getMessage(inbox.getMessageCount());
                System.out.println(msg.getSubject());
            resp.getWriter().print(msg.getSubject());
        } catch (NoSuchProviderException e) {
            throw new ServletException(e);
        } catch (MessagingException e) {
            throw new ServletException(e);
        }
    }
}
