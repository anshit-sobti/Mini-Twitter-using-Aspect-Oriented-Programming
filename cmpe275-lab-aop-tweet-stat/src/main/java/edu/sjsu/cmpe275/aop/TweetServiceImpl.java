package edu.sjsu.cmpe275.aop;

import java.io.IOException;

public class TweetServiceImpl implements TweetService {

    /***
     * Following is a dummy implementation.
     * You can tweak the implementation to suit your need, but this file is NOT part of the submission.
     */

	public void tweet(String user, String message) throws IllegalArgumentException, IOException {
		//System.out.println("\nthrowing");
		//throw new IOException();
    	System.out.printf("User %s tweeted message: %s\n", user, message);
    }

    public void follow(String follower, String followee) throws IOException {
    	System.out.println("\nthrowing");
    	throw new IOException();
       	//System.out.printf("User %s followed user %s \n", follower, followee);
    }

	public void block(String user, String followee) throws IOException {
		// TODO Auto-generated method stub
		
	}

}
