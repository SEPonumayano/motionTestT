package motiontest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddTestCheck
 */
@WebServlet("/AddTestCheck")
public class AddTestCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTestCheck() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String menulist=request.getParameter("menulist");
		String day=request.getParameter("day");
		String route_no=request.getParameter("route_no");
		String transit_no=request.getParameter("transit_no");
		String from_st=request.getParameter("from_st");
		String to_st=request.getParameter("to_st");
		String price=request.getParameter("price");

		String userid="1";
		int user_id = Integer.parseInt(userid);

		CommonAddData data = new CommonAddData(day, route_no, transit_no, from_st, to_st, price, user_id);

		CommonDB.addDB(data);


		System.out.println("テーブルを登録しました.");

		System.out.println("つなげてないからもどれないよ");

	}

}
