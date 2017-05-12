package by.kalilaska.view.impl;

import by.kalilaska.view.Viewer;

/**
 * Created by lovcov on 11.05.2017.
 */
public class ViewerImpl implements Viewer{
    @Override
    public void view(String message) {
        System.out.println(message);
    }
}
