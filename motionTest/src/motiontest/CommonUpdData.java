package motiontest;

public class CommonUpdData extends CommonAddData {
		private int id;

		public CommonUpdData(int id, String day, String route_no, String transit_no,
				String from_st, String to_st, String price, int user_id) {
			super(day, route_no, transit_no, from_st, to_st, price, user_id);
			this.id = id;
		}

		public int getId() {
			return id;
		}

}
