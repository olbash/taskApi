package helpers;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class Utils {


    public int generateRandomNumber() {
        int num = (int)((Math.random() * 9000000)+1000000); //generates a 7 digit number
        return num;
    }

}
