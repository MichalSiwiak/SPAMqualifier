package com.domain.java;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;


/**
 * Reads the number of messages from the source file. 
 * Returns and count the number of all messages and classifies them as spam or non-spam messages
 * 
 * 
 * @author Michał Siwiak
 * @version 1.0
 *
 * @param  	fileName an absolute URL giving the base location of database
 * @return  number of messages
 */
public class SPAMqualifier {
	public static void main(String[] args) {

		String fileName = "spambase.data";

		try (BufferedReader reader = new BufferedReader(new FileReader(fileName));) {
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