package com.osmosyscol.datasuite.near.interop.postal.adjuntos;

public class SendMessageResult {

	 private String MD5OfMessageBody;
	 private String MessageId;


	 // Getter Methods 

	 public String getMD5OfMessageBody() {
	  return MD5OfMessageBody;
	 }

	 public String getMessageId() {
	  return MessageId;
	 }

	 // Setter Methods 

	 public void setMD5OfMessageBody(String MD5OfMessageBody) {
	  this.MD5OfMessageBody = MD5OfMessageBody;
	 }

	 public void setMessageId(String MessageId) {
	  this.MessageId = MessageId;
	 }
	
}
