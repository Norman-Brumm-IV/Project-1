package https;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import dao.DAOget;
import io.javalin.http.Context;
import models.JSONobj;
import models.Users;
import tools.VerboseFlags;

public class Get {
	public static void logout(Context ctx) {
		HttpSession session = ctx.req.getSession(false);
		if(session != null) session.invalidate();
		ctx.redirect("/MarSaraPortal.html");
	}

	public static void getAll(Context ctx) {
		if(VerboseFlags.getAll())
			System.out.println("--------------getAll-Method---------------");
		ctx.json(DAOget.getAllTickets());
		if(VerboseFlags.getAll())
			System.out.println("--------------/getAll-Method--------------");
	}

	public static void needsCompletion(Context ctx) {
		if(VerboseFlags.needsCompletion())
			System.out.println("--------------needsCompletion-Method---------------");

		ctx.json(DAOget.getTicketsWhere("Approved IS NULL"));

		if(VerboseFlags.needsCompletion())
			System.out.println("--------------/needsCompletion-Method--------------");
	}

	public static void averageTicketPrice(Context ctx) {
		if(VerboseFlags.averageTicketPrice())
			System.out.println("------------------averageTicketPrice-Method-------------------");

		JSONobj jobj = new JSONobj();
		jobj.setDoesntMatter((DAOget.getTicketsSingleColumnMathFunction("AVG", "amount", " WHERE approved IS NOT NULL")).toString());
		ArrayList<JSONobj> test = new ArrayList<JSONobj>();
		test.add(jobj);
		ctx.json(test);

		if(VerboseFlags.averageTicketPrice())
			System.out.println("-----------------/averageTicketPrice-Method-------------------");			
	}
	public static void maxTicketPrice(Context ctx) {
		if(VerboseFlags.maxTicketPrice())
			System.out.println("------------------Get-maxTicketPrice-------------------");
		ctx.json(DAOget.getTicketsMathFunction("MAX", "amount", " approved=true"));
		if(VerboseFlags.maxTicketPrice())
			System.out.println("-----------------/Get-maxTicketPrice-------------------");
	}
	public static void minTicketPrice(Context ctx) {
		if(VerboseFlags.minTicketPrice())
			System.out.println("------------------Get-minTicketPrice-------------------");
		ctx.json(DAOget.getTicketsMathFunction("MIN", "amount", " approved=true"));
		if(VerboseFlags.minTicketPrice())
			System.out.println("-----------------/Get-minTicketPrice-------------------");
	}
	public static void ticketsAnswered(Context ctx) {
		if(VerboseFlags.ticketsAnswered())
			System.out.println("------------------Get-ticketsAnswered-------------------");
		List<Users> users = DAOget.getUsersWhere("adminlevel<31");
		int total = 0;
		ArrayList<String> alString = new ArrayList<String>();
		ArrayList<Double> alDouble = new ArrayList<Double>();

		for(Users user : users) {
			int cidNumber = user.getCid();
			int count = (int)(double)DAOget.getTicketsSingleColumnMathFunction("COUNT", "reviewer", " WHERE reviewer=" + cidNumber);
			total += count;
			alString.add(user.getName());
			alDouble.add((double)count);
		}
		
		StringBuilder jsonString = new StringBuilder();
		jsonString.append("[");

		for(int i=0;i<alString.size();i++) {
			double temp = alDouble.get(i);
			double percentage = (int)((temp/total)*100);
			alDouble.set(i, percentage);
			
			jsonString.append("{\"name\":\"" + alString.get(i) +
					"\",\"percentage\":\"" + (int)(double)alDouble.get(i) + "%\"},"
					);
		}
		jsonString.setCharAt(jsonString.length()-1,']');
		final String finalString = jsonString.toString();

		if(VerboseFlags.ticketsAnswered()) {
			System.out.println("endpoint poked:" + ctx.url());
			System.out.println("finalString (jsonified):" + finalString);
		}
		
		ctx.result(finalString);

		if(VerboseFlags.ticketsAnswered())
			System.out.println("-----------------/Get-ticketsAnswered-------------------");
	}
}
