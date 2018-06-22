package ru.intuit.deepjava.firstIndependentWork.resourceSystem;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import ru.intuit.deepjava.firstIndependentWork.resourceSystem.base.VirtuaFileSystem;

public class VirtualFileSystemImpl implements VirtuaFileSystem {

	private String root;
	
	public VirtualFileSystemImpl(String root) {
		this.root = root;
	}

	@Override
	public boolean isExist(String path) {
		return new File(root + path).exists();
	}

	@Override
	public boolean isDirectory(String path) {
		return new File(root + path).isDirectory();
	}

	@Override
	public String getAbsolutePath(String file) {
		return new File(root + file).getAbsolutePath();
	}

	@Override
	public byte[] getBytes(String file) {
		String path = root + file;
		if(!isExist(path)) {
			return null;
		}
		List<Byte> list = new LinkedList<>();
		try(InputStream is = new FileInputStream(new File(path));
				BufferedInputStream buf = new BufferedInputStream(is)) {
			int i;
			while((i = buf.read()) != -1) {
				list.add((byte)i);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] byteArray = new byte[list.size()];
		for(int i = 0; i < list.size(); i++) {
			byteArray[i] = list.get(i);
		}
		return byteArray;
		/*
		 try {
			File file = new File(root + filePath);
			InputStream is = new FileInputStream(file);
			
		    long length = file.length();
		    if (length > Integer.MAX_VALUE) {
		        return null;
		    }
		 
		    byte[] bytes = new byte[(int)length];
		    int offset = 0;
		    int numRead = 0;
		    while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
		        offset += numRead;
		    }
		 
		    if (offset < bytes.length) {
		        throw new IOException("Could not completely read file " + file.getName());
		    }
		 
		    is.close();
		    return bytes;
		} catch(Exception err) {
			return null;
		} 
		 */
	}

	@Override
	public String getUTF8Text(String file) {
		StringBuilder sb = new StringBuilder();
		try (FileInputStream fileInputStream = new FileInputStream(new File(root + file))) {
			DataInputStream dataInputStream = new DataInputStream(fileInputStream);
			InputStreamReader inputStreamReader = new InputStreamReader(dataInputStream, StandardCharsets.UTF_8);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			
			String currentLine;
			while((currentLine = bufferedReader.readLine()) != null) {
				sb.append(currentLine).append(System.lineSeparator());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
		/*
		 if (!isExist(filePath)) {
			return null;
		}
		
		StringBuffer content = new StringBuffer();
		try{			
			FileReader fr = new FileReader(getAbsolutePath(filePath));
			BufferedReader br = new BufferedReader(fr);
			
			String text = br.readLine();
			while (text != null) {
				content.append(text);
				text = br.readLine();
				if (text != null) {
					content.append("\n");
				}
			}			
		} catch (Exception e) {
			// TODO: handle exception!
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return content.toString(); 
		 */
	}

	@Override
	public Iterator<String> getIterator(String startDirectory) {
		return new FileIterator(startDirectory);
	}
	
	
	private class FileIterator implements Iterator<String> {

		private Queue<File> files = new LinkedList<>();
		
		public FileIterator(String path) {
			files.add(new File(root + path));
		}
		
		@Override
		public boolean hasNext() {
			return !files.isEmpty();
		}

		@Override
		public String next() {
			File file = files.peek();
			if(file.isDirectory()) {
				for(File subFile : file.listFiles()) {
					files.add(subFile);
				}
			}
			return files.poll().getAbsolutePath();
		}

	}

	public static void main(String[] args) {
		VirtuaFileSystem vfs = new VirtualFileSystemImpl("");
		System.out.println("Current path: " + vfs.getAbsolutePath(""));
		Iterator<String> iterator = vfs.getIterator("./");
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}
	
}
