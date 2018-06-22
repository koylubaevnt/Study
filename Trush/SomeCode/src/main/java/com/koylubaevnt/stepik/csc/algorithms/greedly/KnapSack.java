package com.koylubaevnt.stepik.csc.algorithms.greedly;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * Задача о непрерывном рюкзаке
 * 
 * Вход: веса w1...wn и стоимости c1...cn данных n предметов; 
 * 		вместимость рюкзака W.
 * Выход: максимальная стоимость частей предметов суммарного 
 * 		веса не более W
 * 
 * @author KojlubaevNT
 *
 */
public class KnapSack {

	private static class Item {
		double cost;
		int weight;
		
		public Item(double c, int w) {
			super();
			this.cost = c;
			this.weight = w;
		}
		
		
	}
	
	public static List<Item> continuousBackpack(int n, int W, Item[] items) {
		Arrays.sort(items, new Comparator<Item>() {
			@Override
			public int compare(Item o1, Item o2) {
				//return -Double.compare(o1.cost / o1.weight, o2.cost / o2.weight);
				return -Long.compare((long)o1.cost * o2.weight, (long)o2.cost * o1.weight);
			}
		});
		
		List<Item> result = new ArrayList<> ();
		
		for (Item item : items) {
			if (W == 0) {
				break;
			}
			if (item.weight <= W) {
				result.add(new Item(item.cost, item.weight));
				W -= item.weight;
			} else {
				result.add(new Item(item.cost * W / item.weight, W));
				W = 0;
			}
		}
		
		return result;
		
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int W = scanner.nextInt();
		Item[] items = new Item[n];
		
		int c;
		int w;
		for(int i = 0; i < n; i++) {
			c = scanner.nextInt();
			w = scanner.nextInt();
			items[i] = new Item(c, w);
		}
		scanner.close();
		
		List<Item> list = continuousBackpack(n, W, items);
		double sum = 0.0;
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Item item = (Item) iterator.next();
			sum += item.cost;
			System.out.println(item.cost + " " + item.weight);
		}
		System.out.println(sum);
	}
	
}
