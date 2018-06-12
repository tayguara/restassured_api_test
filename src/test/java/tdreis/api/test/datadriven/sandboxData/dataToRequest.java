package tdreis.api.test.datadriven.sandboxData;

import tdreis.api.test.supporttests.sandboxsupport.keysResponseSandbox;
import tdreis.api.test.util.Util;

import java.util.Random;

class dataToRequest {

    private static String randomUser = Util.getString(7, true, true) + "_user";
    private static int randomAge = new Random().nextInt(100);
    private static boolean randomActive = java.util.concurrent.ThreadLocalRandom.current().nextBoolean();

    static String[][] body = {{keysResponseSandbox.keysResponseUsername, randomUser},
            {keysResponseSandbox.keysResponseAge, String.valueOf(randomAge)},
            {keysResponseSandbox.keysResponseActive, String.valueOf(randomActive)}};

    static String[][] invalidBody = {{keysResponseSandbox.keysResponseInvalidUsername, randomUser},
            {keysResponseSandbox.keysResponseInvalidAge, String.valueOf(randomAge)},
            {keysResponseSandbox.keysResponseInvalidActive, String.valueOf(randomActive)}};
}
