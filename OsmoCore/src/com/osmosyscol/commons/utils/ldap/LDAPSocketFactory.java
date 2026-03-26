package com.osmosyscol.commons.utils.ldap;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.net.SocketFactory;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class LDAPSocketFactory extends SocketFactory {
	
	private final SSLSocketFactory factory;

	public LDAPSocketFactory(SSLSocketFactory factory) {
		this.factory = factory;
	}

	@Override
	public Socket createSocket(String host, int port) throws IOException,
			UnknownHostException {
		return prepareSocket(factory.createSocket(host, port));
	}

	@Override
	public Socket createSocket(InetAddress host, int port) throws IOException {
		return prepareSocket(factory.createSocket(host, port));
	}

	@Override
	public Socket createSocket(String host, int port, InetAddress localHost,
			int localPort) throws IOException, UnknownHostException {
		return prepareSocket(factory.createSocket(host, port, localHost, localPort));
	}

	@Override
	public Socket createSocket(InetAddress address, int port,
			InetAddress localAddress, int localPort) throws IOException {
		return prepareSocket(factory.createSocket(address, port, localAddress, localPort));
	}
	
	private Socket prepareSocket(Socket socket){
		SSLSocket sslSocket = (SSLSocket) socket;
		SSLParameters params = sslSocket.getSSLParameters();
		params.setEndpointIdentificationAlgorithm("LDAPS");
		sslSocket.setSSLParameters(params);
		return sslSocket;
	}
	
}
