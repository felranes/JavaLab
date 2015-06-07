package daria.krutova.ist.address.view;

import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import daria.krutova.ist.address.model.KPersonD;

/**
 * The controller for the birthday statistics view.
 *
 * @author Marco Jakob
 */
public class KBirthdayStatisticsControllerD {

    @FXML
    private BarChart<String, Integer> kBarChartd;

    @FXML
    private CategoryAxis kXAxisd;

    private ObservableList<String> kMonthNamesd = FXCollections.observableArrayList();

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Get an array with the English month names.
        String[] kMonthsd = DateFormatSymbols.getInstance(Locale.ENGLISH).getMonths();
        // Convert it to a list and add it to our ObservableList of months.
        kMonthNamesd.addAll(Arrays.asList(kMonthsd));

        // Assign the month names as categories for the horizontal axis.
        kXAxisd.setCategories(kMonthNamesd);
    }

    /**
     * Sets the persons to show the statistics for.
     *
     * @param kPersonsd
     */
    public void setPersonData(List<KPersonD> kPersonsd) {
        // Count the number of people having their birthday in a specific month.
        int[] kMonthCounterd = new int[12];
        for (KPersonD p : kPersonsd) {
            int kMonthD = p.getBirthday().getMonthValue() - 1;
            kMonthCounterd[kMonthD]++;
        }

        XYChart.Series<String, Integer> kSeriesd = new XYChart.Series<>();

        // Create a XYChart.Data object for each month. Add it to the series.
        for (int i = 0; i < kMonthCounterd.length; i++) {
            kSeriesd.getData().add(new XYChart.Data<>(kMonthNamesd.get(i), kMonthCounterd[i]));
        }

        kBarChartd.getData().add(kSeriesd);
    }
}