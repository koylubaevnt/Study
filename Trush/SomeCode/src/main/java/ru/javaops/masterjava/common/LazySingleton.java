package ru.javaops.masterjava.common;

/**
 * Ленивая инициализация.
 * Релизация синглтона в Java.
 * 
 * @author KojlubaevNT
 *
 */
public final class LazySingleton {

	private static volatile LazySingleton instance;
	//private final int i;
	
	/**
	 * Java Virtual Machine может сначала инициализировать
	 * переменную instance объектом класса, но объект может быть
	 * еще не доинициализирован. Это было до Java 5!
	 * Поставить <code>volatile</code> для переменной.
	 * Еще можно сделать <code>final</code> для перемнной, для
	 * которой делается длительное вычисление. Т.к. объект
	 * не опубикуется пока не проинициализируются все <code>final</code>
	 * Если все члены класса final, то Double Cheked lockingможно использовать
	 * без ключевого слова <code>vilotile</code>
	 * 
	 * Использовать его не рекомендуется т.к. использование volatile модификатора 
	 * может привести к проблемам производительности на мультипроцессорных системах. 
	 * */
	private LazySingleton() {
		int i = 5 + 8;//Длительное действие
	}
	
	public static LazySingleton getInstance() {
		if(instance == null) {
			synchronized (LazySingleton.class) {
				//Double Checked locking
				if(instance == null) {
					instance = new LazySingleton();
				}
			}
		}
		return instance;
	}
	
	/**
	 * Initialization-on-demand holder idiom
	 * 
	 * Инициализация по требованию
	 * Безопасен за счет механизма <code>classloader</code> в Java.
	 * 
	 * Пока класс загружается все потоки ждут 
	 * (вложенные классы не инициализируются до первого их использования ).
	 * 
	 * Первый, кто запросил <code>instance</code> загрузит и получит класс.
	 * А далее уже его будут использовать другие 
	 */
	private static class LazyHolder {
		private static final LazySingleton INSTANCE = new LazySingleton(); 
	}
	
	public static LazySingleton getInstance2() {
		return LazyHolder.INSTANCE;
	}
}
