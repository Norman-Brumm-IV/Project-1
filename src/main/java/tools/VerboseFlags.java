package tools;

public class VerboseFlags {
	private static boolean after = false;
	// Get Methods
	private static boolean getAll = false;
	private static boolean needsCompletion = false;
	private static boolean averageTicketPrice = false;
	private static boolean maxTicketPrice = false;
	private static boolean maxUser = false;
	private static boolean minTicketPrice = false;
	private static boolean ticketsAnswered = false;
	// Post Methods
	private static boolean authorized = false;
	private static boolean uName = false;
	private static boolean updateTicket = false;
	private static boolean nTicket = false;
	private static boolean pending = false;
	private static boolean completed = false;
	private static boolean getName = false;
	private static boolean login = false;
	// DAOget Methods
	private static boolean getUser = false;
	private static boolean getUsers = false;
	private static boolean getTicket = false;
	private static boolean getTicketsWhere = false;
	private static boolean getAllTickets = false;
	private static boolean getTicketsMathFunction = false;
	// CheckWrongData Methods
	private static boolean checkUsersPassword = false;
	private static boolean getTicketsSingleColumnMathFunction = false;
	// HibernateSessionFactory Methods
	private static boolean getSession = false;


	public static boolean updateTicket() {
		return updateTicket;
	}

	public static void setUpdateTicket(boolean updateTicket) {
		VerboseFlags.updateTicket = updateTicket;
	}

	public static boolean after() {
		return after;
	}

	public static void setAfter(boolean after) {
		VerboseFlags.after = after;
	}

	public static boolean authorized() {
		return authorized;
	}

	public static void setAuthorized(boolean authorized) {
		VerboseFlags.authorized = authorized;
	}

	public static boolean uName() {
		return uName;
	}

	public static void setuName(boolean uName) {
		VerboseFlags.uName = uName;
	}

	public static boolean nTicket() {
		return nTicket;
	}

	public static void setnTicket(boolean nTicket) {
		VerboseFlags.nTicket = nTicket;
	}

	public static boolean pending() {
		return pending;
	}

	public static void setPending(boolean pending) {
		VerboseFlags.pending = pending;
	}

	public static boolean getName() {
		return getName;
	}

	public static void setGetName(boolean getName) {
		VerboseFlags.getName = getName;
	}

	public static boolean getAll() {
		return getAll;
	}

	public static void setGetAll(boolean getAll) {
		VerboseFlags.getAll = getAll;
	}

	public static boolean login() {
		return login;
	}

	public static void setLogin(boolean login) {
		VerboseFlags.login = login;
	}

	public static boolean needsCompletion() {
		return needsCompletion;
	}

	public static void setNeedsCompletion(boolean needsCompletion) {
		VerboseFlags.needsCompletion = needsCompletion;
	}

	public static boolean averageTicketPrice() {
		return averageTicketPrice;
	}

	public static void setAverageTicketPrice(boolean averageTicketPrice) {
		VerboseFlags.averageTicketPrice = averageTicketPrice;
	}

	public static boolean maxTicketPrice() {
		return maxTicketPrice;
	}

	public static void setMaxTicketPrice(boolean maxTicketPrice) {
		VerboseFlags.maxTicketPrice = maxTicketPrice;
	}

	public static boolean getUser() {
		return getUser;
	}

	public static void setGetUser(boolean getUser) {
		VerboseFlags.getUser = getUser;
	}

	public static boolean getTicket() {
		return getTicket;
	}

	public static void setGetTicket(boolean getTicket) {
		VerboseFlags.getTicket = getTicket;
	}

	public static boolean getTicketsWhere() {
		return getTicketsWhere;
	}

	public static void setGetTicketWhere(boolean getTicketWhere) {
		VerboseFlags.getTicketsWhere = getTicketWhere;
	}

	public static boolean getAllTickets() {
		return getAllTickets;
	}

	public static void setGetAllTickets(boolean getAllTickets) {
		VerboseFlags.getAllTickets = getAllTickets;
	}

	public static boolean getTicketsMathFunction() {
		return getTicketsMathFunction;
	}

	public static void setGetTicketsMathFunction(boolean getTicketsMathFunction) {
		VerboseFlags.getTicketsMathFunction = getTicketsMathFunction;
	}

	public static boolean checkUsersPassword() {
		return checkUsersPassword;
	}

	public static void setCheckUsersPassword(boolean checkUsersPassword) {
		VerboseFlags.checkUsersPassword = checkUsersPassword;
	}

	public static boolean getTicketsSingleColumnMathFunction() {
		return getTicketsSingleColumnMathFunction;
	}

	public static void setGetTicketsSingleColumnMathFunction(boolean getTicketsSingleColumnMathFunction) {
		VerboseFlags.getTicketsSingleColumnMathFunction = getTicketsSingleColumnMathFunction;
	}

	public static boolean getSession() {
		return getSession;
	}

	public static void setGetSession(boolean getSession) {
		VerboseFlags.getSession = getSession;
	}

	public static boolean minTicketPrice() {
		return minTicketPrice;
	}

	public static void setMinTicketPrice(boolean minTicketPrice) {
		VerboseFlags.minTicketPrice = minTicketPrice;
	}

	public static boolean getUsers() {
		return getUsers;
	}

	public static void setGetUsers(boolean getUsers) {
		VerboseFlags.getUsers = getUsers;
	}

	public static boolean ticketsAnswered() {
		return ticketsAnswered;
	}

	public static void setTicketsAnswered(boolean ticketsAnswered) {
		VerboseFlags.ticketsAnswered = ticketsAnswered;
	}

	public static boolean maxUser() {
		return maxUser;
	}

	public static void setMaxUser(boolean maxUser) {
		VerboseFlags.maxUser = maxUser;
	}

	public static boolean completed() {
		return completed;
	}

	public static void setCompleted(boolean completed) {
		VerboseFlags.completed = completed;
	}
}
