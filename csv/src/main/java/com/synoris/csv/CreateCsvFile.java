package com.synoris.csv;

import java.io.FileWriter;

public class CreateCsvFile {

	public static void main(String[] args) {
		
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append("Name").append(",").append("Website").append(",").append("Country").append("\n");
		
		stringBuilder.append("Raj").append(",").append("www.infyt.com").append(",").append("India").append("\n");
		stringBuilder.append("Amar").append(",").append("www.infyt.com").append(",").append("USA").append("\n");
		stringBuilder.append("Sid").append(",").append("www.infyt.com").append(",").append("Thailand").append("\n");
		
		try (FileWriter writer = new FileWriter("D:\\Assign\\demo.csv")){
			
			writer.write(stringBuilder.toString());
			System.out.println("CSV file created..");
		} catch (Exception e) {
			
		}
		
	}
		
}
