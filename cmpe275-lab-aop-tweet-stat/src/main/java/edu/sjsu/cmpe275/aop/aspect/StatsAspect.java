package edu.sjsu.cmpe275.aop.aspect;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.annotation.Order;

import edu.sjsu.cmpe275.aop.TweetService;
import edu.sjsu.cmpe275.aop.TweetStatsImpl;

@Aspect
@Order(0)
public class StatsAspect {

	private HashMap<String, Integer> UserTweetStore=new HashMap<String, Integer>();

	public static TreeMap<String, Integer> productiveUserMap = new TreeMap<String, Integer>();

	public static TreeMap<String, Set<String>> followerMap = new TreeMap<String, Set<String>>();

	public static TreeMap<String, Set<String>> blockedMap = new TreeMap<String, Set<String>>();

	private int longestMsgLen=0;
	private String mostProductiveUser;
	private String mostFollowedUser;
	private String mostBlockedUser;

	public int getLongestMsgLen() {
		return longestMsgLen;
	}

	public void setLongestMsgLen(int longestMsgLen) {
		this.longestMsgLen = longestMsgLen;
	}
	
	public String getMostProductiveUser() {
		return mostProductiveUser;
	}

	public void setMostProductiveUser(String mostProductiveUser) {
		this.mostProductiveUser = mostProductiveUser;
	}

    public String getMostFollowedUser() {
		return mostFollowedUser;
	}

    public void setMostFollowedeUser(String mostFollowedUser) {
		this.mostFollowedUser = mostFollowedUser;
	}

	public String getMostBlockedUser() {
		return mostBlockedUser;
	}

	public void setMostBlockedUser(String mostBlockedUser) {
		this.mostBlockedUser = mostBlockedUser;
	}

	
	@Autowired TweetStatsImpl tweetStatsImpl;

	@Before("execution(public void edu.sjsu.cmpe275.aop.TweetService.tweet(..)) && args(user, tweet)")
	public void tweetAdvice(JoinPoint joinPoint, String user, String tweet)throws IllegalArgumentException{
		System.out.println("before tweet call");

		Integer lengthOfTweet=tweet.length();
		System.out.println("IN THE STATS ASPECT ----HASHMAP STORING-----");
		System.out.printf("The user message is:  %s",tweet);
		System.out.printf("The length of user message is %d", lengthOfTweet );

		if(lengthOfTweet>this.getLongestMsgLen()){
			this.setLongestMsgLen(lengthOfTweet);
		}
		if(lengthOfTweet<=140){
			System.out.printf("The tweet is inserted");
			if(!UserTweetStore.containsKey(user)){
				UserTweetStore.put(user,lengthOfTweet);
			}
			else{

				int temp=UserTweetStore.get(user).intValue();
				temp+=lengthOfTweet;
				UserTweetStore.put(user, temp);
			}
		}
		else{
			System.out.printf("The Tweet %s is not inserted as it's length is greater than 140: ",tweet);
			throw new IllegalArgumentException("Message Length Exceeded");
		}
	}


	public HashMap<String, Integer> getTweetMap(){
		return UserTweetStore;
	}



	public Integer getLengthLongestMsg(){
		return longestMsgLen;
	}





	@AfterReturning("execution(public void edu.sjsu.cmpe275.aop.TweetService.tweet(..)) && args(user, tweet)")
	public void dummyAfterAdvice(JoinPoint joinPoint,String user, String tweet) {
		System.out.printf("After the executuion of the method %s\n", joinPoint.getSignature().getName());

		for (Object o : joinPoint.getArgs()){
			System.out.println((String)o);
		}
		tweetStatsImpl.addTweetDetails((String)joinPoint.getArgs()[0], ((String)joinPoint.getArgs()[1]).length());
	}

	@AfterReturning("execution(public void edu.sjsu.cmpe275.aop.TweetService.follow(..)) && args(follower, followee)")
	public void followAdvice1(JoinPoint joinPoint, String follower, String followee){
		Set<String> temp= followerMap.get(followee);
		if(temp == null){
			temp= new HashSet<String>();
			temp.add(follower);
			followerMap.put(followee, temp);
			System.out.println(temp);
		}else{
			temp.add(follower);
		}
	}



	@AfterReturning("execution(public void edu.sjsu.cmpe275.aop.TweetService.block(..)) && args(user, followee)")
	public void followAdvice2(JoinPoint joinPoint, String user, String followee){
		Set<String> temp= blockedMap.get(followee);
		if(temp == null){
			temp= new HashSet<String>();
			temp.add(user);
			blockedMap.put(followee, temp);
			System.out.println(temp);
		}else{
			temp.add(user);
		}
	}
}
