package motiontest;

public class CommonAddData {
	private String day;
	private String route_no;
	private String transit_no;
	private String from_st;
	private String to_st;
	private String price;
	private int user_id;

	public CommonAddData(String day, String route_no, String transit_no,
			String from_st, String to_st, String price, int user_id) {
		this.day = day;
		this.route_no = route_no;
		this.transit_no = transit_no;
		this.from_st = from_st;
		this.to_st = to_st;
		this.price = price;
		this.user_id = user_id;
	}

	public String getDay() {
		return day;
	}

	public String getRoute_no() {
		return route_no;
	}

	public String getTransit_no() {
		return transit_no;
	}

	public String getFrom_st() {
		return from_st;
	}

	public String getTo_st() {
		return to_st;
	}

	public String getPrice() {
		return price;
	}

	public int getUser_id() {
		return user_id;
	}

}
