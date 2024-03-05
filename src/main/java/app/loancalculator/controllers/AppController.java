package app.loancalculator.controllers;

import app.loancalculator.models.Month;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

import static app.loancalculator.calculations.CalculatePayments.getMonthList;
import static app.loancalculator.utils.Export.exportToCSV;
import static app.loancalculator.utils.Filter.getFilteredMonths;
import static app.loancalculator.utils.Graph.getGraphData;


public class AppController {

    private boolean annuity; // If false, linear payment
    private int amount, termY, termM, postStart, postTerm, fromFilter, toFilter;
    private float interest, postInterest;
    private boolean validInput;
    private ObservableList<Month> monthList;
    private  ObservableList<Month> filteredMonths = FXCollections.observableArrayList();
    private Stage stage;

    @FXML
    private Button btnTable;
    @FXML
    private Button btnGraph;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnCalculate;
    @FXML
    private Button btnUpdateFilter;
    @FXML
    private GridPane gridPaneTableGraph;
    @FXML
    private LineChart<Number, Number> lineChartGraph;
    @FXML
    private TableView<Month> tableViewData;
    @FXML
    private TableColumn<Month, Integer> indexCol;
    @FXML
    private TableColumn<Month, Double> remainingCol;
    @FXML
    private TableColumn<Month, Double> interestCol;
    @FXML
    private TableColumn<Month, Double> returnCol;
    @FXML
    private TableColumn<Month, Double> fullPaymentCol;

    @FXML
    private Text textInputErr;
    @FXML
    private Text textFilterErr;
    @FXML
    private TextField textFieldAmount;
    @FXML
    private TextField textFieldTermY;
    @FXML
    private TextField textFieldInterest;
    @FXML
    private TextField textFieldTermM;
    @FXML
    private TextField textFieldPostInterest;
    @FXML
    private TextField textFeldPostponStart;
    @FXML
    private TextField textFieldPostponTerm;
    @FXML
    private  TextField textFieldFrom;
    @FXML
    private TextField textFieldTo;
    @FXML
    private RadioButton radioButtonLinear;
    @FXML
    private RadioButton radioButtonAnnuity;
    @FXML
    private NumberAxis axisX;
    @FXML
    private NumberAxis axisY;

    @FXML
    private void handleClicks(ActionEvent e) {
        Object src = e.getSource();
        if (src == btnTable) {
            gridPaneTableGraph.setVisible(true);
            tableViewData.setVisible(true);
            lineChartGraph.setVisible(false);

        } else if (src == btnGraph) {
            gridPaneTableGraph.setVisible(true);
            tableViewData.setVisible(false);
            lineChartGraph.setVisible(true);

        } else if (src == btnSave) {
            gridPaneTableGraph.setVisible(false);

            exportToCSV(getFileName(), monthList);

        } else if (src == btnCalculate) {
            getDataInput();
            if (!validInput) {
                textInputErr.setVisible(true);

            } else {
                textInputErr.setVisible(false);
                monthList = getMonthList(amount, interest, termY, termM, postStart, postTerm, postInterest, annuity);
                showTable();
                showGraph();
            }

        } else if (src == btnUpdateFilter) {
            getFilterInput();
            if (!validInput) {
                textFilterErr.setVisible(true);

            } else {
                textFilterErr.setVisible(false);
                filteredMonths = getFilteredMonths(monthList, fromFilter, toFilter);
                showTable();
                showGraph();
            }

        }
    }

    private void showGraph() {
        axisX.setAutoRanging(true);
        axisY.setAutoRanging(true);
        lineChartGraph.getData().clear();
        lineChartGraph.getData().add(getGraphData(filteredMonths.isEmpty() ? monthList : filteredMonths));

    }

    private void getFilterInput() {
        String fromRaw, toRaw;

        fromRaw = textFieldFrom.getText();
        toRaw = textFieldTo.getText();

        validInput = true;
        try {
            if (!fromRaw.isEmpty()) {
                fromFilter = Integer.parseInt(fromRaw);
            } else {
                fromFilter = 0;
            }

            if (!toRaw.isEmpty()) {
                toFilter = Integer.parseInt(toRaw);
            } else {
                toFilter = 0;
            }
        } catch (NumberFormatException e) {
            validInput = false;
            return;
        }

        if (fromFilter < 0 || fromFilter > termY * 12 + termM + postTerm || toFilter < 0 || toFilter > termY * 12 + termM + postTerm) {
            validInput = false;
            return;
        }
        if (fromFilter > toFilter && toFilter != 0) {
            validInput = false;
        }

    }

    private void getDataInput() {
        String amountRaw, interestRaw, termYRaw, termMRaw, postStartRaw, postTermRaw, postInterestRaw;
        amountRaw = textFieldAmount.getText();
        interestRaw = textFieldInterest.getText();
        termYRaw = textFieldTermY.getText();
        termMRaw = textFieldTermM.getText();
        postStartRaw = textFeldPostponStart.getText();
        postTermRaw = textFieldPostponTerm.getText();
        postInterestRaw = textFieldPostInterest.getText();

        validInput = true;
        try {
            if (!amountRaw.isEmpty()) {
                amount = Integer.parseInt(amountRaw);
            } else {
                amount = 0;
                validInput = false;
                return;
            }

            if (!interestRaw.isEmpty()) {
                interest = Float.parseFloat(interestRaw);
            } else {
                interest = 0;
                validInput = false;
                return;
            }

            if (!termYRaw.isEmpty()) {
                termY = Integer.parseInt(termYRaw);
            } else {
                termY = 0;
            }
            if (!termMRaw.isEmpty()) {
                termM = Integer.parseInt(termMRaw);
            } else {
                termM = 0;
            }
            if (termM == termY && termY == 0) {
                validInput = false;
                return;
            }

            if (!postStartRaw.isEmpty()) {
                postStart = Integer.parseInt(postStartRaw);
            } else {
                postStart = 0;
            }
            if (!postTermRaw.isEmpty()) {
                postTerm = Integer.parseInt(postTermRaw);
            } else {
                postTerm = 0;
            }
            if (!postInterestRaw.isEmpty()) {
                postInterest = Float.parseFloat(postInterestRaw);
            } else {
                postInterest = 0;
            }

        } catch (NumberFormatException e) {
            validInput = false;
            return;
        }

        if (radioButtonAnnuity.isSelected()) {
            annuity = true;
        }
        else {
            annuity = false;
        }

        if ((postStart == 0 || postTerm == 0 || postInterest == 0) && (postInterest != 0 || postTerm != 0 || postStart != 0)) {
            validInput = false;
            return;
        }

        if (amount <= 0 || termY < 0 || termM < 0 || postStart < 0 || postTerm < 0 || interest <= 0 || postInterest < 0) {
            validInput = false;
            return;
        }

        if (termY * 12 + termM <= postStart || termY * 12 + termM  <= postStart + postTerm - 1) {
            validInput = false;
        }
    }

    private String getFileName() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Export file");
        File file = fileChooser.showSaveDialog(stage);
        return file.getAbsolutePath();
    }

    private void showTable() {
        indexCol.setCellValueFactory(new PropertyValueFactory<>("Index"));
        remainingCol.setCellValueFactory(new PropertyValueFactory<>("RemainingPayment"));
        interestCol.setCellValueFactory(new PropertyValueFactory<>("InterestPayment"));
        returnCol.setCellValueFactory(new PropertyValueFactory<>("CreditRepayment"));
        fullPaymentCol.setCellValueFactory(new PropertyValueFactory<>("FullPayment"));
        tableViewData.setItems(filteredMonths.isEmpty() ? monthList : filteredMonths);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

}