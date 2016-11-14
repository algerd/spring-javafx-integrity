
package ru.javafx.jfxclient.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.javafx.jfxclient.example.controller.ArtistController;
import ru.javafx.jfxclient.example.controller.MainController;
import ru.javafx.jfxclient.example.controller.RequestViewService;
import ru.javafx.jfxclient.example.jfxintegrity.BaseFxmlController;

/*
Прямое создание Application класса с Spring Framework контейнером.
*/
public class MainSpring extends Application {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private AnnotationConfigApplicationContext springContext;
    
    @Override
    public void init() throws Exception {
        springContext = new AnnotationConfigApplicationContext("ru.javafx.jfxclient.example");      
    }
    
    @Autowired
    private RequestViewService requestViewService;
    
    @Override
    public void start(Stage stage) throws Exception {  
        springContext.getBeanFactory().registerSingleton("primaryStage", stage);
        springContext.getAutowireCapableBeanFactory().autowireBean(this);
        
        BaseFxmlController mainController = springContext.getBean(MainController.class);    
        Scene scene = new Scene(mainController.getView());  
        stage.titleProperty().bind(mainController.titleProperty());
        stage.setScene(scene); 
        // some logic
        requestViewService.show(ArtistController.class);
        stage.getIcons().add(new Image("images/icon_root_layout.png"));
        
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        springContext.close();
    }
   
    public static void main(String[] args) {
        Application.launch(args);
    }
    
}
