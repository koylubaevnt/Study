package com.koylubaevnt.own.others;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


class Element {
/*
	public static final int YES = 1;
	public static final int NO = -1;
	public static final int UNKNOWN = 0;
	*/
	
	private Integer[] array;
	private Integer[] leftChange;
	private Integer[] rightChange;
	//private Integer[] arrayOld;
	//private int canSorted = UNKNOWN;
	
	public Element(Integer[] array, Integer[] leftChange, Integer[] rightChange/*, int index, Integer[] arrayOld*/) {
		this.array = array;
		this.leftChange = leftChange;
		this.rightChange = rightChange;
		//this.index = index;
		//this.arrayOld = arrayOld;
	}
	
	public Integer[] getArray() {
		return array;
	}
	
	public Integer[] getLeftChange() {
		return leftChange;
	}
	
	public Integer[] getRightChange() {
		return rightChange;
	}
	/*
	public int getCanSorted() {
		return canSorted;
	}
	
	public void setCanSorted(int canSorted) {
		this.canSorted = canSorted;
	}
	
	public int getIndex() {
		return index;
	}
	
	public Integer[] getArrayOld() {
		return arrayOld;
	}
*/
	@Override
	public boolean equals(Object obj) {
		return obj instanceof Element 
				&& Arrays.equals(array, ((Element)obj).array) 
				//&& Arrays.equals(arrayOld, ((Element)obj).arrayOld)
				&& Arrays.equals(leftChange, ((Element)obj).leftChange)
				&& Arrays.equals(rightChange, ((Element)obj).rightChange);
				//&& ((Element)obj).index == index;
	}
	
	@Override
	public int hashCode() {
		int result = Arrays.hashCode(array);
	//	result += Arrays.hashCode(arrayOld);
		result += Arrays.hashCode(leftChange);
		result += Arrays.hashCode(rightChange);
		return result;
	}
	
	@Override
	public String toString() {
		return "Element [array=" + Arrays.toString(array) + ", leftChange=" + Arrays.toString(leftChange)
				+ ", rightChange=" + Arrays.toString(rightChange) //+ ", arrayOld=" + Arrays.toString(arrayOld)
				+/* ", index=" + index + */"]";
	}
	
	
}

class Node {
	
	Node prevNode = null;
	private Element element;
	private List<Element> arrayList = new ArrayList<>();
	
	public Node(Node prevNode, Element element) {
		this.prevNode = prevNode;
		this.element = element;
	}
	
	public Node(Element element) {
		this(null, element);
	}
	
	public void add(Element element) {
		arrayList.add(element);
	}
	
	public Node getPrevNode() {
		return prevNode;
	}
	
	public Element getElement() {
		return element;
	}
	
	public List<Element> getArrayList() {
		return arrayList;
	}

	public int getSize() {
		int result = 0;
		Node n = prevNode;
		while(n != null) {
			result++;
			n = n.prevNode;
		}
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof Node
				&& 	element.equals(((Node)obj).element);
	}
	
	@Override
	public int hashCode() {
		return element.hashCode();
	}
	
	@Override
	public String toString() {
		return "Node [prevNode=" + prevNode + ", element=" + element + ", arrayList=" + arrayList + "]";
	}
	
	
}


public class SloutionArraySort {

	static Node parentNode;
	static List<Node> nodeAnswers = new ArrayList<>();
	//static Set<String> unique = new HashSet<>();
	static Set<Element> unique = new HashSet<>();
	static StringBuilder sb = new StringBuilder();
	
	public static boolean sort(Integer[] array){
		if(isSorted(array)) {
			return true;
		} 
		
		boolean result = false;
		//Element parentElement = new Element(array, new Integer[] { null, null }, new Integer[] { null, null },  /*0, */null);
		Element parentElement = new Element(array, new Integer[] { null, null }, new Integer[] { null, null });
		nodeAnswers.clear();
		unique.clear();
		parentNode = new Node(parentElement);
		//unique.add(Arrays.toString(array));
		unique.add(parentElement);
		sort(parentNode);
		
		if(nodeAnswers.size() > 0) {
			//Массив отсортирован...
			result = true;
			int indexMin = 0;
			int min = nodeAnswers.get(0).getSize();
			for(int i = 1; i < nodeAnswers.size(); i++) {
				if(min > nodeAnswers.get(i).getSize()) {
					min = nodeAnswers.get(i).getSize();
					indexMin = i;
				}
			}
			
			Node node = nodeAnswers.get(indexMin);
			sb = new StringBuilder();
			Deque<Element> queue = new ArrayDeque<>();
			Integer[] leftChange = null, rightChange = null;
			while(node != null) {
				Element curElement = node.getElement();
				if(node == nodeAnswers.get(indexMin)) {
					//queue.addFirst(new Element(curElement.getArray(), null, null, /*0,*/ curElement.getArrayOld()));
					queue.addFirst(new Element(curElement.getArray(), null, null));
				} else {
					//queue.addFirst(new Element(curElement.getArray(), leftChange, rightChange, /*0,*/ curElement.getArrayOld()));
					queue.addFirst(new Element(curElement.getArray(), leftChange, rightChange));
				}
				leftChange = curElement.getLeftChange();
				rightChange = curElement.getRightChange();
				node = node.getPrevNode();
			}
			while(!queue.isEmpty()) {
				Element element = queue.removeFirst();
				sb.append(Arrays.toString(element.getArray()));
				if(element.getLeftChange() != null) {
					sb.append(" ")
					.append(Arrays.toString(element.getLeftChange()))
					.append("<->")
					.append(Arrays.toString(element.getRightChange()));
				}	
				sb.append("\n");
			}
		}
		return result;
	}
	
	public static void sort(Node node) {
		Element el = node.getElement();
		Integer[] array = el.getArray();
		for(int i = 0; i < array.length - 3; i++) {
			for(int j = i + 2; j  < array.length - 1; j++) {
				Integer[] arrayNew = Arrays.copyOf(array, array.length);
				replace(arrayNew, i, j);
				//Element element = new Element(arrayNew, new Integer[] { array[i], array[i + 1] }, new Integer[] { array[j], array[j + 1] }, /*i + j,*/ el.getArray());
				Element element = new Element(arrayNew, new Integer[] { array[i], array[i + 1] }, new Integer[] { array[j], array[j + 1] });
				if(!isSorted(arrayNew)) {
					if(!unique.contains(element)) {
						unique.add(element);
						node.add(element);
					}
				} else {
					Node n = new Node(node, element);
					nodeAnswers.add(n);
				}
			}
		}
		
		List<Element> list = node.getArrayList();
		for(int i = 0;  i < list.size(); i++) {
			Node newNode = new Node(node, list.get(i));
			sort(newNode);
		}
	}
	
	private static void replace(Integer[] array, int i, int j) {
		Integer tmp1 = array[i];
		Integer tmp2 = array[i + 1];
		array[i] = array[j];
		array[i + 1] = array[j + 1];
		array[j] = tmp1;
		array[j + 1] = tmp2;
	}
	
	private static boolean isSorted(Integer[] array) {
		Integer[] newA = Arrays.copyOf(array, array.length);
	    Arrays.sort(newA);
	    return Arrays.equals(array, newA);
	}
	
	public static void main(String[] args) {
		Integer[] array1 = { 1, 6, 3, 2, 5, 4 };
		Integer[] array2 = { 3, 5, 2, 4, 6, 1 };
		Integer[] array3 = { 5, 4, 1, 6, 3, 2 };//541632 
		Integer[] array4 = { 1, 2, 4, 6, 5, 3 };
		Integer[] array5 = { 5, 6, 1, 2, 3, 4 };
		/*
		if(sort(array1)) {
			System.out.println("Массив " + Arrays.toString(array1) + " ОТСОРТИРОВАН: ");
			System.out.println(sb.toString());
		} else {
			System.out.println("ОШИБКА, массив " + Arrays.toString(array1) + " должен сортироваться");
		}
		
		if(sort(array2)) {
			System.out.println("Массив " + Arrays.toString(array2) + " ОТСОРТИРОВАН");
			System.out.println(sb.toString());
		} else {
			System.out.println("ОШИБКА, массив " + Arrays.toString(array2) + " должен сортироваться");
		}
		
		if(sort(array3)) {
			System.out.println("Массив " + Arrays.toString(array3) + " ОТСОРТИРОВАН");
			System.out.println(sb.toString());
		} else {
			System.out.println("ОШИБКА, массив " + Arrays.toString(array3) + " должен сортироваться");
		}
		if(sort(array4)) {
			System.out.println("ОШИБКА, массив " + Arrays.toString(array4) + " не должен сортироваться");
			System.out.println(sb.toString());
		} else {
			System.out.println("Массив " + Arrays.toString(array4) + " НЕ СОРТИРУЕТСЯ!");
		}*/
		if(sort(array5)) {
			System.out.println("Массив " + Arrays.toString(array5) + " ОТСОРТИРОВАН");
			System.out.println(sb.toString());
		} else {
			System.out.println("Массив " + Arrays.toString(array5) + " НЕ СОРТИРУЕТСЯ!");
		}
	}

}
