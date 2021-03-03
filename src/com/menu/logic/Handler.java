package com.menu.logic;

import com.menu.components.Menu;

import java.util.Scanner;

public class Handler {

    private final Flow flow;

    public Handler(Flow flow) {
        this.flow = flow;
    }

    public void displayMenu(Menu menu){
        System.out.println("\n###########################");
        System.out.println("\t\t  " +menu.getTitle());
        System.out.println("###########################\n");
        menu.getFields().forEach(field -> {
            System.out.println(field.toString());
            System.out.println("---------------------------");
        });
        System.out.println("\n0- Go back or exit...\n");
        menu.processSelection(listen());
    }

    public void navigateToSubMenu(String previous, String target){
        Menu next = getMenuByTitle(target);
        if(!previous.equals("")){
            next.setPrevious(previous);
        }
        displayMenu(next);
    }

    public Menu getMenuByTitle(String title) {
        if(this.flow.getMenuList("json\\menu_list.json").stream().anyMatch(menu -> menu.getTitle().equals(title))){
            return this.flow.getMenuList("json\\menu_list.json").stream().filter(menu -> menu.getTitle().equals(title)).findFirst().orElse(null);
        } else {
            System.out.println("\nField has no corresponding action or target. Please fix it in the json file...");
            System.exit(3);
            return null;
        }
    }

    private int listen() {
        Scanner scanner = new Scanner(System.in);
        int option;
        try{
            option = scanner.nextInt();
            return option;
        } catch (Exception e){
            e.getCause();
            System.out.println("\n[ERROR] Type a VALID number...");
            System.exit(1);
            return -1;
        }
    }
}
