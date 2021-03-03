package com.menu;

import com.menu.components.Menu;
import com.menu.logic.Flow;
import com.menu.logic.Handler;

public class Main {

    public static void main(String[] args) {
        Flow flow = new Flow();
        Handler handler = new Handler(flow);
        Menu menu = handler.getMenuByTitle("Main Menu");
        handler.displayMenu(menu);
    }
}
