package mx.com.goh.security.keyprovider.response;

import mx.com.goh.gcommons.response.type.ErrorType;

/**
 * Class for the define customized responses for the user.<br>
 * Version control:<br>
 * - 1.0.0 | 17/06/2019 | Gustavo Olivares Hernández
 * 
 * @author golivaresh
 * @version 1.0.0
 */
public final class KeyProviderResponse {
   
    /**
     * Error response for the user.
     * @author golivaresh
     * @version 1.0.0
     */
    public enum Error implements ErrorType {
        PARAMETERS_NOTFOUND("ERRPARAM", "The parameters issuer and service-name are required!"),
        CLIENT_REQUIRED("CLNT_REQUIRED", "The client request is required!"),
        CLIENT_NOT_FOUND("CLNT_NOT_FOUND", "The client was not found with the issuer sent!"),
        SERVICE_NOT_FOUND("SERVICE_NOT_FOUND", "The requested service has not been found!"),
        ;

        private String code;
        private String message;

        /**
         * Constructor of ProviderError class.
         * 
         * @param code
         *            of the response.
         * @param message
         *            of the response.
         */
        private Error(String code, String message) {
            this.code = code;
            this.message = message;
        }

        /*
         * (non-Javadoc)
         * 
         * @see mx.com.goh.gcommons.response.type.IEnumResponse#getCode()
         */
        @Override
        public String getCode() {
            return this.code;
        }

        /*
         * (non-Javadoc)
         * 
         * @see mx.com.goh.gcommons.response.type.IEnumResponse#getMessage()
         */
        @Override
        public String getMessage() {
            return this.message;
        }

    }
}
