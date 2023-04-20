package xyz.carjoy.test;

public class User {
    private Integer userId;
    private String userName;
    public User(Integer userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        User user2 = (User)o;
        return this.userName.equals(user2.userName) && this.userId.equals(user2.userId);
    }

    @Override
    public int hashCode(){
        return userId.hashCode()+userName.hashCode();
    }
}
