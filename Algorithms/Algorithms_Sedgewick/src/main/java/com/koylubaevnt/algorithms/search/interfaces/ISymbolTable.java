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
public interface ISymbolTable<Key extends Comparable<Key>, Value> {

	/**
	 * Помещение пары ключ-значение в таблицу 
	 * 	(удаление ключа key из таблицы, если значение равно null)
	 * 
	 * @param key	Ключ
	 * @param value	Значение
	 */
	public void put(Key key, Value value);
	
	/**
	 * Получение значения, связанное с заданным ключем в таблице имен
	 * 
	 * @param key	Ключ
	 * @return		Значения, связанное с заданным ключем;
	 * 				{@code null}, если ключ отсутсвует в таблице имен
	 */
	public Value get(Key key);
	
	/**
	 * Удаление ключа <code>key</code> (и его значение) из таблицы
	 * 
	 * @param key	Ключ
	 */
	public void delete(Key key);
	
	/**
	 * Имеется ли значение связанное с ключем <code>key</code>
	 * 
	 * @param key	Ключ
	 * @return		<code>true</code> значение, связанное с ключем имеется. <code>fasle</code> - не имеется.
	 */
	public boolean contains(Key key);

	/**
	 * Пуста ли таблица
	 * 
	 * @return <code>true</code> - таблица пуста. <code>fasle</code> - таблица не пуста.
	 */
	public boolean isEmpty();
	
	/**
	 * Получить количество пар ключ-значение в таблице
	 * 
	 * @return	Количество пар ключ-значение в таблице
	 */
	public int size();
		
	/**
	 * Все ключи из таблицы имен
	 * 
	 * @return	Ключи из  таблицы
	 */
	public Iterable<Key> keys();
	
}
