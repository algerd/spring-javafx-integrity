package ru.javafx.jfxclient.example.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javafx.jfxclient.example.jfxintegrity.BaseFxmlController;
import ru.javafx.jfxclient.example.jfxintegrity.FXMLController;

@FXMLController(loadable = false)
public class ExplorerController extends BaseFxmlController {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @FXML
    private AnchorPane explorer;
    
    @Autowired
    private MainController parentController;
    
    @Autowired
    private RequestViewService requestViewService;

    @Override
    public void initialize(URL url, ResourceBundle rb) {  
        super.setView(explorer);
        logger.info("ExplorerController: " + parentController);
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
    
    @Override
    public String toString() {
        return "ExplorerController{" + '}';
    }
       
}
