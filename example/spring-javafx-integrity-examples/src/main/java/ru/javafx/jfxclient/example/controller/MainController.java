package ru.javafx.jfxclient.example.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javafx.jfxclient.example.jfxintegrity.BaseFxmlController;
import ru.javafx.jfxclient.example.jfxintegrity.FXMLController;
import ru.javafx.jfxclient.example.jfxintegrity.Loadable;
import ru.javafx.jfxclient.example.utils.TabPaneDetacher;

@FXMLController(
    css = {"/styles/Styles.css"},
    title = "Example of JavaFx with Spring Boot ")
public class MainController extends BaseFxmlController {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
      
    @Autowired
    private MenuController menuController;
    @Autowired
    private ExplorerController explorerController;
          
    @FXML
    private TabPane tabPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {        
        TabPaneDetacher.create().makeTabsDetachable(tabPane);
    }
    
    public void show(Loadable controller) {
        Tab tab = new Tab();
        tab.setClosable(true); 
        tab.textProperty().bind(controller.titleProperty());
        tab.setContent(controller.getView()); 
        tabPane.getTabs().add(tab); 
        tabPane.getSelectionModel().selectLast();
    }

    @Override
    public String toString() {
        return "MainController{" + '}';
    }
            
}
