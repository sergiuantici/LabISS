package com.bug.bugiss2.controller;
import com.bug.bugiss2.MainApplication;
import com.bug.bugiss2.repos.BugRepository;
import com.bug.bugiss2.repos.UserRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LoginController {

    @FXML
    private Button log;

    @FXML
    private PasswordField password;
    Stage stage;
    @FXML
    private TextField username;
    private UserRepository userRepostiory;
    private BugRepository bugRepository;

    @FXML
    void login(ActionEvent event) throws IOException {
        var user=username.getText();
        var pass=password.getText();
        if (user == null || user== "") {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("The account doesn't exist");
            alert.showAndWait();
            return;
        } else if (pass == null || pass=="") {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Wrong password!");
            alert.showAndWait();
            return;
        }
        var user2=userRepostiory.exists(user,pass);
        if(user2==null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Contu nu exista!");
            alert.showAndWait();
        }
        else{
            if(Objects.equals(user2.getRole(), "verificator"))
            {
                FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("verificator.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(fxmlLoader.load());
                stage.setScene(scene);
                VerificatorController controller=fxmlLoader.getController();
                controller.init(userRepostiory,bugRepository);
                stage.show();
            }
            else if(Objects.equals(user2.getRole(), "programator"))
            {
                FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("programator.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(fxmlLoader.load());
                stage.setScene(scene);
                ProgramatorController controller=fxmlLoader.getController();
                controller.init(userRepostiory,bugRepository);
                stage.show();
            }
            else
            {
                FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("admin.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(fxmlLoader.load());
                stage.setScene(scene);
                AdminController controller=fxmlLoader.getController();
                controller.init(userRepostiory,bugRepository);
                stage.show();
            }
        }
    }

    public void init(UserRepository userRepository, BugRepository bugRepository){
        this.userRepostiory= userRepository;
        this.bugRepository=bugRepository;
    }
}
