package Marina.Malashenko.inb.ch.makery.adress.view;

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
import Marina.Malashenko.inb.ch.makery.adress.model.MPersonM;

public class MBirthdayStatisticsControllerM {
	 @FXML
	    private BarChart<String, Integer> mbarChartm;

	    @FXML
	    private CategoryAxis mxAxism;

	    private ObservableList<String> mmonthNamesm = FXCollections.observableArrayList();

	    @FXML
	    private void initialize() {

	        String[] months = DateFormatSymbols.getInstance(Locale.ENGLISH).getMonths();
	        
	        mmonthNamesm.addAll(Arrays.asList(months));

	       
	        mxAxism.setCategories(mmonthNamesm);
	    }

	    public void msetPersonDataM(List<MPersonM> persons) {
	        
	        int[] monthCounter = new int[12];
	        for (MPersonM p : persons) {
	            int month = p.getBirthday().getMonthValue() - 1;
	            monthCounter[month]++;
	        }

	        XYChart.Series<String, Integer> series = new XYChart.Series<>();

	        
	        for (int i = 0; i < monthCounter.length; i++) {
	            series.getData().add(new XYChart.Data<>(mmonthNamesm.get(i), monthCounter[i]));
	        }

	        mbarChartm.getData().add(series);
	    }
}
