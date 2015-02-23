package co.afrolabs.truevote.constant;

public class ServerConstants {

	public static final String WEB_CLIENT_ID = "593053426366-vbivt8qsdsa00kduutcf14rrn0vljhc8.apps.googleusercontent.com";
	  public static final String ANDROID_CLIENT_ID = "593053426366-ic2atj7gjokj98h0cstojq757n2bo2gj.apps.googleusercontent.com";
	  public static final String IOS_CLIENT_ID = "replace this with your iOS client ID";
	  public static final String ANDROID_AUDIENCE = WEB_CLIENT_ID;
	  
	  public static final String REQUEST_SOURCE_WEB = "WEB_CLIENT";
	  public static final String REQUEST_SOURCE_ANDROID = "ANDROID_CLIENT";
	 
	  public static final String EMAIL_SCOPE = "https://www.googleapis.com/auth/userinfo.email";
	  public static final String API_EXPLORER_CLIENT_ID = com.google.api.server.spi.Constant.API_EXPLORER_CLIENT_ID;
	  
	  /**
		 * gidi Server response codes
		 */
		public static final String RESPONSE_SUCCESS = "200";
		public static final String RESPONSE_LOGIN_DETAIL = "201";
		public static final String RESPONSE_FAILED = "000";
		public static final String RESPONSE_LOGIN_FAILED = "202";
		public static final String RESPONSE_MISSING = "203";
		public static final String RESPONSE_NOTREGISTERED = "204";
		public static final String RESPONSE_RESQUEST_ERROR = "404";
		public static final String RESPONSE_ACCESS_DENIED = "405";
		
	  public static final String REQUEST_VOTE_STATISTICS = "STATISTICS";
	  public static final String REQUEST_VOTE = "VOTE";
	  public static final String REQUEST_LOGIN = "LOGIN";
	  public static final String REQUEST_GET_CANDIDATES = "GET_CANDIDATES";
	  public static final String REQUEST_GET_USER_CHOICE = "GET_USER_CHOICE";
	  public static final String REQUEST_IREPORT = "IREPORT";
	  public static final String REQUEST_GET_MANIFESTO = "GET_MANIFESTO";
	  public static final String REQUEST_CREATE_MANIFESTO = "CREATE_MANIFESTO";

}
