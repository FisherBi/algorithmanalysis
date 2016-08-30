package JavaFxExample;/**
 * Created by fisbii on 16-8-30.
 */

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;

public class MyPieChart extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        ObservableList<javafx.scene.chart.PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new javafx.scene.chart.PieChart.Data("Aisa",4298723000.0),
                        new javafx.scene.chart.PieChart.Data("North America",355361000.0),
                        new javafx.scene.chart.PieChart.Data("South America",616644000.0),
                        new javafx.scene.chart.PieChart.Data("Europe",742452000.0),
                        new javafx.scene.chart.PieChart.Data("Africa",1110635000.0),
                        new javafx.scene.chart.PieChart.Data("Oceania",38304000.0));
        final javafx.scene.chart.PieChart chart = new javafx.scene.chart.PieChart(pieChartData);
        chart.setTitle("Population of the Continents");
        primaryStage.setScene(new Scene(chart));
        primaryStage.setTitle("Pie Chart");
        primaryStage.show();
    }
}
