package com.bug.bugiss2.controller;

import com.bug.bugiss2.MainApplication;
import com.bug.bugiss2.domain.Bug;
import com.bug.bugiss2.observer.Observer;
import com.bug.bugiss2.repos.BugRepository;
import com.bug.bugiss2.repos.UserRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ProgramatorController implements Observer {
    private UserRepository userRepository;
    private BugRepository bugRepository;
    @FXML
    private Button button;

    @FXML
    private TableColumn<?, ?> denumire;

    @FXML
    private TableColumn<?, ?> descriere;
    @FXML
    private TableColumn<?, ?> status;
    @FXML
    private Button fixed;

    @FXML
    private TextField search;

    @FXML
    private TableView<Bug> tabel;
    Stage stage;
    @FXML
    void back(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("hello-view.fxml"));
        stage = (Stage) button.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        LoginController controller=fxmlLoader.getController();
        controller.init(userRepository,bugRepository);
        stage.show();
    }

    @FXML
    void find(ActionEvent event) {
        var searchText=search.getText();
        if (Objects.equals(searchText, "") || Objects.equals(searchText, " "))
        {
            init(userRepository,bugRepository);
            return;
        }
        modelEvent.setAll(bugRepository.findSome(searchText));
        tabel.setItems(modelEvent);
    }

    @FXML
    void fixed(ActionEvent event) {
        var elem=tabel.getSelectionModel().getSelectedItem();
        if (Objects.equals(elem.getStatus(), "Fixed"))
            return;

        bugRepository.update(elem);
        init(userRepository,bugRepository);
    }
    ObservableList<Bug> modelEvent = FXCollections.observableArrayList();
    public void init(UserRepository userRepository, BugRepository bugRepository) {
        this.userRepository=userRepository;
        this.bugRepository=bugRepository;
        this.bugRepository.addObserver(this);
        denumire.setCellValueFactory(new PropertyValueFactory<>("name"));
        descriere.setCellValueFactory(new PropertyValueFactory<>("description"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));

        modelEvent.setAll(bugRepository.findAll());
        tabel.setItems(modelEvent);
    }

    @Override
    public void update() {
        denumire.setCellValueFactory(new PropertyValueFactory<>("name"));
        descriere.setCellValueFactory(new PropertyValueFactory<>("description"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));

        modelEvent.setAll(bugRepository.findAll());
        tabel.setItems(modelEvent);
    }
}
