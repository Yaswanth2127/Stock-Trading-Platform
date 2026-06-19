package Stock_Trading_Platform.src.model;


public class User {
    private  int userId;
    private String UserName;
    private double walletBalance;
    public  User(){

    }
    public User(String userName, double walletBalance) {
        UserName = userName;
        this.walletBalance = walletBalance;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public double getWalletBalance() {
        return walletBalance;
    }

    public void setWalletBalance(double walletBalance) {
        this.walletBalance = walletBalance;
    }
}
