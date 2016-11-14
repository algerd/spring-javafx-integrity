package ru.javafx.jfxclient.example;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import ru.javafx.jfxclient.example.controller.ArtistController;
import ru.javafx.jfxclient.example.controller.MainController;
import ru.javafx.jfxclient.example.controller.RequestViewService;
import ru.javafx.jfxclient.example.jfxintegrity.BaseFxmlController;

/*
Прямое создание Application класса с запуском приложения в одном потоке (Task<Object>).
Это позволяет создать бин Stage.
Дополнительная логика для Stage вплетена в метод showStage(), главный контроллер жёстко прописан в коде.
*/
//@SpringBootApplication
public class MainSpringBootThread extends Application {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static String[] args;
    private ConfigurableApplicationContext springContext;  
            
    @Override
    public void start(Stage appStage) throws Exception {  
        Task<Object> worker = new Task<Object>() {
            @Override
            protected Object call() throws Exception {
                springContext = SpringApplication.run(MainSpringBootThread.class, MainSpringBootThread.args);
                return null;
            }
        };
        worker.run();  
        worker.setOnSucceeded(event -> showStage());
        worker.setOnFailed(event -> showAlert());
    }
    
    @Bean("primaryStage")
    public Stage getStage() {
        Stage newStage = new Stage(StageStyle.DECORATED);
        return newStage;
    }
   
    private void showStage() { 
        BaseFxmlController controller = springContext.getBean(MainController.class);
        Scene scene = new Scene(controller.getView());
        Stage primaryStage = springContext.getBean("primaryStage", Stage.class);
        primaryStage.titleProperty().bind(controller.titleProperty());
        primaryStage.setScene(scene);
        // some logic
        springContext.getBean(RequestViewService.class).show(ArtistController.class);
        primaryStage.getIcons().add(new Image("images/icon_root_layout.png"));  
        
        primaryStage.show();
    }
    
    private void showAlert() {
        logger.info("Application force stoped!");
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText("Application force stoped!");
        alert.setContentText("Application force stoped!");
        alert.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        Platform.exit();
        springContext.close();
    }
  
    public static void main(String[] args) {
        MainSpringBootThread.args = args;
        launch(MainSpringBootThread.class, args);
    }

}
