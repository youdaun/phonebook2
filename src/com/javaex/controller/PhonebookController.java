package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;

@WebServlet("/pbc")
public class PhonebookController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//System.out.println("PhonebookController");
		
		String act = request.getParameter("action");
		//System.out.println(act);
		
		if("list".equals(act)) {
			System.out.println("action=list");
			PhoneDao phoneDao = new PhoneDao();
			List<PersonVo> personList = phoneDao.getPersonList();
			
			//System.out.println(personList);

			
			//html과 list섞어서 표현해야한다
			//servlet 으로는 표현이 복잡하다 ---> jsp를 이용한다.
			
			//리퀘스트 어트리뷰트 영역에 리스트 넘겨줌
			request.setAttribute("pList", personList); 
			
			//포뤄드 누구한테 시킬건지
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/list.jsp");
			//포워드
			rd.forward(request, response); 	
			
		} else if("writeForm".equals(act)) {
			System.out.println("action=writeForm");
			
			RequestDispatcher rd= request.getRequestDispatcher("/WEB-INF/writeForm.jsp");
			rd.forward(request, response); 	
			
		} else if("write".equals(act)) {
			System.out.println("action=write");
			
			//파라미터 3개를 꺼내온다 --> Vo로 만든다
			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");
			
			PersonVo pvo = new PersonVo(name, hp, company);
			
			//dao 메모리에 올린다 --> dao.insert(vo);
			PhoneDao pDao = new PhoneDao();
			pDao.PersonInsert(pvo);
			
			response.sendRedirect("/phonebook2/pbc?action=list");
			
		} else if("delete".equals(act)) {
			System.out.println("action=delete");
			
			int id = Integer.parseInt(request.getParameter("id"));
			PhoneDao pDao = new PhoneDao();
			pDao.PersonDelete(id);
			
			response.sendRedirect("/phonebook2/pbc?action=list");
			
			
		} else if("updateForm".equals(act)) {
			System.out.println("action=updateForm");
			
			int id = Integer.parseInt(request.getParameter("id"));
			PhoneDao pDao = new PhoneDao();
			PersonVo pvo = pDao.getPerson(id);
			
			request.setAttribute("pvo", pvo);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/updateForm.jsp");
			rd.forward(request, response); 	
			
		} else if("update".equals(act)) {
			
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");
			
			PhoneDao pDao = new PhoneDao();
			PersonVo pvo = new PersonVo(name, hp, company);
			pDao.PersonUpdate(id, pvo);	
			
			response.sendRedirect("/phonebook2/pbc?action=list");
		} else {
			System.out.println("파라미터값 없음");
		}
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
