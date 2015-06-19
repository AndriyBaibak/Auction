package gui;


import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import org.apache.log4j.Logger;
import javax.servlet.annotation.WebServlet;
@Theme("chameleon")
public class MainUI extends UI {


    private static Logger log = Logger.getLogger(MainUI.class);
    private static MainWindow mainWindow = null;
    private WindowForLogin loginWindow = null;


    @Override
    protected void init(VaadinRequest request) {
        loginWindow = new WindowForLogin();
        getCurrent().addWindow(loginWindow);
    }

    public static void addMainWindow(String loginForCurrentUser, String passwordForCurrentUser) {
        mainWindow = new MainWindow(loginForCurrentUser, passwordForCurrentUser);
        getCurrent().addWindow(mainWindow);
    }

    public static MainUI getCurrent() {
        return (MainUI) UI.getCurrent();
    }

    public void logout() {
        mainWindow.close();
        loginWindow.close();
        getCurrent().addWindow(new WindowForLogin());
    }

    public static MainWindow getMainWindow() {
        return mainWindow;
    }

    @WebServlet(urlPatterns = {"/*", "/VAADIN/*"})
    @VaadinServletConfiguration(ui = MainUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}