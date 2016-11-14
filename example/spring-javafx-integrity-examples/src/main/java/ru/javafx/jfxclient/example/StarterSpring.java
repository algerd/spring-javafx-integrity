package ru.javafx.jfxclient.example;

import javafx.scene.image.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.javafx.jfxclient.example.controller.ArtistController;
import ru.javafx.jfxclient.example.controller.MainController;
import ru.javafx.jfxclient.example.controller.RequestViewService;
import ru.javafx.jfxclient.example.jfxintegrity.BaseSpringJavaFxApplication;

/*
Унаследованное от BaseSpringJavaFxApplication создание Application класса с запуском приложения в FX-launcher потоке.
(В BaseSpringJavaFxApplication бин Stage зарегистрирован в start() методе через springContext.getBeanFactory().registerSingleton())
Дополнительная логика для Stage вынесена в @Override метод show(), главный контроллер передаётся серез метод-лаунчер.
*/
public class StarterSpring extends BaseSpringJavaFxApplication {
	
	public static void main(String[] args) {
		launchApp(
            StarterSpring.class, 
            MainController.class, 
            new AnnotationConfigApplicationContext("ru.javafx.jfxclient.example"),
            args
        );
	}
    
    @Autowired
    private RequestViewService requestViewService;

    @Override
    public void show() {
        requestViewService.show(ArtistController.class);
        primaryStage.getIcons().add(new Image("images/icon_root_layout.png"));        
    }
	
}
