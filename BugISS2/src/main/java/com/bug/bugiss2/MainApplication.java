package com.bug.bugiss2;

import com.bug.bugiss2.controller.LoginController;
import com.bug.bugiss2.repos.BugRepository;
import com.bug.bugiss2.repos.UserRepository;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Properties;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
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
        var userRepository=new UserRepository(Props);
        var bugRepository=new BugRepository(Props);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        LoginController ctrl =
                fxmlLoader.getController();
        ctrl.init(userRepository,bugRepository);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}