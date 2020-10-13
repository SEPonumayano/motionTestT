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
 * Servlet implementation class List
 */
@WebServlet("/List")
public class List extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public List() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//Connection connect=null;  //接続
    	request.setCharacterEncoding("UTF-8");
		//Statement stmt=null;       //接続
		//PreparedStatement ps=null; //接続
		//ResultSet rs=null;         //取得
		//LIMIT句の値

    	/** ページング **/
		//ページ数取得
		//String Page = request.getParameter("Page");

		//現在のページ
		//String nowPage = "";
		//if (Page != null) {
			String nowPage = "1";
		//} else {
		//	nowPage = "1";
		//}
		//int now = Integer.parseInt(nowPage);

		//int limitSta = (now - 1) * 10;


		int user_id=1;

		//総件数
		//String SelectQuery=null; //表取得
		//String CntQuery=null;    //件数取得
		//String nowPage=null;     //現在のページ
		//String SerchName=null;   //検索用文字列
		//int limitSta=0;          //検索開始位置

		int limitSta = 10;
		/** DBの取得 **/
		//Transit_dataを取得(総数取得)
		int listCnt = CommonDB.getTransitListCnt(user_id);

		ResultSet rs=CommonDB.getTransitListAll(limitSta,user_id);

		//ページング関連
		//String listC = String.valueOf(listCnt);
		//String noww = String.valueOf(now);
		//request.setAttribute("listCnt", listC);
		//request.setAttribute("page", noww);

		//交通手段一覧取得用
		request.setAttribute("rs", rs);
		//request.setAttribute("Page",Page);


		/**交通手段一覧のページへ遷移**/
		RequestDispatcher rd = request.getRequestDispatcher("/List.jsp");
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
