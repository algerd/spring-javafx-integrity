package ru.javafx.jfxclient.example;

import ru.javafx.jfxclient.example.jfxintegrity.BaseSpringBootJavaFxApplication;
import javafx.scene.image.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.javafx.jfxclient.example.controller.ArtistController;
import ru.javafx.jfxclient.example.controller.MainController;
import ru.javafx.jfxclient.example.controller.RequestViewService;

/*
Унаследованное от BaseSpringBootJavaFxApplication создание Application класса с запуском приложения в FX-launcher потоке.
(В BaseSpringBootJavaFxApplication бин Stage зарегистрирован в start() методе через springContext.getBeanFactory().registerSingleton())
Дополнительная логика для Stage вынесена в @Override метод show(), главный контроллер передаётся серез метод-лаунчер.
*/
//@SpringBootApplication
public class StarterSpringBoot extends BaseSpringBootJavaFxApplication {
	
	public static void main(String[] args) {
		launchApp(StarterSpringBoot.class, MainController.class, args);
	}
    
    @Autowired
    private RequestViewService requestViewService;

    @Override
    public void show() {
        requestViewService.show(ArtistController.class);
        primaryStage.getIcons().add(new Image("images/icon_root_layout.png"));        
    }
	
}
