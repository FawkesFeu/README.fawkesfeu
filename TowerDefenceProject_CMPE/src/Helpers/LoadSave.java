package Helpers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;

import object.PathPoint;

public class LoadSave {
	
	public static BufferedImage getSpriteAtlas() {
		
	BufferedImage image = null;
	InputStream is = LoadSave.class.getClassLoader().getResourceAsStream("spriteatlas.png");
		
		try {
			image = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return image;
		
	}
	
	
	//Create a new lvl with default values
	public static void CreateLevel(String name, int[] idArr) {
		File newLevel = new File("res/" + name + ".txt");
		
		if(newLevel.exists()) {
			System.out.println("File " + name + " already exists.");
			return;
		}else {
			try {
				newLevel.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			writeToFile(newLevel, idArr, new PathPoint(0,0), new PathPoint(0,0));
		}
		
		
	}
	
	private static void writeToFile(File f, int[] idArr,PathPoint start, PathPoint end) {
	
		
		try {
			PrintWriter pw = new PrintWriter(f);
			
			for(int i : idArr) {
				pw.println(i);
			}
			
			pw.println(start.getxCord());
			pw.println(start.getyCord());
			pw.println(end.getxCord());
			pw.println(end.getyCord());
			
			pw.close();
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public static void SaveLevel(String name, int[][] idArr, PathPoint start, PathPoint end) {
		File levelFile = new File("res/" + name + ".txt");
		
		if(levelFile.exists()) {
			writeToFile(levelFile, Utils.TwoDto1DArr(idArr),start,end);
		}else {
			System.out.println("File: " + name + " does not exist.");
			return;
		}
		
	}
	
	private static ArrayList<Integer> readFromFile(File file) {
		ArrayList<Integer> list = new ArrayList<>();
		try {
			Scanner sc = new Scanner(file);
			
			while(sc.hasNextLine()) {
				list.add(Integer.parseInt(sc.nextLine()));
			}
			
			sc.close();
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		
		return list;
	}
	
	public static ArrayList<PathPoint> GetLevelPathPoints(String name){
		File lvlFile = new File("res/"+name+".txt");
		
		if(lvlFile.exists()) {
			ArrayList<Integer> list = readFromFile(lvlFile);
			ArrayList<PathPoint> points = new ArrayList<>();
			points.add(new PathPoint(list.get(400),list.get(401)));
			points.add(new PathPoint(list.get(402),list.get(403)));
			return points;
		}else {
			System.out.println("File: " + name + " does not exist.");
			return null;
		}
	}
	
	public static int[][] getLevelData(String name) {
		File lvlFile = new File("res/"+name+".txt");
		
		if(lvlFile.exists()) {
			ArrayList<Integer> list = readFromFile(lvlFile);
			return Utils.ArrayListTo2Dint(list, 20, 20);
		}else {
			System.out.println("File: " + name + " does not exist.");
			return null;
		}
		
		
		
	}
	
	//Save 2d int array to file
	//Load int array from file

	


}
