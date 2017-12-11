package com.domain.java;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class SPAMqualifier {
	public static void main(String[] args) {
		String fileName = "D:\\Moje Dokumenty\\Downloads\\zadania_zestaw4\\spambase\\spambase.data";

		try (FileReader fileReader = new FileReader(fileName);
				BufferedReader reader = new BufferedReader(fileReader);) {
			String nextLine = null;
			int lines = 0;
			ArrayList<ArrayList<Double>> messages = new ArrayList<ArrayList<Double>>();
			while ((nextLine = reader.readLine()) != null) {
				String[] messageString = nextLine.split(",");
				ArrayList<Double> message = new ArrayList<>();

				for (String string : messageString) {
					double element = Double.parseDouble(string);
					message.add(element);
				}
				messages.add(message);
				lines++;
			}

			long messageId = 1;
			long messageSpam = 0;
			long messageNonSpam = 0;

			for (ArrayList<Double> message : messages) {
				if (message.get(message.size() - 1) == 1) {
					System.out.println("id: " + messageId + " SPAM");
					messageSpam++;
				} else {
					System.out.println("id: " + messageId + " NON SPAM");
					messageNonSpam++;
				}
				messageId++;
			}
			DecimalFormat percentFormat = new DecimalFormat("##.##%");
			System.out.println("===============================");
			System.out.println("");
			System.out.println("Class Distribution:");
			System.out.println("Number of messages: " + lines);
			System.out.println("Number of SPAM: " + messageSpam + "   " + "("
					+ percentFormat.format((double) messageSpam / lines) + ")");
			System.out.println("Number of NON-SPAM: " + messageNonSpam + "   " + "("
					+ percentFormat.format((double) messageNonSpam / lines) + ")");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}