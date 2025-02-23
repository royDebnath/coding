package leetcode.all.heap;

import java.util.*;

/**
 * This problem simulates a basic form of Twitter functionality. It involves designing a system that allows users to post tweets, follow other users, unfollow users, and view the 10 most recent tweets in their news feed. Each tweet has a unique ID, and the news feed must display these tweets in order from most to least recent. The following functionalities are to be implemented:
 *
 * Twitter(): a constructor to initialize the Twitter object.
 * postTweet(userId, tweetId): allows a user to post a tweet.
 * getNewsFeed(userId): retrieves the 10 most recent tweets from the user and the users they follow.
 * follow(followerId, followeeId): allows a user to follow another user.
 * unfollow(followerId, followeeId): allows a user to unfollow another user.
 *
 * Approach
 *
 * The selection of Data Structures:
 *
 * We maintain a hashMap of users and their posts.
 * We also maintain a hashMap of user and a hashset of users the user follows
 * (a set of all the users the user follows)
 * We use a priority queue for getting the top recent tweets from the user himself and all the users that he is following. we make use of the helper class here to implement the merge k sorted list algorithm. there we need to maintain pointers for all the lists here we maintain the index for each list helper functions helps us to acvhieve that.
 * Also helper class helps us to compare two tweets based on time. Hence the helper class implements the Comparable interface that compare two helper object based on time ie the recent first.
 * we have a time variable which keeps track of the time and it is incremented everytime a tweet is added. this time is used to compare two different tweets.
 *
 *
 *
 */
public class Q355_DesignTwitter {

}
class Twitter {
    class Helper implements Comparable<Helper>
    {
        int time;
        int index;
        int userId;
        int tweetId;
        @Override
        public int compareTo(Helper o)
        {
            return  o.time - this.time;
        }
        Helper(int[] tweet, int index, int userId)
        {
            tweetId = tweet[0] ;
            this.time = tweet[1];
            this.index = index;
            this.userId = userId;
        }

        // to get the previous element we are storing the most recent in the end. hence going back.
        int nextFeed()
        {
            return index-1;
        }

    }

    // global time variable (helpful in returning the most recent tweets first)
    int time ;
    // to store what all users a user follows
    HashMap<Integer, HashSet<Integer>> followsMap ;
    // to store the tweet and its time
    HashMap<Integer, List<int[]>> tweetsMap;

    public Twitter() {
        followsMap = new HashMap();
        tweetsMap = new HashMap();
        // time count
        time = 0;
    }

    public void postTweet(int userId, int tweetId) {
        List<int[]> tweets = tweetsMap.getOrDefault(userId, new LinkedList());
        tweets.add(new int[]{ tweetId, time++});
        tweetsMap.put(userId, tweets);
    }

    public List<Integer> getNewsFeed(int userId) {
        // merge k sorted algorithm on a linkedlist/ arraylist
        PriorityQueue<Helper> pq = new PriorityQueue();
        List<int[]> tweets;

        tweets = tweetsMap.getOrDefault( userId,new LinkedList() );
        // construct a helper method to take care of the last index and sort tweets based on time in the priority queue with the help the comparable interface (recent first)
        if(tweets.size()>0)
            pq.add( new Helper(tweets.getLast(),tweets.size()-1, userId) );

        // add all the followers tweets also to the priority queue

        // if(followsMap.containsKey(userId))
        for(int followingId: followsMap.getOrDefault(userId, new HashSet<Integer>()))
        {
            tweets = tweetsMap.getOrDefault(followingId, new LinkedList());
            if(tweets.size()>0)
                pq.add(new Helper(tweets.getLast(), tweets.size()-1, followingId));
        }
        List<Integer> feeds  =  new LinkedList();
        while(!pq.isEmpty() && feeds.size()<10)
        {
            Helper feed = pq.poll();
            feeds.add(feed.tweetId);
            if(feed.nextFeed()>=0)
            {

                pq.add(new Helper( tweetsMap.get(feed.userId).get(feed.nextFeed()) , feed.nextFeed() ,feed.userId ));
            }
        }
        return feeds;
    }

    public void follow(int followerId, int followeeId) {
        HashSet<Integer> follows = followsMap.getOrDefault(followerId , new HashSet());
        follows.add(followeeId);
        followsMap.put(followerId, follows);
    }

    public void unfollow(int followerId, int followeeId) {
        if(followsMap.containsKey(followerId))
            followsMap.get(followerId).remove(followeeId);
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId, tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId, followeeId);
 * obj.unfollow(followerId, followeeId);
 */