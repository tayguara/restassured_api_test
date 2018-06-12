package tdreis.api.test.common;

import java.io.IOException;
import java.util.Properties;

public abstract class Property {

    public static final String ENVIRONMENT_TEST;
    public static final String SITE_ADDRESS_GITHUB;
    public static final String SITE_ADDRESS_SANDBOX;

    private static final String PROPERTIES_FILE = "api/test/conf/config.properties";

    static {
        ENVIRONMENT_TEST = get("environment.test");
        SITE_ADDRESS_GITHUB = get("site.address.github");
        SITE_ADDRESS_SANDBOX = get("site.address.sandbox");
    }

    /**
     * Define environment and URL to test
     *
     * @param name value to be verify
     * @return value
     */
    private static String get(String name) {
        Properties properties = new Properties();
        String value = null;

        try {
            properties.load(Property.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE));
            value = properties.getProperty(name);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return value;
    }
}
