package com.koylubaevnt.address.view;

import java.text.DateFormatSymbols;
import java.util.List;
import java.util.Locale;

import com.koylubaevnt.address.model.Person;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;

/**
 * ���������� ��� ������������� ���������� ���� ��������.
 * 
 */
public class BirthdayStatisticsController {

	@FXML
	private BarChart<String, Integer> barChart;
	
	@FXML
	private CategoryAxis xAxis;
	
	private ObservableList<String> monthNames = FXCollections.observableArrayList();
	
	/**
     * �������������� �����-����������. ���� ����� ���������� �������������
     * ����� ����, ��� fxml-���� ��� ��������.
     */
    @FXML
    private void initialize() {
    	// �������� ������ � ����������� ������� �������
    	String[] months = DateFormatSymbols.getInstance(Locale.ENGLISH).getMonths();
    	// ����������� ��� � ������ � ��������� � ��� ObservableList �������.
    	monthNames.addAll(months);
    	
    	// ��������� ����� ������� ����������� ��� �������������� ���.
    	xAxis.setCategories(monthNames);
    }
    
    /**
     * ����� ���������, � ������� ����� �������� ����������.
     * 
     * @param persons
     */
    public void setPersonData(List<Person> persons) {
    	// ������� ���������, ������� ��� ������� � ��������� ������.
    	int[] monthCounter = new int[12];
    	for (Person p : persons) {
    		int month = p.getBirthday().getMonthValue() - 1;
    		monthCounter[month]++;
    	}
    	
    	XYChart.Series<String, Integer> series = new XYChart.Series<>();
    	
    	// ������� ������ XYChart.Data ��� ������� ������.
    	// ��������� ��� � �����.
    	for (int i = 0; i < monthCounter.length; i++) {
    		series.getData().add(new XYChart.Data<String, Integer>(monthNames.get(i), monthCounter[i]));
    	}
    	
    	barChart.getData().add(series);
    }
}
