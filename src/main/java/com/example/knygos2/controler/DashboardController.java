package com.example.knygos2.controler;

import com.example.knygos2.model.Book;
import com.example.knygos2.model.BookDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    @FXML
    private Label status;
    @FXML
    private TableView bookTableView;
    @FXML
    private TextField idField;

    // Lentelės stulpeliai
    @FXML
    private TableColumn idColumn;
    @FXML
    private TableColumn titleColumn;
    @FXML
    private TableColumn pageColumn;
    @FXML
    private TableColumn categorysColumn;
    @FXML
    private TableColumn autorColumn;
    @FXML
    private TableColumn summaryColumn;

    // Formos elementai
    @FXML
    private TextField titleField;
    @FXML
    private TextField pageField;

    //
    @FXML
    private CheckBox checkBoxGrozineLiteratura;
    @FXML
    private CheckBox checkBoxMokslineLiteratura;
    @FXML
    private CheckBox checkBoxPoezija;

    //
    @FXML
    private RadioButton radioButtonAntanas;
    @FXML
    private RadioButton radioButtonJonas;
    @FXML
    private RadioButton radioButtonStyvas;
    //
    @FXML
    private ToggleGroup radioGroup;
    //
    @FXML
    private ChoiceBox choiceBoxSummary;
    //Buttons
    @FXML
    private Button createButton, searchButton, updateButton, deleteButton;


    @FXML
    public void searchButtonClick() {
        String idField2 = idField.getText();
        try {
            Book byID = BookDao.searchById(idField2);
            ObservableList<Book> list = FXCollections.observableArrayList();
            list.add(new Book(byID.getId(), byID.getTitle(), byID.getPage(), byID.getCategory(), byID.getAutor(), byID.getSummary()));

            idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
            pageColumn.setCellValueFactory(new PropertyValueFactory<>("page"));
            categorysColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
            autorColumn.setCellValueFactory(new PropertyValueFactory<>("autor"));
            summaryColumn.setCellValueFactory(new PropertyValueFactory<>("summary"));

            bookTableView.setItems(list);
            status.setText("Pavyko atlikti paieška pagal ID");
        } catch (NullPointerException e) {
            status.setText("Nepavyko atlikti paieškos pagal ID");
        }
    }

    @FXML
    public void onCreateButtonClick() {
        String titleField2 = titleField.getText();
        String secondsField2 = pageField.getText();

//        status.setText("Valio");

        String category = "";
        if (checkBoxGrozineLiteratura.isSelected()) {
            category += checkBoxGrozineLiteratura.getText() + ",";
        }
        if (checkBoxMokslineLiteratura.isSelected()) {
            category += checkBoxMokslineLiteratura.getText() + ",";
        }
        if (checkBoxPoezija.isSelected()) {
            category += checkBoxPoezija.getText() + ",";
        }

        String autor = "";
        if (radioButtonAntanas.isSelected()) {
            autor = radioButtonAntanas.getText();
        } else if (radioButtonJonas.isSelected()) {
            autor = radioButtonJonas.getText();
        } else if (radioButtonStyvas.isSelected()) {
            autor = radioButtonStyvas.getText();
        }

        String summary = "";
        if (!choiceBoxSummary.getSelectionModel().isEmpty()) {
            summary = choiceBoxSummary.getSelectionModel().getSelectedItem().toString();
        }


        if (category.isEmpty())
            status.setText("Prašome pasirinkti kategoriją");
        // else {visasi kitais atvejais, galime registruoti irasa DB(nes jis praejo validacija)}
        int year2 = Integer.parseInt(summary);
        System.out.println(year2);

    }

//    @FXML
//    public void initialize(){
//
//    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        status.setText("initialize");
        choiceBoxSummary.getItems().add("Puiku");
        choiceBoxSummary.getItems().add("Gerai");
        choiceBoxSummary.getItems().add("Pusetinai");
        choiceBoxSummary.getItems().add("Blogai");
        choiceBoxSummary.getItems().add("Labai blogai");

        checkBoxPoezija.setSelected(true);
        radioButtonAntanas.setSelected(true);
        choiceBoxSummary.getSelectionModel().selectFirst();
    }
}