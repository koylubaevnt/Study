package com.koylubaevnt.address.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Вспомогательные функции для работы с датами.
 * 
 * @author Никита
 *
 */
public class DateUtil {

	/** Шаблон даты, используемый для преобразования. */
	private static final String DATE_PATTERN = "dd.MM.yyyy";
	
	/** Форматировщик даты.*/
	private static final DateTimeFormatter DATE_FORMATTER =
			DateTimeFormatter.ofPattern(DATE_PATTERN);
	
	/**
	 * Возвращает полученную дату в виде хорошо отформатированной строки.
	 * Используется определенный выше {@link DateUtil#DATE_PATTERN}.
	 * 
	 * @param date - дата, которая будет возвращена в виде строки.
	 * @return отформатированную строку
	 */
	public static String format(LocalDate date) {
		if(date == null) {
			return null;
		}
		return DATE_FORMATTER.format(date);
	}
	
	/**
	 * Преобразует строку, которая отформатирована по правилам
	 * шаблона {@link DateUtil#DATE_PATTERN} в объект {@link LocalDate}.
	 * 
	 * Возвращает null, если строка не может быть преобразована.
	 * 
	 * @param dateString - дата в виде String.
	 * @return объект дты или null, если строка не может быть преобразована
	 */
	public static LocalDate parse(String dateString) {
		try {
			return DATE_FORMATTER.parse(dateString, LocalDate::from);
		} catch (DateTimeParseException e) {
			return null;
		}
	}
	
	/**
	 * ПРоверяет, является ли строка корректной датой.
	 * 
	 * @param dateString
	 * @return true, ели строка является корректной датой
	 */
	public static boolean validDate(String dateString) {
		// Пытаемся разобрвть строку
		return DateUtil.parse(dateString) != null;
	}
}
