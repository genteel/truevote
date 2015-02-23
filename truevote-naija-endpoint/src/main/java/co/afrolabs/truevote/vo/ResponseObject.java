package co.afrolabs.truevote.vo;

import co.afrolabs.truevote.constant.ServerConstants;

public class ResponseObject {
	private String responseCode=ServerConstants.RESPONSE_RESQUEST_ERROR;;
	private String message;
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
