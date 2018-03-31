package edu.sjsu.cmpe275.aop;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import edu.sjsu.cmpe275.aop.aspect.StatsAspect;

public class TweetStatsImpl implements TweetStats {
	@Autowired
	private StatsAspect statsObj;


	public void resetStatsAndSystem() {
		statsObj.setLongestMsgLen(0);
		statsObj.productiveUserMap.clear();
		statsObj.blockedMap.clear();
		statsObj.followerMap.clear();
	}
	
	
    
	public int getLengthOfLongestTweetAttempted() {
		return statsObj.getLongestMsgLen();
	}
	
	

	public String getMostProductiveUser() {
		int maxProductiveUser = 0;
		String user = null;
			if (statsObj.productiveUserMap.size() != 0)
		{
			for (Map.Entry<String, Integer> entry : statsObj.productiveUserMap.entrySet())
			{
				String currentUser = entry.getKey();
				int currentSize = entry.getValue();
				if (currentSize > maxProductiveUser)
				{
					user = currentUser;
					maxProductiveUser = currentSize;
				}
			}
			return user; 
		}
		return null;
	}
	
	

	public String getMostBlockedFollower() {
		int maxBlockedUser=0;
		String user = null;
		if(statsObj.blockedMap.size() !=0)
		{
			for (Map.Entry<String, Set<String>> entry : statsObj.blockedMap.entrySet())
			  {
				String currentUser = entry.getKey();
				int currentSize = entry.getValue().size();
				if (currentSize > maxBlockedUser)
				{
					user = currentUser;
					maxBlockedUser = currentSize;
				}
			}
			return user; 
		}
		return null;
	}
	

	public String getMostFollowedUser() {
		int maxFollowedUser = 0;
		String user = null;
		/* If size of map is 0 then return null as no user is there in map */
		if (statsObj.followerMap.size() != 0)
		{
			for (Map.Entry<String, Set<String>> entry : statsObj.followerMap.entrySet())
			{
				String currentUser = entry.getKey();
				int currentSize = entry.getValue().size();
				if (currentSize > maxFollowedUser)
				{
					user = currentUser;
					maxFollowedUser = currentSize;
				}
			}
			return user; 
		}
		return null;
	}
	

    public void addTweetDetails(String user, int length){
		Integer exist_tweet_len = statsObj.productiveUserMap.get(user);
		if(exist_tweet_len== null)
		{
			exist_tweet_len=0;
		}
		else{
			exist_tweet_len = exist_tweet_len;
		}
		exist_tweet_len = exist_tweet_len+length;
		statsObj.productiveUserMap.put(user, exist_tweet_len);
		System.out.println("p_user_map"+statsObj.productiveUserMap);
	}
}



