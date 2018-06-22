/**
 * 
 */
package com.koylubaevnt.algorithms.search.interfaces;

/**
 * <code>SymbolTable</code> - таблица имен.
 * Структура данных для хранения пар ключ-значение.
 * Поддерживает две операции:
 * 1. Вставить новую пару
 * 2. Найти значение, свячзанное с заданным ключем
 * 
 * @author KojlubaevNT
 *
 */
public interface IOrderedSymbolTable<Key extends Comparable<Key>, Value> extends ISymbolTable<Key, Value>{
	
	/**
	 * Возвращает ключи из интервала [lo..hi] в таблице имен 
	 * 
	 * @return	Ключи из интервала [lo..hi] в таблице имен
	 */
	public Iterable<Key> keys(Key lo, Key hi);
	
	/**
	 * Возвращает наименьший ключ в таблице имен
	 * 
	 * @return	Наименьший ключ в таблице имен
	 */
	public Key min();
	
	/**
	 * Возвращает наибольший ключ в таблице имен
	 * 
	 * @return	Наибольший ключ в таблице имен
	 */
	public Key max();
	
	/**
	 * Возвращает наименьший ключ в таблице имен больше чем или равный {@code key}. 
	 * 
	 * @param key	Ключ
	 * @return		Наименьший ключ в таблице имен больше чем или равный {@code key}.
	 */
	public Key ceiling(Key key);
	
	/**
	 * Возвращает наибольший ключ в таблице имен меньше чем или равный {@code key}. 
	 * 
	 * @param key	Ключ
	 * @return		Наибольший ключ в таблице имен меньше чем или равный {@code key}.
	 */
	public Key floor(Key key);
	
	/**
	 * Возвращает количество ключей, меньших {@code key}
	 * 
	 * @param key	Ключ
	 * @return		Количество ключей, меньших {@code key}
	 */
	public int rank(Key key);
	
	/**
	 * Возвращает ключ ранга {@code k}
	 * 
	 * @param key	Ключ	
	 * @return		Kлюч ранга {@code k}
	 */
	public Key select(int key);
	
	/**
	 * Удаление наименьшего ключа
	 * 
	 */
	public void deleteMin();
	
	/**
	 * Удаление наибольшего ключа
	 * 
	 */
	public void deleteMax();
	
	/**
	 * Возвращает количество ключей в интервале [lo..hi]
	 * 
	 * @param lo	Нижняя граница интервала
	 * @param hi	Верхняя граница интервала
	 * @return		Количество ключей в интервале [lo..hi]
	 */
	public int size(Key lo, Key hi);
}
