package JavaFxExample;/**
 * Created by fisbii on 16-8-30.
 */

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;

public class Chart extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Aisa",4298723000.0),
                        new PieChart.Data("North America",355361000.0),
                        new PieChart.Data("South America",616644000.0),
                        new PieChart.Data("Europe",742452000.0),
                        new PieChart.Data("Africa",1110635000.0),
                        new PieChart.Data("Oceania",38304000.0));
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Population of the Continents");
        primaryStage.setScene(new Scene(chart));
        primaryStage.setTitle("Pie Chart");
        primaryStage.show();
    }
}
