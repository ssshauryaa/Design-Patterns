package restaurantRating;

import java.util.*;
import java.util.stream.Stream;

import static java.util.Collections.*;

public class RestaurantManagement {
    HashMap<String, User> users = new HashMap<String, User>();
    HashMap<String, Restaurant> restaurants = new HashMap<String, Restaurant>();

    void addUser(String userName) throws Exception {
        if(!users.containsKey(userName)) {
            User user = new User(userName);
            users.put(userName, user);
        } else {
            throw new Exception("UserName " + userName + " already exists");
        }
    }

    void addRestaurant(String restaurantName) throws Exception {
        if(!restaurants.containsKey(restaurantName)) {
            Restaurant restaurant = new Restaurant(restaurantName);
            restaurants.put(restaurantName, restaurant);
        } else {
            throw new Exception("RestaurantName " + restaurantName + " already exists");
        }
    }

    void addRating(String userName, String restaurantName, int rating) throws Exception {
        if(!users.containsKey(userName)) {
            throw new Exception("Invalid User" + userName);
        }
        if(!restaurants.containsKey(restaurantName)) {
            throw new Exception("Invalid Restaurant" + restaurantName);
        }
        if(rating<1 || rating>10) {
            throw new Exception("Invalid Rating. It must be between 1 and 10");
        }
        restaurants.get(restaurantName).addRating(users.get(userName), new Rating(rating));
    }

    void addRating(String userName, String restaurantName,
                   int rating, List<String> items,
                   String description) throws Exception {
        if(!users.containsKey(userName)) {
            throw new Exception("Invalid User" + userName);
        }
        if(!restaurants.containsKey(restaurantName)) {
            throw new Exception("Invalid Restaurant" + restaurantName);
        }
        if(rating<1 || rating>10) {
            throw new Exception("Invalid Rating. It must be between 1 and 10");
        }
        restaurants.get(restaurantName).addRating(users.get(userName), new Rating(rating, description, items));
    }

    void getRestaurant(String restaurantName) throws Exception {
        if(!restaurants.containsKey(restaurantName)) {
            throw new Exception("Invalid Restaurant" + restaurantName);
        }

        System.out.println(restaurants.get(restaurantName).toString());
    }

    void listRestaurants(int n) {
        List<Restaurant> restaurantList = new ArrayList<Restaurant>();
        for (Map.Entry<String, Restaurant> e: restaurants.entrySet()) {
            restaurantList.add(e.getValue());
        }

        Collections.sort(restaurantList);
        for(Restaurant restaurant: restaurantList) {
            n--;
            if(n>=0) {
                System.out.println(restaurant);
            } else {
                break;
            }

        }
    }

    void listRatings(String restaurantName, int count) throws Exception {
        if(!restaurants.containsKey(restaurantName)) {
            throw new Exception("Invalid Restaurant" + restaurantName);
        }
        HashMap<User, Rating> ratingMap = restaurants.get(restaurantName).getRatings();
        List<Rating> ratingList = new ArrayList<Rating>();

        for (Map.Entry<User, Rating> e: ratingMap.entrySet()) {
            ratingList.add(e.getValue());
        }

        Collections.sort(ratingList);
        for(Rating rating: ratingList) {
            if(count>0) {
                System.out.println(rating);
                count--;
            } else {
                break;
            }
        }
    }

    void listRatings(String restaurantName, int count, String type, HashMap<String,Integer> filter) throws Exception {
        if(!restaurants.containsKey(restaurantName)) {
            throw new Exception("Invalid Restaurant" + restaurantName);
        }
        HashMap<User, Rating> ratingMap = restaurants.get(restaurantName).getRatings();
        List<Rating> ratingList = new ArrayList<Rating>();

        int lowerLimit = 1;
        int upperLimit = 10;
        if(filter.containsKey("lower_limit")) {
            lowerLimit = filter.get("lower_limit");
        }
        if(filter.containsKey("upper_limit")) {
            upperLimit = filter.get("upper_limit");
        }
        for (Map.Entry<User, Rating> e: ratingMap.entrySet()) {
            Rating rating = e.getValue();
            if(rating.getRating()>=lowerLimit && rating.getRating()<upperLimit) {
                ratingList.add(e.getValue());
            }
        }

        Collections.sort(ratingList);
        if(type.equals("ASC")) {
            Collections.reverse(ratingList);
        } else if(!type.equals("DESC")) {
            throw new Exception("Invalid Type");
        }
        for(Rating rating: ratingList) {
            if(count>0) {
                System.out.println(rating);
                count--;
            } else {
                break;
            }
        }
    }

    void printUserDetails(String userName) throws Exception {
        if(!users.containsKey(userName)) {
            throw new Exception("Invalid User" + userName);
        }
        users.get(userName).toString();
    }


    public static void main(String[] args) throws Exception {
        RestaurantManagement management = new RestaurantManagement();
        management.addRestaurant("restaurant1");
        management.addRestaurant("restaurant2");
        management.addRestaurant("restaurant3");

        management.addUser("user1");
        management.addUser("user2");

        management.addRating("user1", "restaurant1", 5);
        management.addRating("user1", "restaurant2", 8);
        management.addRating("user2", "restaurant1", 7);
        management.addRating("user2", "restaurant3", 8);

        List<String> items = new ArrayList<>();
        items.add("dosa");
        items.add("samosa");
        management.addRating("user1", "restaurant2", 7, items, "Samosa and Tea were good");
        items.clear();
        items.add("dosa");
        management.addRating("user2", "restaurant1", 8, items, "Dosa was awesome");

        management.listRatings("restaurant1", 3);

        HashMap<String,Integer> filter = new HashMap<String, Integer>();
        filter.put("lower_limit",8);
        filter.put("upper_limit",9);
        management.listRatings("restaurant1", 2, "ASC", filter);

        management.getRestaurant("restaurant1");

        management.listRestaurants(3);
    }
}
