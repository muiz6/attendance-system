package com.muiz6.system.attendance.ui.panel;

import javax.swing.*;

public abstract class NavigatorPanel extends JPanel {

    private final NavigatorInterface _nav;

    public NavigatorPanel(NavigatorInterface nav) {
        _nav = nav;
    }

    public NavigatorInterface getNavigator() {
        return _nav;
    }

    public interface NavigatorInterface {

        /**
         *
         * @param id must be a navigator id in Constants class
         */
        void navigate(int id);
    }
}
