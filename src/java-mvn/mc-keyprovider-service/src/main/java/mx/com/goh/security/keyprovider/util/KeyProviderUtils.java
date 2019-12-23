package mx.com.goh.security.keyprovider.util;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 * Utility class for the help in tasks of this project. <br>
 * Version control:<br>
 * - 1.0.0 | 10/06/2019 | Gustavo Olivares Hernández
 * 
 * @author golivaresh
 * @version 1.0.0
 */
public final class KeyProviderUtils {
    /**
     * Logger for this class.
     */
    public static final Logger LOGGER = Logger.getLogger(KeyProviderUtils.class);

    /**
     * Default charset name for base64 encode/decode.
     */
    public static final String ENCODE_CHARSET_NAME = "utf-8";

    /**
     * Path for the root url.
     */
    public static final String REQUEST_PATH_ROOT_KPCONTROLLER = "/";

    /**
     * Path for get public key.
     */
    public static final String REQUEST_PATH_PUBLIC_KEY = "/public/publickey";

    /**
     * Path for get private key.
     */
    public static final String REQUEST_PATH_PRIVATE_KEY = "/private/privatekey";

    /**
     * Path for get secret key.
     */
    public static final String REQUEST_PATH_SECRET_KEY = "/private/secretKey";

    /**
     * Array for the configuration of authorize requests.
     */
    public static final String[] GET_AUTHORIZE_REQUESTS = 
            new String[] { REQUEST_PATH_PRIVATE_KEY,
                REQUEST_PATH_SECRET_KEY, REQUEST_PATH_PUBLIC_KEY };

    /**
     * Constructor of KeyProviderUtils class.
     */
    private KeyProviderUtils() {
        super();
    }

    /**
     * Encode the {@link String} parameter in {@link Base64}.
     * 
     * @param str
     *            String for encode.
     * @return encode String in Base64.<br>
     *         In case the string is null, empty or a error occurred, it returns
     *         empty value.
     */
    public static final String encodeBase64String(String str) {
        try {
            return Base64.getEncoder().encodeToString(StringUtils.defaultIfEmpty(str, 
                    StringUtils.EMPTY).getBytes(ENCODE_CHARSET_NAME));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return StringUtils.EMPTY;
    }

    /**
     * Decode the {@link String} parameter previously encoded in {@link Base64}.
     * 
     * @param str
     *            String for decode.
     * @return decode String.<br>
     *         In case the string is null, empty or a error occurred, it returns
     *         empty value.
     */
    public static final String decodeBase64String(String str) {
        try {
            byte[] decode = Base64.getDecoder().decode(
                    StringUtils.defaultIfEmpty(str, StringUtils.EMPTY).getBytes());
            return new String(decode, ENCODE_CHARSET_NAME);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return StringUtils.EMPTY;
    }

}
