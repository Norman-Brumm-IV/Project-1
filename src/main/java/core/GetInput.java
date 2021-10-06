package core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import https.Get;
import https.Post;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import tools.VerboseFlags;

public class GetInput {
	VerboseFlags dBug = new VerboseFlags();

	//this class will get the input from whatever we are using to get input and then send it out from here
	public static void main(String[] args) {
		Javalin app = Javalin.create().start(7000);
		
		setVerboseMode(true);
		VerboseFlags.setCompleted(true);

		app.post("/login", Post::login);
		app.get("/logout", Get::logout);
		app.post("/authorized", Post::isAuthorized);
		app.get("/all", Get::getAll);
		app.get("/needscompletion", Get::needsCompletion);
		app.post("/getNameOfUser", Post::getNameOfUser);
		app.post("/updateTicket", Post::updateTicket);
		app.post("/newTicket", Post::newTicket);
		app.post("/pending", Post::pending);
		app.post("/completed", Post::completed);

		app.get("/stats/average", Get::averageTicketPrice);
		app.get("/stats/max", Get::maxTicketPrice);
		app.get("/stats/min", Get::minTicketPrice);
		app.get("/stats/answered", Get::ticketsAnswered);

		app.after(ctx -> {
			if(VerboseFlags.after())
				System.out.println("---------------after--------------------");
			ctx.res.addHeader("Access-Control-Allow-Origin", "null");
			
			
			if(VerboseFlags.after()) {
				System.out.println("endpoint poked:" + ctx.url());
				System.out.println("Session:" + ctx.req.getSession(false));
				System.out.println("---------------/after-------------------");
			}
		});

		app.config.addStaticFiles("/static", Location.CLASSPATH);
	}


	// believe it or not. this is far shorter than trying to type them all out. This is also dynamic.
	private static void setVerboseMode(boolean boo) {
		Class<VerboseFlags> curClass = VerboseFlags.class;
		Method[] allMethods = curClass.getMethods();
		for(Method method : allMethods) {
		    if(method.getName().startsWith("set")) {
		        try {
					method.invoke(curClass, boo);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
		    }
		}
	}

}
