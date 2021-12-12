package restaurantRating;

import java.util.Objects;

public class User {
    String userName;
    int totalRating;
    int foodinessLevel;
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getUserName().equals(user.getUserName());
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", totalRating=" + totalRating +
                ", foodinessLevel=" + foodinessLevel +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserName());
    }

    public User(String userName) {
        this.userName = userName;
        this.foodinessLevel = 1;
        this.totalRating = 0;
    }

    void incrementRating() {
        totalRating++;
        if(totalRating>4) {
            foodinessLevel = 2;
        }
        if(totalRating>8) {
            foodinessLevel = 3;
        }
        if(totalRating>16) {
            foodinessLevel = 4;
        }
    }
}
