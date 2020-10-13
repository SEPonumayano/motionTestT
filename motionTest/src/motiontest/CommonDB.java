package motiontest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CommonDB {
	private static final String URL = "jdbc:mysql://localhost:3306/carfare?serverTimezone=JST";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";

	/**
	 *	登録、編集画面でプルダウンメニュー作成時に使用
	 *	片道or往復
	 **/
	public static ResultSet getRouteAll() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connect = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			Statement stmt = connect.createStatement();
			String getQuery = "SELECT * FROM route ORDER BY route_no ASC;";
			return stmt.executeQuery(getQuery);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 *	登録確認、編集確認画面で表示する際に使用
	 *	片道or往復
	 **/
	public static String getRouteName(String route_no) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connect = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			Statement stmt = connect.createStatement();
			String getQuery = "SELECT route_name FROM route WHERE route_no = " + route_no + ";";
			ResultSet rs = stmt.executeQuery(getQuery);
			rs.next();
			return rs.getString("route_name");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 *	登録、編集画面でプルダウンメニュー作成時に使用
	 *	交通手段
	 **/
	public static ResultSet getTransitAll() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connect = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			Statement stmt = connect.createStatement();
			String getQuery = "SELECT * FROM transit ORDER BY transit_no ASC;";
			return stmt.executeQuery(getQuery);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 *	登録確認、編集確認画面で表示する際に使用
	 *	交通手段
	 **/
	public static String getTransitName(String transit_no) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connect = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			Statement stmt = connect.createStatement();
			String getQuery = "SELECT transit_name FROM transit WHERE transit_no = " + transit_no + ";";
			ResultSet rs = stmt.executeQuery(getQuery);
			rs.next();
			return rs.getString("transit_name");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 *	登録、編集画面から使用
	 *	交通機関、出発駅、到着駅を受け取り、LIKE句をかけて絞り込む
	 **/
	public static ResultSet getTransitDataAll(String transit_no, String from_st, String to_st,
			int user_id, int limitSta) {
		try {
			/**
			 * 受け取った値の確認
			 * 受け取った値がnullであれば空文字("")を代入
			 * そうでなければそのまま代入する
			 **/
			transit_no = (transit_no == null) ? "'%%' " : "'%" + transit_no + "%' ";
			from_st = (from_st == null) ? "'%%' " : "'%" + from_st + "%' ";
			to_st = (to_st == null) ? "'%%' " : "'%" + to_st + "%' ";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connect = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			Statement stmt = connect.createStatement();
			String getQuery = "SELECT transit_data.data_id,transit_data.transit_no,transit.transit_name,transit_data.from_st,transit_data.to_st,transit_data.price "
					+ "FROM transit_data , transit "
					+ "WHERE transit_data.transit_no = transit.transit_no "
					+ "AND transit_data.user_id = " + user_id + " "
					+ "AND transit_data.transit_no LIKE " + transit_no
					+ "AND transit_data.from_st LIKE " + from_st
					+ "AND transit_data.to_st LIKE " + to_st
					+ "ORDER BY transit_data.data_id ASC "
					+ "LIMIT " + limitSta + " , 10;";
			return stmt.executeQuery(getQuery);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 *	登録、編集画面から使用
	 *	交通機関、出発駅、到着駅を受け取り、LIKE句をかけて絞り込む
	 *	絞り込んだ件数を返す
	 **/
	public static int getTransitDataCnt(String transit_no, String from_st, String to_st, int user_id) {
		try {
			/**
			 * 受け取った値の確認
			 * 受け取った値がnullであれば空文字("")を代入
			 * そうでなければそのまま代入する
			 **/
			transit_no = (transit_no == null || transit_no.equals("")) ? "'%%' " : "'%" + transit_no + "%' ";
			from_st = (from_st == null || from_st.equals("")) ? "'%%' " : "'%" + from_st + "%' ";
			to_st = (to_st == null || to_st.equals("")) ? "'%%' " : "'%" + to_st + "%' ";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connect = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			Statement stmt = connect.createStatement();
			String getQuery = "SELECT count(*) AS count "
					+ "FROM transit_data, transit "
					+ "WHERE transit_data.user_id = " + user_id + " "
					+ "AND transit_data.transit_no LIKE " + transit_no
					+ "AND transit_data.from_st LIKE " + from_st
					+ "AND transit_data.to_st LIKE " + to_st + " ;";
			ResultSet rs = stmt.executeQuery(getQuery);
			rs.next();
			return rs.getInt("count");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 *	一覧画面で使用
	 **/
	public static ResultSet getTransitListAll(int limitSta, int user_id) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connect = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			Statement stmt = connect.createStatement();
			String getQuery = "SELECT transit_list.id,transit_list.day,transit.transit_name,route.route_name, " +
					"transit_list.from_st,transit_list.to_st,transit_list.price " +
					"FROM transit_list,route,transit " +
					"WHERE transit_list.route_no = route.route_no " +
					"AND transit_list.transit_no = transit.transit_no " +
					"AND transit_list.user_id = " + user_id + " " +
					"AND transit_list.delete_flg = 0 " +
					"ORDER BY day ASC " +
					"LIMIT " + limitSta + " , 10;";
			return stmt.executeQuery(getQuery);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 *	一覧画面で使用
	 **/
	public static int getTransitListCnt(int user_id) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connect = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			Statement stmt = connect.createStatement();
			String getQuery = "SELECT count(*) AS count " +
					"FROM transit_list " +
					"WHERE user_id = " + user_id + " " +
					"AND delete_flg = 0 " + " ;";
			ResultSet rs = stmt.executeQuery(getQuery);
			rs.next();
			return rs.getInt("count");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 *	ログイン画面で使用
	 *	ログイン時に入力された値を受け取る
	 *	一致するものがあれば ユーザー情報を返す
	 *	一致するものがなければ nullを返す
	 **/
	public static ResultSet getUser(String password, String address) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connect = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			Statement stmt = connect.createStatement();
			String getQuery = "SELECT * FROM user "
					+ "WHERE  address LIKE '" + address + "'"
					+ "AND password LIKE '" + password + "';";
			return stmt.executeQuery(getQuery);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 *	ログイン画面で使用
	 *	ログイン時に入力された値を受け取る
	 *	一致するものがあれば ユーザー情報を返す
	 *	一致するものがなければ nullを返す
	 **/
	public static ResultSet getUser(String address) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connect = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			Statement stmt = connect.createStatement();
			String getQuery = "SELECT * FROM user "
					+ "WHERE address LIKE '" + address + "';";
			return stmt.executeQuery(getQuery);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 *	ログイン画面で使用
	 *	ログイン時に入力された値を受け取る
	 *	一致するものがあれば trueを返す
	 *	一致するものがなければ falseを返す
	 **/
	public static Boolean isUser(String loginKey) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connect = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			Statement stmt = connect.createStatement();
			String getQuery = "SELECT * FROM user "
					+ "WHERE password LIKE '" + loginKey + "';";
			return stmt.executeQuery(getQuery).next();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 *	ログイン画面で使用
	 *	利用者分類表を返す
	 **/
	public static ResultSet getUserCategoryAll() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connect = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			Statement stmt = connect.createStatement();
			String getQuery = "SELECT * FROM user_category ORDER BY role_id ASC;";
			return stmt.executeQuery(getQuery);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 *	各画面で使用
	 *
	 * 	現在利用しているユーザーidを取得する
	 *
	 * 	発見できなかった場合0を返す
	 **/
	public static int getUserId(String address, String password) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connect = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			Statement stmt = connect.createStatement();
			String getQuery = "SELECT user_id FROM user "
					+ "WHERE address LIKE '" + address + "' "
					+ "AND password LIKE '" + password + "';";
			ResultSet rs = stmt.executeQuery(getQuery);
			rs.next();
			return rs.getInt("user_id");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static int getUserId(int id) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connect = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			Statement stmt = connect.createStatement();
			String getQuery = "SELECT user_id FROM user WHERE user_id = " + id + ";";
			ResultSet rs = stmt.executeQuery(getQuery);
			rs.next();
			return rs.getInt("user_id");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/***
	 *	登録画面で使用
	 */
	public static void addDB(CommonAddData data) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connect = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			Statement stmt = connect.createStatement();
			String InsQuery = "INSERT INTO `transit_list` (`id`, `day`, `route_no`, `transit_no`, `from_st`, `to_st`, `price`, `user_id`, `delete_flg`) VALUES (NULL, '"
					+ data.getDay() + "', '" + data.getRoute_no() + "', '"
					+ data.getTransit_no() + "', '" + data.getFrom_st() + "', '"
					+ data.getTo_st() + "', '" + data.getPrice() + "', '" + data.getUser_id() + "', 0);";
			stmt.executeUpdate(InsQuery);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 *	編集画面で使用
	 **/
	public static void updateDB(CommonUpdData data) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connect = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			Statement stmt = connect.createStatement();
			String UpdQuery = "UPDATE transit_list SET day = '" + data.getDay()
					+ "', route_no = '" + data.getRoute_no() + "', transit_no = '" + data.getTransit_no()
					+ "', from_st = '" + data.getFrom_st() + "', to_st = '" + data.getTo_st()
					+ "', price = '" + data.getPrice() + "' WHERE id = " + data.getId();
			stmt.executeUpdate(UpdQuery);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 *	削除画面で使用
	 **/
	public static void deleteDB(int id) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connect = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			Statement stmt = connect.createStatement();
			String DelQuery = "UPDATE transit_list SET delete_flg = '" + 1 + "' WHERE id = " + id
					+ ";";
			stmt.executeUpdate(DelQuery);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}