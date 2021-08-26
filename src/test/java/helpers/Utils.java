package helpers;
public class Utils {


    public int generateRandomNumber() {
        int num = (int)((Math.random() * 9000000)+1000000); //generates a 7 digit number
        return num;
    }

}
