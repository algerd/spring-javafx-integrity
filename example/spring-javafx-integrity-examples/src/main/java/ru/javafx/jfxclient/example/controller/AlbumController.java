
package ru.javafx.jfxclient.example.controller;

import java.net.URL;
import java.util.ResourceBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import ru.javafx.jfxclient.example.jfxintegrity.BaseFxmlController;
import ru.javafx.jfxclient.example.jfxintegrity.FXMLController;

@FXMLController(title = "Album")
@Scope("prototype")
public class AlbumController extends BaseFxmlController {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
        
}
