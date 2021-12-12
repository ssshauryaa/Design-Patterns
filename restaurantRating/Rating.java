package restaurantRating;

import java.util.ArrayList;
import java.util.List;

public class Rating implements Comparable<Rating> {
    int rating;
    String description;
    List<String> items;

    public Rating(int rating) {
        this.rating = rating;
        this.description = "";
        this.items = new ArrayList<String>();
    }

    public Rating(int rating, String description, List<String> items) {
        this.rating = rating;
        this.description = description;
        this.items = items;
    }

    public int getRating() {
        return rating;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getItems() {
        return items;
    }


    @Override
    public String toString() {
        return "Rating{" +
                "rating=" + rating +
                ", description='" + description + '\'' +
                ", items=" + items +
                '}';
    }

    @Override
    public int compareTo(Rating that) {
        return that.rating-this.rating;
    }
}
