package restaurantRating;

import java.util.HashMap;
import java.util.Objects;

public class Restaurant implements Comparable<Restaurant> {
    String name;
    int totalRating;
    int numberOfRatings;
    float avgRating;
    HashMap<User,Rating> ratings = new HashMap<User, Rating>();

    public HashMap<User, Rating> getRatings() {
        return ratings;
    }

    public Restaurant(String name) {
        this.name = name;
        this.totalRating = 0;
        this.numberOfRatings = 0;
        this.avgRating = (float) 0.0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Restaurant)) return false;
        Restaurant that = (Restaurant) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    void addRating(User user, Rating rating) {
        ratings.put(user,rating);
        totalRating+=rating.getRating();
        numberOfRatings++;
        avgRating = (float)totalRating/numberOfRatings;
        user.incrementRating();
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", avgRating=" + avgRating +
                '}';
    }

    @Override
    public int compareTo(Restaurant that) {
        if(this.avgRating>that.avgRating) {
            return 0;
        } else {
            return 1;
        }
    }
}
