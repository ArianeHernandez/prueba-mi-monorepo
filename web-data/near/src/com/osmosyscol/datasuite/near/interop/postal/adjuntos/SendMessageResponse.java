package com.osmosyscol.datasuite.near.interop.postal.adjuntos;

public class SendMessageResponse {

	 ResponseMetadata ResponseMetadataObject;
	 SendMessageResult SendMessageResultObject;


	 // Getter Methods 

	 public ResponseMetadata getResponseMetadata() {
	  return ResponseMetadataObject;
	 }

	 public SendMessageResult getSendMessageResult() {
	  return SendMessageResultObject;
	 }

	 // Setter Methods 

	 public void setResponseMetadata(ResponseMetadata ResponseMetadataObject) {
	  this.ResponseMetadataObject = ResponseMetadataObject;
	 }

	 public void setSendMessageResult(SendMessageResult SendMessageResultObject) {
	  this.SendMessageResultObject = SendMessageResultObject;
	 }
	
}
