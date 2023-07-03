package com.synoris.csv;

	import java.io.*;
	import java.util.ArrayList;
	import java.util.List;

	public class RecordExporterImporter {
	    public static void exportRecords(List<Record> records, String exportFilePath) {
	        try (FileOutputStream fileOut = new FileOutputStream(exportFilePath);
	             ObjectOutputStream objOut = new ObjectOutputStream(fileOut)) {
	            objOut.writeObject(records);
	            System.out.println("Records exported successfully.");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    @SuppressWarnings("unchecked")
	    public static List<Record> importRecords(String importFilePath) {
	        List<Record> importedRecords = new ArrayList<>();
	        try (FileInputStream fileIn = new FileInputStream(importFilePath);
	             ObjectInputStream objIn = new ObjectInputStream(fileIn)) {
	            importedRecords = (List<Record>) objIn.readObject();
	            System.out.println("Records imported successfully.");
	        } catch (IOException | ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	        return importedRecords;
	    }

	    public static void main(String[] args) {
	        // Sample existing records
	        List<Record> existingRecords = new ArrayList<>();
	        existingRecords.add(new Record(1, "Chandrakant", 25));
	        existingRecords.add(new Record(2, "Shravan", 30));
	       
	        // Export records
	        String exportFilePath = "records_export.dat";
	        exportRecords(existingRecords, exportFilePath);

	        // Import records
	        String importFilePath = "records_export.dat";
	        List<Record> importedRecords = importRecords(importFilePath);

	        // Perform validation and update existing records
	        for (Record importedRecord : importedRecords) {
	            if (isValidRecord(importedRecord)) {
	                // Update existing record or add a new record
	                int index = findRecordIndex(existingRecords, importedRecord.getId());
	                if (index != -1) {
	                    existingRecords.set(index, importedRecord);
	                } else {
	                    existingRecords.add(importedRecord);
	                }
	            }
	        }

	        // Print updated records
	        System.out.println("Updated Records:");
	        for (Record record : existingRecords) {
	            System.out.println(record);
	        }
	    }

	    // Example validation method
	    private static boolean isValidRecord(Record record) {
	        return record.getId() > 0 && record.getAge() > 0;
	    }

	    // Helper method to find the index of a record in the list based on ID
	    private static int findRecordIndex(List<Record> records, int id) {
	        for (int i = 0; i < records.size(); i++) {
	            if (records.get(i).getId() == id) {
	                return i;
	            }
	        }
	        return -1; // Record not found
	    }
	}

	class Record implements Serializable {
	    private int id;
	    private String name;
	    private int age;

	    public Record(int id, String name, int age) {
	        this.id = id;
	        this.name = name;
	        this.age = age;
	    }

	    public int getId() {
	        return id;
	    }

	    public String getName() {
	        return name;
	    }

	    public int getAge() {
	        return age;
	    }

	    @Override
	    public String toString() {
	        return "Record [id=" + id + ", name=" + name + ", age=" + age + "]";
	    }
	}


