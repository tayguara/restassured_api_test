package tdreis.api.test.util;

import com.jayway.restassured.response.Response;
import tdreis.api.test.supporttests.Common;
import org.apache.commons.lang3.RandomStringUtils;
import org.json.simple.JSONValue;
import org.junit.Assert;

import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.stream.Collectors;

public class Util {

    /**
     * Return obj of according a array send {key => value}
     *
     * @param arrayVal array
     * @return Map obj
     */
    public static Map getArrayObj(String[][] arrayVal) {
        int initArray;
        int array = arrayVal.length;
        Map obj = new LinkedHashMap();

        for (initArray = 0; initArray < array; initArray++) {
            obj.put(arrayVal[initArray][0], arrayVal[initArray][1]);
        }

        return obj;
    }

    /**
     * Return obj of according a string[] send {key => value}
     *
     * @param arrayVal array
     * @return Map obj
     */
    public static Map getStringToObj(String[] arrayVal) {
        int initArray;
        int array = arrayVal.length;
        Map obj = new LinkedHashMap();

        for (initArray = 0; initArray < array; initArray++) {
            obj.put(arrayVal[0], arrayVal[1]);
        }

        return obj;
    }

    /**
     * Return json of according a obj send
     *
     * @param obj Map
     * @return String jsonText
     * @throws IOException jsonText
     */
    public static String getJson(Map obj) throws IOException {
        StringWriter out = new StringWriter();
        JSONValue.writeJSONString(obj, out);

        return out.toString();
    }

    /**
     * Check array contain a string
     *
     * @param texts    array
     * @param response string
     */
    public static void inArray(Object[] texts, String response) {
        String error = null;

        for (Object text : texts) {
            try {
                Assert.assertEquals(text, response);
                return;
            } catch (Throwable e) {
                error = e.getMessage();
            }
        }

        org.testng.Assert.fail(error);
    }

    /**
     * Return local host ip
     *
     * @return String hostAddress
     * @throws UnknownHostException hostAddress
     */
    public static String getIp() throws UnknownHostException {
        InetAddress ip = InetAddress.getLocalHost();

        return ip.getHostAddress();
    }

    /**
     * Return random string
     *
     * @param length     int
     * @param useLetters bool
     * @param useNumbers bool
     * @return String generatedString
     */
    public static String getString(int length, boolean useLetters, boolean useNumbers) {

        return RandomStringUtils.random(length, useLetters, useNumbers);
    }

    /**
     * Generate md5 a string
     *
     * @param word string
     * @return String md5
     * @throws NoSuchAlgorithmException hexString
     * @throws UnsupportedEncodingException hexString
     */
    public static String getMd5(String word) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String hex;

        MessageDigest md = MessageDigest.getInstance("MD5");

        md.update(word.getBytes());

        byte byteData[] = md.digest();

        //convert the byte to hex format method 1
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        //convert the byte to hex format method 2
        StringBuilder hexString = new StringBuilder();

        for (int i = 0; i < byteData.length; i++) {
            hex = Integer.toHexString(0xff & byteData[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();
    }

    /**
     * Remove indexOf a array
     *
     * @param array array
     * @param index int
     * @return String[] unique
     */
    public static String[] removeKeyArray(String[] array, int[] index) {
        for (int ind : index) {
            System.arraycopy(array, ind + 1, array, ind, array.length - 1 - ind);
        }

        return new HashSet<String>(Arrays.asList(array)).toArray(new String[0]);
    }

    /**
     * Response header to String array
     *
     * @param request Response
     * @return String header
     */
    public static String[] headerToArrayString(Response request) {
        String head = request.headers().toString();

        return head.split("\n");
    }

    /**
     * Check is Null
     *
     * @param request     Response
     * @param responseKey StringArray
     * @return boolean
     */
    public static boolean getRequestArrayResponse(Response request, String[][] responseKey) {
        for (String[] response : responseKey) {
            Assert.assertNotNull(request.then().extract().path(response[0]));
        }

        return true;
    }

    /**
     * Array to List
     *
     * @param array
     * @param <T>
     * @return
     */
    public static <T> List<T> toList(T[] array) {
        return Arrays.stream(array).collect(Collectors.toList());
    }

    /**
     * Check is Int
     *
     * @param val
     * @return
     */
    public static Map isInt(String val) {
        Map obj = new LinkedHashMap();

        try {
            Integer.parseInt(val);
            obj.put(Common.keyBoolean, true);
            obj.put(Common.keyException, Common.messageException);
        } catch (NumberFormatException exc) {
            obj.put(Common.keyBoolean, false);
            obj.put(Common.keyException, exc.toString());
        }

        return obj;
    }
}
