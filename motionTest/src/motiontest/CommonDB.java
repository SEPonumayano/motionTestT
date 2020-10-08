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
			//DBへ接続
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
	 *	登録、編集画面でプルダウンメニュー作成時に使用
	 *	交通手段
	 **/
	public static ResultSet getTransitAll() {
		try {
			//DBへ接続
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
	 *	登録、編集画面から使用
	 *	交通機関、出発駅、到着駅を受け取り、LIKE句をかけて絞り込む
	 **/
	public static ResultSet getTransitDataAll(String transit_no, String from_st, String to_st,int limitSta) {
		try {
			/**
			 * 受け取った値の確認
			 * 受け取った値がnullであれば空文字("")を代入
			 * そうでなければそのまま代入する
			 **/
			transit_no = (transit_no == null) ? "'%%' " : "'%" + transit_no + "%' ";
			from_st = (from_st == null) ? "'%%' " : "'%" + from_st + "%' ";
			to_st = (to_st == null) ? "'%%' " : "'%" + to_st + "%' ";
			//DBへ接続
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connect = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			Statement stmt = connect.createStatement();
			String getQuery = "SELECT transit_data.data_id,transit_data.transit_no,transit.transit_name,transit_data.from_st,transit_data.to_st,transit_data.price "
					+ "FROM transit_data , transit "
					+ "WHERE transit_data.transit_no = transit.transit_no "
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
	 *	絞り込んだ件数を返す  transitNo
	 **/
	public static int getTransitDataCnt(String transit_no, String from_st, String to_st) {
		try {
			/**
			 * 受け取った値の確認
			 * 受け取った値がnullであれば空文字("")を代入
			 * そうでなければそのまま代入する
			 **/
			//String transitNo = (transit_no == 0) ? "'%%' " : "'%" + transit_no + "%' ";
			transit_no = (transit_no == null) ? "'%%' " : "'%" + transit_no + "%' ";
			from_st = (from_st == null) ? "'%%' " : "'%" + from_st + "%' ";
			to_st = (to_st == null) ? "'%%' " : "'%" + to_st + "%' ";
			//DBへ接続
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connect = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			Statement stmt = connect.createStatement();
			String getQuery = "SELECT count(*) AS count "
					+ "FROM transit_data , transit "
					+ "WHERE transit_data.transit_no = transit.transit_no "
					+ "AND transit_data.transit_no LIKE " + transit_no
					+ "AND transit_data.from_st LIKE " + from_st
					+ "AND transit_data.to_st LIKE " + to_st
					+ "ORDER BY transit_data.data_id ASC;";
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
	public static ResultSet getTransitListAll() {
		try {
			//DBへ接続
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connect = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			Statement stmt = connect.createStatement();
			String getQuery = "SELECT transit_list.id,transit_list.day,transit.transit_name,route.route_name, " +
					"transit_list.from_st,transit_list.to_st,transit_list.price " +
					"FROM transit_list,route,transit " +
					"WHERE transit_list.route_no = route.route_no " +
					"AND transit_list.transit_no = transit.transit_no " +
					"ORDER BY day ASC;";
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
	public static ResultSet getUser(String address, String password) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connect = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			Statement stmt = connect.createStatement();
			String getQuery = "SELECT * FROM user "
					+ "WHERE address LIKE '" + address + "' "
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
	 *	一致するものがあれば trueを返す
	 *	一致するものがなければ falseを返す
	 **/
	public static Boolean isUser(String address, String password) {
		try {
			return getUser(address, password).next();
		} catch (SQLException e) {
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
			//DBへ接続
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

}
