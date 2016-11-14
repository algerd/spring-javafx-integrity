package ru.javafx.jfxclient.example.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javafx.jfxclient.example.jfxintegrity.BaseFxmlController;
import ru.javafx.jfxclient.example.jfxintegrity.FXMLController;

@FXMLController(loadable = false)
public class MenuController extends BaseFxmlController {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
      
    @Autowired
    private MainController parentController;
    
    @Autowired
    private RequestViewService requestViewService;
    
    @FXML
    private AnchorPane menu;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        super.setView(menu);
        logger.info("MenuController: " + parentController);
    } 
    
    @FXML
    private void showArtists() {
        logger.info("showArtists");
        requestViewService.show(ArtistController.class);
    }
    
    @FXML
    private void showAlbums() {
        logger.info("showAlbums");  
        requestViewService.show(AlbumController.class);
    }
        
    @FXML
    private void showDialog() {
        logger.info("showDialog");  
        requestViewService.show(DialogController.class, Modality.WINDOW_MODAL);
    }

    @Override
    public String toString() {
        return "MenuController{" + '}';
    }
           
}
