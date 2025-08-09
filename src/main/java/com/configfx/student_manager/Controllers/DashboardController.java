package com.configfx.student_manager.Controllers;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.PieChart;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private AreaChart<?, ?> areachart;

    @FXML
    private PieChart piechart;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        Camember
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Math", 25),
                new PieChart.Data("Physique", 35),
                new PieChart.Data("SVT", 52),
                new PieChart.Data("Informatique", 25)
        );

        piechart.setData(pieChartData);
        piechart.animatedProperty().setValue(true);
    }
}
