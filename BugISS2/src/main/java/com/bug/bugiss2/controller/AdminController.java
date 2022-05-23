package com.bug.bugiss2.controller;

import com.bug.bugiss2.MainApplication;
import com.bug.bugiss2.domain.User;
import com.bug.bugiss2.repos.BugRepository;
import com.bug.bugiss2.repos.UserRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;

public class AdminController {
    private UserRepository userRepository;
    private BugRepository bugRepository;
    public void init(UserRepository userRepository, BugRepository bugRepository) {
        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "Administrator",
                        "Verificator",
                        "Programator"
                );
        occupation.setItems(options);
        this.userRepository=userRepository;
        this.bugRepository=bugRepository;
    }
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private ComboBox<String> occupation;
    Stage stage;
    @FXML
    void back(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("hello-view.fxml"));
        stage = (Stage) occupation.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        LoginController controller=fxmlLoader.getController();
        controller.init(userRepository,bugRepository);
        stage.show();
    }

    @FXML
    void create(ActionEvent event) {
        var emailText=email.getText();
        var passwordText=password.getText();
        var occupationText=occupation.getValue();
        if(userRepository.existsAlready(emailText)!=null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Emailul deja e folosit!");
            alert.showAndWait();
            return;
        }
        if(occupation.getValue().equals("Alege:"))
            return;
        userRepository.save(new User(emailText,passwordText,occupation.getValue().toLowerCase(Locale.ROOT)));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Yey!");
        alert.setContentText("Avem un angajat nou!!");
        alert.showAndWait();
    }
}
