package de.junkerjoerg12.expert_laboratory.ui_components;

import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;

public class MyMenuBar extends MenuBar {
  public MyMenuBar(){
    this.setId("menuebar");
    this.setLayoutX(1);
    this.setLayoutY(1);

    Menu temp = new Menu("File");
    temp.getItems().add(new MenuItem("New"));
    temp.getItems().add(new MenuItem("Open"));
    temp.getItems().add(new MenuItem("Close"));
    temp.getItems().add(new SeparatorMenuItem());
    temp.getItems().add(new MenuItem("Save (Ctrl + S)"));
    temp.getItems().add(new MenuItem("Save As (Ctrl + Shift + S)"));
    temp.getItems().add(new CheckMenuItem("Autosave"));

    this.getMenus().add(temp);

    temp = new Menu("Edit");
    temp.getItems().add(new MenuItem("Exit"));

    this.getMenus().add(temp);

    temp= new Menu("Help");
    temp.getItems().add(new MenuItem("About"));
    
    this.getMenus().add(temp);

    this.setWidth(1920);
    this.setHeight(25);
    this.setPrefSize(getWidth(), getHeight());
  }
}
