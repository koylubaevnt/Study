package ru.javaops.masterjava.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MemoryLeaks {

	/**
	 * Утечка памяти, т.к. мы храним в Map ссылки на картинки и никогда не удаляем их.
	 * Сборщик мусора не сможет удалить не используемые объекты...
	 * 
	 * Избежать: использование weak или soft references
	 * weak - GC удаляет объект, если нет "сильных ссылок"
	 * soft - GC удаляет объект, если не хватает памяти	
	*/
	Map<String, Bitmap> imagesWeakMemory = new HashMap<String, Bitmap>();
	public Bitmap getWeakBitmap(String name) {
		if(!imagesWeakMemory.containsKey(name)) {
			//Загрузка картинки долгая операция, используется кеш для ускорения
			imagesWeakMemory.put(name, loadBitmap(name));
		}
		return imagesWeakMemory.get(name);
	}
	public void workWithWeakMap() {
		Random random = new Random();
		Bitmap picture = getWeakBitmap("picture" + random.nextInt(100));
		System.out.println(picture);
	}
	
	//Использование WEAK references
	Map<String, WeakReference<Bitmap>> imagesGoodMemory = new HashMap<String, WeakReference<Bitmap>>();
	public Bitmap getGoodBitmap(String name) {
		if(!imagesGoodMemory.containsKey(name) || imagesGoodMemory.get(name).get() == null) {
			//Загрузка картинки долгая операция, используется кеш для ускорения
			imagesGoodMemory.put(name, new WeakReference<Bitmap>(loadBitmap(name)));
		}
		return imagesGoodMemory.get(name).get();
	}
	
	public void workWithGoodMap() {
		Random random = new Random();
		Bitmap picture = getWeakBitmap("picture" + random.nextInt(100));
		System.out.println(picture);
	}
	
	
	//Избегать memory leaks:
	//Закрывать потоки, сокеты, соединения
	private  void avoidMemoryLeaks() throws IOException, SQLException {
		//Java 7 - try-catch-resources: 
		//hidden finnaly block - close all resources wich declared in try block
		// and implements AutoCloseable interface (include interface Closeable)
		try (OutputStream out = new FileOutputStream(new File("test.txt"))) {
			out.write(new byte[] {1, 2, 3, 4, 5});
		}
		
		//Older Java
		OutputStream out = new FileOutputStream(new File("test2.txt")); 
		try{
			out.write(new byte[] {1, 2, 3, 4, 5});
		} finally {
			out.close();
		}
		
		
		//Java 7 - try-catch-resources
		//hidden finnaly block - close all resources wich declared in try block
		// and implements AutoCloseable interface (include interface Closeable)
		try(PreparedStatement stmt = createStatement();
			ResultSet rs = stmt.executeQuery();) {
			//Work with result set
			
		}
		
		//Older Java
		PreparedStatement stmt = createStatement();
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery();
			//Work with result set
		} finally {
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(rs != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	private PreparedStatement createStatement() {
		return null;
	}
	private static Bitmap loadBitmap(String name) {
		return new Bitmap(name);
	}
	
	private static class Bitmap {
		
		private final String name;
		
		public Bitmap(String name) {
			this.name = name;
		}
		
		@Override
		public String toString() {
			return this.getClass().getName() + "[" + name + "]";
		}
	}
	
}

