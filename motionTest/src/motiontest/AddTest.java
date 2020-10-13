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
 * Servlet implementation class AddTest
 */
@WebServlet("/AddTest")
public class AddTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTest() {
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


		//識別値
		String menulist=request.getParameter("menulist");
		if(menulist==null) {
			menulist ="1";
		}

		//日付
		String day=request.getParameter("day");

		//片道or往復
		String route_no=request.getParameter("route_no");

		//交通機関
		String transit_no=request.getParameter("transit_no");

		//出発駅
		String from_st=request.getParameter("from_st");

		//到着駅
		String to_st=request.getParameter("to_st");

		//値段
		String price=request.getParameter("price");

		/** DBの取得 **/
		ResultSet rs =CommonDB.getRouteAll();

		ResultSet rs1=CommonDB.getTransitAll();

		//識別値
		request.setAttribute("menulist", menulist);

		//送る値
		request.setAttribute("day", day);
		request.setAttribute("route_no", route_no);
		request.setAttribute("transit_no", transit_no);
		request.setAttribute("from_st", from_st);
		request.setAttribute("to_st", to_st);
		request.setAttribute("price",price);

		//DB
		request.setAttribute("rs", rs);
		request.setAttribute("rs1", rs1);

		RequestDispatcher rd = request.getRequestDispatcher("/addTest.jsp");
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		//値
		String menulist=request.getParameter("menulist");
		String day=request.getParameter("day");
		String route_no=request.getParameter("route_no");
		String transit_no=request.getParameter("transit_no");
		String from_st=request.getParameter("from_st");
		String to_st=request.getParameter("to_st");
		String price=request.getParameter("price");

		//ユーザーID
		String userid="1";
		int user_id = Integer.parseInt(userid);

		//エラーチェック
		CommonAddData data = new CommonAddData(day, route_no, transit_no, from_st, to_st, price, user_id);
		String errmsg = CommonErrMsg.getErrMsg(data);

		System.out.println(errmsg);

		//行先
		if(errmsg.isEmpty()) {
			System.out.println("ok");

			request.setAttribute("menulist", menulist);

			request.setAttribute("day", day);
			request.setAttribute("route_no", route_no);
			request.setAttribute("transit_no", transit_no);
			request.setAttribute("from_st", from_st);
			request.setAttribute("to_st", to_st);
			request.setAttribute("price",price);

			/** DBの取得 **/
			String route_name=CommonDB.getRouteName(route_no);
			String transit_name=CommonDB.getTransitName(transit_no);

			request.setAttribute("route_name", route_name);
			request.setAttribute("transit_name", transit_name);

			RequestDispatcher rd =
            		request.getRequestDispatcher("/addTestCheck.jsp");
            rd.forward(request,response);

		}else {
			/** DBの取得 **/
			ResultSet rs =CommonDB.getRouteAll();
			ResultSet rs1=CommonDB.getTransitAll();

			request.setAttribute("menulist", menulist);

			request.setAttribute("day", day);
			request.setAttribute("route_no", route_no);
			request.setAttribute("transit_no", transit_no);
			request.setAttribute("from_st", from_st);
			request.setAttribute("to_st", to_st);
			request.setAttribute("price",price);

			request.setAttribute("rs", rs);
			request.setAttribute("rs1", rs1);

			request.setAttribute("errmsg",errmsg);

			RequestDispatcher rd =
            		request.getRequestDispatcher("/addTest.jsp");
            rd.forward(request,response);
		}

	}

}
