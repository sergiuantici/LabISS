package com.bug.bugiss2;

import com.bug.bugiss2.controller.LoginController;
import com.bug.bugiss2.repos.BugRepository;
import com.bug.bugiss2.repos.UserRepository;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.IOException;
import java.util.Properties;

public class MainApplication extends Application {
    private static SessionFactory sessionFactory;
    static void initialize() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            StandardServiceRegistryBuilder.destroy( registry );
        }
    }
    @Override
    public void start(Stage stage) throws IOException {
        initialize();
        Properties Props = new Properties();
        try {
            Props.load(MainApplication.class.getResourceAsStream("/database.properties"));
            System.out.println("Client properties set. ");
            Props.list(System.out);
        } catch (IOException e) {
            System.err.println("Cannot find database.properties " + e);
            return;
        }
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("hello-view.fxml"));
        var userRepository=new UserRepository(Props,sessionFactory);
        var bugRepository=new BugRepository(Props,sessionFactory);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        LoginController ctrl =
                fxmlLoader.getController();
        ctrl.init(userRepository,bugRepository);
        stage.show();
        stage.setOnCloseRequest(event1 -> {
            sessionFactory.close();
            System.exit(0);
        });
    }

    public static void main(String[] args) {
        launch();
    }
}