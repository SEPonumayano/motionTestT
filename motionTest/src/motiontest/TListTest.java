package motiontest;

import java.io.IOException;
import java.net.URLDecoder;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TListTest
 */
@WebServlet("/TListTest")
public class TListTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TListTest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String errmsg=request.getParameter("errmsg");

		/** ユーザーID取得 **/
		//String userid=(String)request.getAttribute("user_id");
		String userid="1";
		int user_id = Integer.parseInt(userid);


		/** 登録か編集かの登録値の受け渡し**/
		String menulist=request.getParameter("menulist");
		//String menulist ="1";

		if(menulist==null) {
			System.out.println("nullだよ");
		}else {
			System.out.println(menulist);
		}

		String day=request.getParameter("day");
		String route_no=request.getParameter("route_no");
		String route_name=request.getParameter("route_name");
		String transit_name =request.getParameter("transit_name");
		String price=request.getParameter("price");

		//片道往復name
				String route_name_encoded=request.getParameter("route_name_encoded");
				if(route_name_encoded!=null) {
					route_name= URLDecoder.decode(route_name_encoded, "UTF-8");
				}

				//交通機関name
				String transit_name_encoded =request.getParameter("transit_name_encoded");
				if(transit_name_encoded!=null) {
					transit_name= URLDecoder.decode(transit_name_encoded, "UTF-8");
				}

		/** ページング **/

		//ページ数取得
		String Page = request.getParameter("Page");

		//現在のページ
		String nowPage = "";
		if (Page != null) {
			nowPage = Page;
		} else {
			nowPage = "1";
		}
		int now = Integer.parseInt(nowPage);

		//LIMIT句の値
		int limitSta =(now - 1)*10;

		/** 検索値の取得**/
		//交通機関No
		String transit_no=request.getParameter("transit_no");
		//int transit_no=Integer.parseInt(transit_no_int);
		if(transit_no.isEmpty()) {
			transit_no="";
			System.out.println("empty transitNo");
		}
		System.out.println(transit_no+"です");


		//出発駅
		String from_st=request.getParameter("from_st");

		if(from_st==null) {
			from_st="";
			System.out.println("empty from_st");
		}


		//出発駅（エンコード版）
		String from_st_encoded=request.getParameter("from_st_encoded");
		//String from_st_encoded="%E6%8A%BC%E4%B8%8A";

		//エンコードからデコードへ変換
		if(from_st_encoded!=null) {
			from_st= URLDecoder.decode(from_st_encoded, "UTF-8");
		}

		System.out.println(from_st);

		//到着駅
		String to_st=request.getParameter("to_st");

		if(to_st==null) {
			to_st="";
			System.out.println("empty to_st");
		}

		//到着駅（エンコード版）
		String to_st_encoded=request.getParameter("to_st_encoded");

		//エンコードからデコードへ変換
		if(to_st_encoded!=null) {
			to_st= URLDecoder.decode(to_st_encoded, "UTF-8");
		}

		System.out.println(to_st);


		/** DBの取得 **/
		//Transit_dataを取得(総数取得)
		int listCnt=CommonDB.getTransitDataCnt(transit_no, from_st, to_st,user_id);

		//Transit_dataを取得(一覧取得)
		ResultSet rs =CommonDB.getTransitDataAll(transit_no, from_st, to_st,user_id,limitSta);


		/** 送る用の値 **/
		//ユーザーID
		request.setAttribute("user_id", userid);

		//登録か編集かの判断値
		request.setAttribute("menulist", menulist);
		request.setAttribute("day", day);
		request.setAttribute("route_no", route_no);
		request.setAttribute("route_name", route_name);
		request.setAttribute("transit_name", transit_name);

		request.setAttribute("errmsg", errmsg);

		//ページング関連
		String listC = String.valueOf(listCnt);
		String noww = String.valueOf(now);
		request.setAttribute("listCnt", listC);
		request.setAttribute("page", noww);

		//交通手段一覧取得用
		request.setAttribute("rs", rs);

		//検索値
		request.setAttribute("transit_no", transit_no);
		request.setAttribute("from_st", from_st);
		request.setAttribute("to_st", to_st);
		request.setAttribute("price", price);

		/**交通手段一覧のページへ遷移**/
		RequestDispatcher rd = request.getRequestDispatcher("/tListTest.jsp");
		rd.forward(request, response);


	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);


	}}
