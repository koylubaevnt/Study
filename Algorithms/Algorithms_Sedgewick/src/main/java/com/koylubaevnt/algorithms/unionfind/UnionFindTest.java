package com.koylubaevnt.algorithms.unionfind;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.koylubaevnt.algorithms.utils.Timer;

public class UnionFindTest {

	public static FileInputStream openFile() {
		String name = "src/main/java/com/koylubaevnt/algorithms/unionfind/largeUF.txt";
		File file = new File(name);
		FileInputStream fis = null;
		
		if (file.exists()) {
            try {
				fis = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				throw new IllegalArgumentException("Could not open " + name);
			}
            
		}
		return fis;
	}
	
	public static void main(String[] args) {
		FileInputStream fis = null;
		Scanner scanner = null;
		
		long time = 0;
		int p, q, n;
		
		fis = openFile();
		scanner = new Scanner(new BufferedInputStream(fis));
		n = scanner.nextInt();
		Timer timer = new Timer();
		QuickUnionWHP quWHP = new QuickUnionWHP(n);
		while(scanner.hasNext()) {
			p = scanner.nextInt();
			q = scanner.nextInt();
			if(!quWHP.isConnected(p, q)) {
				quWHP.union(p, q);
			}
		}
		time += timer.elapsedTime();
		System.out.println("QuickUnionWHP: " + time + " miliseconds");
		
		time = 0;
		fis = openFile();
		scanner = new Scanner(new BufferedInputStream(fis));
		n = scanner.nextInt();
		timer = new Timer();
		QuickUnionW quW = new QuickUnionW(n);
		while(scanner.hasNext()) {
			p = scanner.nextInt();
			q = scanner.nextInt();
			if(!quW.isConnected(p, q)) {
				quW.union(p, q);
			}
		}
		time += timer.elapsedTime();
		System.out.println("QuickUnionW: " + time + " miliseconds");
		
		time = 0;
		fis = openFile();
		scanner = new Scanner(new BufferedInputStream(fis));
		n = scanner.nextInt();
		timer = new Timer();
		QuickUnion qu = new QuickUnion(n);
		while(scanner.hasNext()) {
			p = scanner.nextInt();
			q = scanner.nextInt();
			if(!qu.isConnected(p, q)) {
				qu.union(p, q);
			}
		}
		time += timer.elapsedTime();
		System.out.println("QuickUnion: " + time + " miliseconds");
		
		time = 0;
		fis = openFile();
		scanner = new Scanner(new BufferedInputStream(fis));
		n = scanner.nextInt();
		timer = new Timer();
		QuickFind qf = new QuickFind(n);
		while(scanner.hasNext()) {
			p = scanner.nextInt();
			q = scanner.nextInt();
			if(!qf.isConnected(p, q)) {
				qf.union(p, q);
			}
		}
		time += timer.elapsedTime();
		System.out.println("QuickFind: " + time + " miliseconds");
		
	}

}
