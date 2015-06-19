package gui;


import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.Window;

public class WindowForException extends Window {

    public WindowForException(Object ob) {
        buildExceptionLayout();
    }

    public void buildExceptionLayout() {
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        TextArea textArea = new TextArea("Exception");

        horizontalLayout.addComponent(textArea);
        setContent(horizontalLayout);
        setCaption("Exception window");
    }
}
