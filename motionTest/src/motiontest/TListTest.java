package motiontest;

import java.io.IOException;
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
		int limitSta = (now - 1) * 10;

		/** 登録か編集かの登録値の受け渡し**/
		//String menulist = request.getParameter("menulist");
		String menulist ="1";

		/** 検索値の取得**/
		//交通機関No
		String transit_no_int=request.getParameter("transit_no");
		//int transit_no=Integer.parseInt(transit_no_int);
		int transit_no=2;

		//出発駅
		//String from_st=request.getParameter("from_st");
		String from_st="御茶ノ水";

		//到着駅
		String to_st=request.getParameter("to_st");


		/** DBの取得 **/
		//Transit_dataを取得(総数取得)
		//ResultSet rs=null;
		//rs=CommonDB.getTransitDataCnt(transit_no, from_st, to_st);

		//Transit_dataを取得(一覧取得)
		ResultSet rs =null;
		rs=CommonDB.getTransitDataAll(transit_no, from_st, to_st);


		/** 送る用の値 **/

		//ページング用のSQL入れる

		int listCnt =0;//rs.getInt(1);

		String listC = String.valueOf(listCnt);
		String noww = String.valueOf(now);
		request.setAttribute("listCnt", listC);
		request.setAttribute("page", noww);

		//登録か編集かの判断値
		request.setAttribute("menulist", menulist);

		//交通手段一覧取得用
		request.setAttribute("rs", rs);

		//検索値
		request.setAttribute("transit_no", transit_no_int);
		request.setAttribute("from_st", from_st);
		request.setAttribute("to_st", to_st);

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
	}

}
