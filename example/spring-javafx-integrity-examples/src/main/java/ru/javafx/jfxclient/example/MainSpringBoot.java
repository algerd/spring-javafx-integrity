package ru.javafx.jfxclient.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.javafx.jfxclient.example.controller.ArtistController;
import ru.javafx.jfxclient.example.controller.MainController;
import ru.javafx.jfxclient.example.controller.RequestViewService;
import ru.javafx.jfxclient.example.jfxintegrity.BaseFxmlController;

/*
Прямое создание Application класса с запуском приложения в FX-launcher потоке.
Это вынуждает регистрировать бин Stage в start() методе через springContext.getBeanFactory().registerSingleton().
Дополнительная логика для Stage вплетена в метод showStage(), главный контроллер жёстко прописан в коде.
*/
//@SpringBootApplication
public class MainSpringBoot extends Application {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private ConfigurableApplicationContext springContext;
    
    @Override
    public void init() throws Exception {
        springContext = SpringApplication.run(MainSpringBoot.class);      
    }
    
    @Override
    public void start(Stage stage) throws Exception {  
        springContext.getBeanFactory().registerSingleton("primaryStage", stage);
        
        BaseFxmlController mainController = springContext.getBean(MainController.class);    
        Scene scene = new Scene(mainController.getView());  
        stage.titleProperty().bind(mainController.titleProperty());
        stage.setScene(scene); 
        // some logic
        springContext.getBean(RequestViewService.class).show(ArtistController.class);
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
