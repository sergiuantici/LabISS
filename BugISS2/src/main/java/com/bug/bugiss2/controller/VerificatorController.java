package com.bug.bugiss2.controller;

import com.bug.bugiss2.MainApplication;
import com.bug.bugiss2.domain.Bug;
import com.bug.bugiss2.repos.BugRepository;
import com.bug.bugiss2.repos.UserRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class VerificatorController {

    @FXML
    private TextArea description;

    @FXML
    private TextField name;
    private UserRepository userRepository;
    private BugRepository bugRepository;

    @FXML
    void insertBug(ActionEvent event) {
        var nameS=name.getText();
        var descS=description.getText();
        if(nameS==null || nameS.equals("") ||descS==null || descS.equals(""))
            return;
        bugRepository.save(new Bug(nameS,descS,"WIP"));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Off, inca avem Buguri?");
        alert.setContentText("Ba, mersi ca mi ai zis, sper sa rezolvam repede");
        alert.showAndWait();
    }
    Stage stage;
    @FXML
    void logout(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("hello-view.fxml"));
        stage = (Stage) description.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        LoginController controller=fxmlLoader.getController();
        controller.init(userRepository,bugRepository);
        stage.show();
    }
    void init(UserRepository userRepository, BugRepository bugRepository){
        this.userRepository=userRepository;
        this.bugRepository=bugRepository;
    }

}
