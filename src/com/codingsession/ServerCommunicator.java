package com.codingsession;

import java.awt.PageAttributes.MediaType;
import java.io.StringReader;
import java.io.StringWriter;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.JerseyClient;
import org.glassfish.jersey.client.JerseyClientBuilder;

public class ServerCommunicator {

	public static void getOwnField(){
		//http://192.168.118.215:8080/coding/getfield?index=1
		
		Client client = ClientBuilder.newClient(new ClientConfig());
	 
		String entity = client.target("http://192.168.118.215:8080/coding/")
	            .path("getfield")
	            .queryParam("index", "1")
	            .request()
	            .header("some-header", "true")
	            .get(String.class);
		
		System.out.println(entity);
		
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Spielfeld.class);
			
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Spielfeld feld = (Spielfeld) jaxbUnmarshaller.unmarshal(new StringReader(entity));
			
			System.out.println("");
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void getOpponentField(){
		//http://192.168.118.215:8080/coding/getfield?index=2
	}
	
}
