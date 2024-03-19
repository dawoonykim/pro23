package com.spring.ex04;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/mem4.do")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
		System.out.println("MemberServlet 객체 초기화");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doHandle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("mem4.do 요청함");
		MemberDAO dao = new MemberDAO();
		MemberVO memberVO = new MemberVO();
		String action = request.getParameter("action");
		System.out.println("action : " + action);
		String nextPage = "";

		if (action == null || action.equals("listMembers")) {
			List<Map<String, String>> membersList = dao.selectAllMemberList();
			System.out.println("membersList : " + membersList);
			request.setAttribute("membersList", membersList);
			nextPage = "test01/listMembers.jsp";
		} else if (action.equals("searchMember")) {
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			System.out.println("서블릿에서 받아온 name : " + name + " 서블릿에서 받아온 email " + email);

			memberVO.setName(name);
			memberVO.setEmail(email);
			List<Map<String, String>> membersList = dao.searchMember(memberVO);
			request.setAttribute("membersList", membersList);
			nextPage = "test01/listMembers.jsp";
		} else if (action.equals("foreachSelect")) {
			System.out.println("action : " + action);
			List<String> arrList = new ArrayList();
			arrList.add("이순신");
			arrList.add("김유신");
			arrList.add("홍길동");

			List<Map<String, String>> membersList = dao.foreachSelect(arrList);
			request.setAttribute("membersList", membersList);
			nextPage = "test01/listMembers.jsp";
		}else if(action.equals("deleteMember")) {
			String id = request.getParameter("id");
			System.out.println(id);

			dao.deleteMember(id);
		
			List<Map<String, String>> membersList = dao.selectAllMemberList();
			request.setAttribute("membersList", membersList);
			nextPage = "test01/listMembers.jsp";
		}else if(action.equals("insertMember")) {
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			System.out.println(id + " " + pwd + " " + name + " " + email);

			memberVO.setId(id);
			memberVO.setPwd(pwd);
			memberVO.setName(name);
			memberVO.setEmail(email);

			dao.insertMember(memberVO);
			List<Map<String, String>> membersList = dao.selectAllMemberList();
			request.setAttribute("membersList", membersList);
			nextPage = "test01/listMembers.jsp";
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
		dispatcher.forward(request, response);
	}
	
	

}
