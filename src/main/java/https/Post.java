package https;

import org.json.JSONObject;

import dao.DAOget;
import dao.DAOupdate;
import io.javalin.http.*;
import models.Tickets;
import models.Users;
import tools.CheckWrongData;
import tools.VerboseFlags;

public class Post {
	public static void login(Context ctx) {
		if(VerboseFlags.login()) {
			System.out.println("-------------Login-class----------------");
			System.out.println("endpoint poked:" + ctx.url());
			System.out.println("ctx.body(): " + ctx.body());
		}

		JSONObject jobj = new JSONObject(ctx.body());
		String uName = jobj.getString("username");
		String pWord = jobj.getString("password");

		if(!CheckWrongData.canParseAsDouble(uName)) {
			if(VerboseFlags.login())
				System.out.println("uName was not parsable as a double: " + uName);
			ctx.status(403);
			return;
		} else {
			Users user = DAOget.getUser(uName);

			if(CheckWrongData.checkUsersPassword(user, pWord))
				ctx.status(200);
			else
				ctx.status(403);
		}
		if(VerboseFlags.login())
			System.out.println("-------------/Login-class---------------");
	}

	public static void isAuthorized(Context ctx) {
		if(VerboseFlags.authorized()) {
			System.out.println("--------------isAuthorized-Method---------------");
			System.out.println("endpoint poked:" + ctx.url());
			System.out.println("cyx.body():" + ctx.body());
		}
		String body = ctx.body();
		if(body.indexOf("?")<0) {
			ctx.status(403);
			if(VerboseFlags.authorized())
				System.out.println("endpoint poked:" + ctx.url());
				System.out.println("no ? in the URL passed in");
				System.out.println("--------------/isAuthorized-Method--------------");
			return;
		}

		String creds = body.substring(body.indexOf("?"));
		if(creds.indexOf("&")<0) {
			ctx.status(403);
			if(VerboseFlags.authorized()) {
				System.out.println("endpoint poked:" + ctx.url());
				System.out.println("no & in the URL passed in");
				System.out.println("--------------/isAuthorized-Method--------------");
			}
			return;
		}


		String[] credArray = creds.split("&");
		String uName = credArray[0].substring(credArray[0].indexOf("=") + 1);
		String pWord = credArray[1].substring(credArray[1].indexOf("=") + 1);
		if(VerboseFlags.authorized()) {
			System.out.println("endpoint poked:" + ctx.url());
			System.out.println("username: " + uName);
			System.out.println("password: " + pWord);
		}

		Users user = DAOget.getUser(uName);
		if(CheckWrongData.checkUsersPassword(user, pWord)) {
			ctx.status(200);
			ctx.req.getSession();
			ctx.json(user.getAdminlevel());
		} else {
			ctx.status(403);
		}

		if(VerboseFlags.authorized())
			System.out.println("--------------/isAuthorized-Method--------------");
	}

	public static void getNameOfUser(Context ctx) {
		if(VerboseFlags.uName()) {
			System.out.println("--------------getNameOfUser-Method--------------");
		System.out.println("endpoint poked:" + ctx.url());
		System.out.println("cyx.body():" + ctx.body());
		}
		String body = ctx.body();

		String creds = body.substring(body.indexOf("?"));
		String[] credArray = creds.split("&");
		String uName = credArray[0].substring(credArray[0].indexOf("=") + 1);
		String pWord = credArray[1].substring(credArray[1].indexOf("=") + 1);
		if(VerboseFlags.uName()) {
			System.out.println("endpoint poked:" + ctx.url());
			System.out.println("username: " + uName);
			System.out.println("password: " + pWord);
		}

		Users user = DAOget.getUser(uName);
		ctx.result(user.getName());

		if(VerboseFlags.uName())
			System.out.println("--------------/getNameOfUser-Method-------------");
	}

	public static void updateTicket(Context ctx) {
		if(VerboseFlags.updateTicket()) {
			System.out.println("-------------updateTicket-Method----------------");
			System.out.println("endpoint poked:" + ctx.url());
			System.out.println("ctx.body(): " + ctx.body());
		}

		JSONObject jobj = new JSONObject(ctx.body());
		String rID = jobj.getString("id");
		if(!CheckWrongData.canParseAsDouble(rID)) {
			if(VerboseFlags.updateTicket()) {
				System.out.println("endpoint poked:" + ctx.url());
				System.out.println("ticket number ended up not being a number. This should only happen if someone is mucking around where/how they shouldnt.");
				System.out.println("-------------/updateTicket-Method---------------");
			}
			ctx.status(403);
			return;
		}
			
		String appDen = jobj.getString("approved");
		String reason = jobj.getString("reason");
		String approver = getNameFromURL(jobj.getString("approver"));
		
		
		if(approver==null || reason==null || approver==null) {
			if(VerboseFlags.updateTicket()) {
				System.out.println("endpoint poked:" + ctx.url());
				System.out.println("one of the Strings ended up being null. This should only happen if someone is mucking around where/how they shouldnt.");
				System.out.println("-------------/updateTicket-Method---------------");
			}
			ctx.status(403);
			return;
		}
		if(!CheckWrongData.canParseAsDouble(approver) || !CheckWrongData.canParseAsBoolean(appDen)) {
			if(VerboseFlags.updateTicket()) {
				System.out.println("endpoint poked:" + ctx.url());
				System.out.println("one of the Strings ended up being the wrong data type. This should only happen if someone is mucking around where/how they shouldnt.");
				System.out.println("-------------/updateTicket-Method---------------");
		}
			ctx.status(403);
			return;
		}

		Tickets ticket = DAOget.getTicket(Integer.parseInt(rID));
		
		if(ticket.getApproved()!=null) {
			if(VerboseFlags.updateTicket()) {
				System.out.println("endpoint poked:" + ctx.url());
				System.out.println("the ticket has already been approved. This should only happen if someone is mucking around where/how they shouldnt.");
				System.out.println("-------------/updateTicket-Method---------------");
		}
			ctx.status(403);
			return;
		}
		
		ticket.setApproved(Boolean.parseBoolean(appDen));
		ticket.setReviewer(Integer.parseInt(approver));
		ticket.setAdReason(reason);
		
		
		
		DAOupdate.saveOrUpdateTicket(ticket);
		
		
		if(VerboseFlags.updateTicket())
			System.out.println("-------------/updateTicket-Method---------------");
	}
	
	public static void newTicket(Context ctx) {
		if(VerboseFlags.nTicket()) { 
			System.out.println("-------------newTicket-Method----------------");
			System.out.println("endpoint poked:" + ctx.url());
			System.out.println("ctx.body(): " + ctx.body());
		}
		JSONObject jobj = new JSONObject(ctx.body());
		
		String amount = jobj.getString("amount");
		String reason = jobj.getString("reason");
		String requestor = getNameFromURL(jobj.getString("requestor"));
		if(VerboseFlags.nTicket()) {
			System.out.println("---------------");
			System.out.println("endpoint poked:" + ctx.url());
			System.out.println("amount:" + amount);
			System.out.println("reason:" + reason);
			System.out.println("requestor:" + requestor);
			System.out.println("---------------");
		}
		
		if(!CheckWrongData.canParseAsDouble(amount)) {
			if(VerboseFlags.nTicket()) {
				System.out.println("endpoint poked:" + ctx.url());
				System.out.println("the amount was not a number");
				System.out.println("------------/newTicket-Method----------------");
			}
			ctx.result("the amount was not a number");
			ctx.status(400);
			return;
		}
		
		if(requestor==null) {
			if(VerboseFlags.nTicket()) {
				System.out.println("endpoint poked:" + ctx.url());
				System.out.println("the requestor was not a number. This should only happen if someone is mucking around where/how they shouldnt.");
				System.out.println("------------/newTicket-Method----------------");
			}
			ctx.status(403);
			return;
		}
		
		Tickets ticket = new Tickets(Integer.parseInt(requestor), reason, Double.parseDouble(amount));
		DAOupdate.saveOrUpdateTicket(ticket);
		ctx.status(201);
		
		
		if(VerboseFlags.nTicket())
			System.out.println("------------/newTicket-Method----------------");
	}
	
	public static void pending(Context ctx) {
		if(VerboseFlags.pending()) {
			System.out.println("-------------pending-Method----------------");
			System.out.println("endpoint poked:" + ctx.url());
			System.out.println("ctx.body(): " + ctx.body());
		}
		
		String uName = getNameFromURL(ctx.body());
		if(uName==null || !CheckWrongData.canParseAsDouble(uName)) {
			if(VerboseFlags.pending()) {
				System.out.println("endpoint poked:" + ctx.url());
				System.out.println("ctx.body():" + ctx.body());
				System.out.println("uName:" + uName);
				System.out.println("the requestor was not a number, or was empty. This should only happen if someone is mucking around where/how they shouldnt.");
				System.out.println("------------/pending-Method----------------");
			}
			ctx.status(403);
			return;
		}
		
		ctx.json(DAOget.getTicketsWhere("Approved IS NULL AND CID=" + uName));
		
		

		
		if(VerboseFlags.pending())
			System.out.println("------------/pending-Method----------------");
	}
	
	public static void completed(Context ctx) {
		if(VerboseFlags.completed()) {
			System.out.println("-------------Post-Completed----------------");
			System.out.println("endpoint poked:" + ctx.url());
			System.out.println("ctx.body(): " + ctx.body());
		}
		
		String uName = getNameFromURL(ctx.body());
		if(uName==null || !CheckWrongData.canParseAsDouble(uName)) {
			if(VerboseFlags.pending()) {
				System.out.println("endpoint poked:" + ctx.url());
				System.out.println("ctx.body():" + ctx.body());
				System.out.println("uName:" + uName);
				System.out.println("the requestor was not a number, or was empty. This should only happen if someone is mucking around where/how they shouldnt.");
				System.out.println("------------/Post-Completed----------------");
			}
			ctx.status(403);
			return;
		}
		
		ctx.json(DAOget.getTicketsWhere("Approved IS NOT NULL AND CID=" + uName));
		
		

		
		if(VerboseFlags.pending())
			System.out.println("------------/Post-Completed----------------");
	}

	/**
	 * Gets the user name from a string passed in. It also checks that the user exists and the password that is stored in the same
	 * location as the username is the correct password
	 * @param urlString - URL string, should have ? and two & symbols
	 * @return The username as a String, or NULL if any issues were found, or the credentials didnt match a user in the database. 
	 */
	private static String getNameFromURL(String urlString) {
		if(VerboseFlags.getName())
			System.out.println("--------------getName-Method---------------");
		if(urlString.indexOf("?")<0) {
			if(VerboseFlags.getName()) {
				System.out.println("no ? in the URL passed in");
				System.out.println("--------------/getName-Method--------------");
			}
			return null;
		}

		String creds = urlString.substring(urlString.indexOf("?"));
		if(creds.indexOf("&")<0) {
			if(VerboseFlags.getName()) {
				System.out.println("no & in the URL passed in");
				System.out.println("--------------/getName-Method--------------");
			}
			return null;
		}

		String[] credArray = creds.split("&");
		String uName = credArray[0].substring(credArray[0].indexOf("=") + 1);
		String pWord = credArray[1].substring(credArray[1].indexOf("=") + 1);
		if(VerboseFlags.getName()) {
			System.out.println("username: " + uName);
			System.out.println("password: " + pWord);
		}
		
		Users user = DAOget.getUser(uName);
		if(!CheckWrongData.checkUsersPassword(user, pWord)) {
			if(VerboseFlags.getName()) {
				System.out.println("no user found in the database with username (" + uName + ") and password (" + pWord + ")");
				System.out.println("--------------/getName-Method--------------");
			}
			return null;
		}
		
		return user.getCid() + "";
	}
	
}
