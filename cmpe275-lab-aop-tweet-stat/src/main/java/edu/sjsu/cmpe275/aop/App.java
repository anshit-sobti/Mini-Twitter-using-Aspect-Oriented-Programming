package edu.sjsu.cmpe275.aop;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;


public class App {
	public static void main(String[] args) {
		/***
		 * Following is a dummy implementation of App to demonstrate bean creation with Application context.
		 * You may make changes to suit your need, but this file is NOT part of the submission.
		 */

		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("context.xml");
		TweetService tweeter = (TweetService) ctx.getBean("tweetService");
		TweetStats stats = (TweetStats) ctx.getBean("tweetStats");
//		while(true){
//			try {
//				tweeter.tweet("Anshit", "Hello, this is my first tweet. abcdgggghhfhfhffhfhfhhfhfhfhggggggggggdhdbdhdvgdvdgdvdgvdgdvdgdvdhdvhdvdhdvdhdbvdhbdhdbdhjdbdjbdjdbdjdbdjdbdjdbdjdhhdh");
//				tweeter.tweet("Anshit", "Hello, this is my second tweet.");
//				tweeter.tweet("Kshitij", "Hello, this is my second tweet.");
//
//				tweeter.follow("Anshit", "Kshitij");
//				tweeter.follow("Raghav", "Kshitij");
//				tweeter.follow("Raghav", "Anshit");
//				tweeter.block("Kshitij", "Tushar");
//				tweeter.block("Naveen", "Tushar");
//
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
		
		
//		try {
//			tweeter.tweet("Anshit", "Hello, this is my first tweet. abcdgggghhfhfhffhfhfhhfhfhfhggggggggggdhdbdhdvgdvdgdvdgvdgdvdgdvdhdvhdvdhdvdhdbvdhbdhdbdhjdbdjbdjdbdjdbdjdbdjdbdjdhhdh");
//			
//		} catch (IllegalArgumentException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		try {
			tweeter.tweet("Anshit", "aaaa");			
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			tweeter.follow("Anshit", "Kshitij");
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			tweeter.follow("Raghav", "Anshit");
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			tweeter.block("Anshit", "Kshitij");
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		
		System.out.println("Most productive user: " + stats.getMostProductiveUser());
		System.out.println("Most followed user: " + stats.getMostFollowedUser());
		System.out.println("Length of the longest tweet attempted:  " + stats.getLengthOfLongestTweetAttempted());
		System.out.println("Most blocked user:  " + stats.getMostBlockedFollower());

		ctx.close();
	}
}
