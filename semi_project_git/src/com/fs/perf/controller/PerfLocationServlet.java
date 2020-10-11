package com.fs.perf.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.fs.model.vo.Performance;
import com.fs.perf.model.service.PerfService;

/**
 * Servlet implementation class PerfLocationServlet
 */
@WebServlet("/perf/perfLocation")
public class PerfLocationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PerfLocationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String cate=request.getParameter("cate");
		List<String> list=new PerfService().searchLocation(cate);
		Map<String,Performance> map=new PerfService().locationPick(list,cate);
		JSONArray arr=new JSONArray();
		JSONObject j;
		if(pList!=null) {
			for(Performance perf:pList) {
				j=new JSONObject();
				j.put("perfNo",perf.getPerfNo());
				j.put("perfName",perf.getPerfName());
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy년 MM월 dd일");
				j.put("perfStart",sdf.format(perf.getPerfStart()));
				j.put("perfEnd",sdf.format(perf.getPerfEnd()));
				j.put("location",perf.getPerfLocation()); 
				j.put("poster",perf.getPerfPoster());
				arr.add(j);
			}
		}
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(arr);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
