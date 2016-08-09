package org.kingsunitedway.testapp20160808.Model;

/**
 * Created by kingsUnitedWay on 8/8/16.
 */
public class mainMenuListItem {

    //Member Variables
    private String mainMenuOrder;
    private String mainMenuLabel;
    private String mainMenuIcon;
    private String mainMenuHex;


    //Constructor
    public mainMenuListItem(String order, String label, String icon, String hex){

        mainMenuOrder = order;
        mainMenuLabel = label;
        mainMenuIcon = icon;
        mainMenuHex = hex;

    }

    //Interface


    public String getMainMenuOrder() {
        return mainMenuOrder;
    }

    public void setMainMenuOrder(String mainMenuOrder) {
        this.mainMenuOrder = mainMenuOrder;
    }

    public String getMainMenuLabel() {
        return mainMenuLabel;
    }

    public void setMainMenuLabel(String mainMenuLabel) {
        this.mainMenuLabel = mainMenuLabel;
    }

    public String getMainMenuIcon() {
        return mainMenuIcon;
    }

    public void setMainMenuIcon(String mainMenuIcon) {
        this.mainMenuIcon = mainMenuIcon;
    }

    public String getMainMenuHex() {
        return mainMenuHex;
    }

    public void setMainMenuHex(String mainMenuHex) {
        this.mainMenuHex = mainMenuHex;
    }
}
