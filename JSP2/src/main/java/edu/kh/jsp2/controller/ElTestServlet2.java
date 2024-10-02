package edu.kh.jsp2.controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/el_scope")
public class ElTestServlet2 extends HttpServlet {
	
	// a태그 요청은 GET 방식
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 요청 처리
		// 1. page scope -> JSP에서만 확인가능
		
		// 2. request scope
		// - 요청 받은 Servlet 과 요청이 위임된 JSP에서 유지되는 객체
		
		// 1) 객체에 값(속성) 추가하는 방법
		//    범위객체.setAttribute("key", value);
		
		// 2) 객체에서 값(속성) 얻어오는 방법
		//    Object 범위객체.getAttribute("key");
		// -> 반환형 Object -> 필요 시 다운캐스팅
		
		req.setAttribute("requestValue", "request scope 객체에 세팅한 값");
		// requestValue=request scope 객체에 세팅한 값
		
		System.out.println(req.getAttribute("requestValue"));
		// request scope 객체에 세팅한 값
		
		// 3. session scope
		// - 클라이언트가 서버에 첫 요청 시
		//   서버쪽에 생성된느 객체
		// - 클라이언트가 브라우저를 종료 하거나
		//   지정된 세션 만료 시간이 지나면 사라짐
		//  -> 위 상황이 아니면 계속 유지되는 객체
		
		// 1) session scope 객체 얻어오기
		HttpSession session = req.getSession();
		
		// 2) session scope 객체게 값 세팅
		session.setAttribute("sessionValue", "session scope 객체에 세팅한 값");
		
		// 4. application scope
		// - 서버 전체에 1개만 존재하는 객체
		// -> 모든 클라이언트가 공유
		
		// - 서버 시작 시 생성, 서버 종료 시 소멸
		
		// 1) application scope 객체 얻어오기
		
		ServletContext application = req.getServletContext();
		
		// 2) 값 세팅
		application.setAttribute("applicationValue", "application scope 객체에 세팅한 값");
		
		
		// -----------------------------------
		
		// 범위별 우선 순위 확인
		// 좁은 범위가 우선순위가 높다!
		// page > request > session > application
		
		// key값을 동일하게 하여 범위별 객체에 값 추가
		
		req.setAttribute("menu", "짬뽕(request)");
		session.setAttribute("menu", "짜장(session)");
		application.setAttribute("menu", "볶음밥(applicatioin)");
		
		
		// -----------------------------------
		
		// 응답 처리
		String path = "/WEB-INF/views/el/scope.jsp";
		
		RequestDispatcher dispatcher = req.getRequestDispatcher(path);
		
		dispatcher.forward(req, resp);
	}
}
