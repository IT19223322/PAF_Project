package com;
import model.customerPayment;

//for REST service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//for JSON
import com.google.gson.*;
//for XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 


	
	@Path("/customer_payment") 
	public class customerPaymentServlet {
		
	customerPayment paymentObj = new customerPayment();
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readCusPayment() 
	 {     //test
	 return paymentObj.viewCusPayment(); 
	 } 

	//insert
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertItem(@FormParam("cardno") String cardNo, 
	 @FormParam("nameonCard") String nameonCard, 
	 @FormParam("expDate") String expDate, 
	 @FormParam("cvv") String cvv) 
	
	{ 
	 String output = paymentObj.insertPaymentd(cardNo, nameonCard, expDate, cvv); 
	return output; 
	}
	
	//update
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateCustPayment(String cuspaymentInfo) 
	{ 

	 JsonObject paymentObject = new JsonParser().parse(cuspaymentInfo).getAsJsonObject(); 
	 
	 String cardNo = paymentObject.get("cardNo").getAsString(); 
	 String nameonCard = paymentObject.get("nameonCard").getAsString(); 
	 String expDate = paymentObject.get("expDate").getAsString(); 
	 String cvv = paymentObject.get("cvv").getAsString(); 
	 
	 String output = paymentObj.updateCusPayment(cardNo, nameonCard, expDate, cvv); 
	return output;
	}

	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteCusDetails(String cuspaymentinfo) 
	{ 

	 Document doc = Jsoup.parse(cuspaymentinfo, "", Parser.xmlParser()); 
	 
	 String Card_Id = doc.select("Card_Id").text(); 
	 String output = paymentObj.deleteCusPayment(Card_Id); 
	return output; 
	}

}
