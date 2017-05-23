package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DataBase;
import model.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String name=request.getParameter("name");  
	    String password=request.getParameter("password");  
	    List<String> info=new ArrayList<String>();  
	    
	    if(name==null||"".equals(name))
	    { //用户名输入格式问题  
	        info.add("用户名不能为空");  
	        System.out.println("用户名不能为空");  
	    }  
	  
	    if(password==null||"".equals(password))
	    {//密码输入格式问题  
	        info.add("密码不能为空");  
	        System.out.println("密码不能为空");  
	    }  
	    
	    if(info.size()==0){  
	        User user=new User();  
	        user.setUserid(name);
	        user.setPswd(password);;  
	        //UserDAOProxy userDAOProxy=new UserDAOProxy();  
	        DataBase db = new DataBase();
	        try {  	              
	            if(db.compareIdPassword(name, password)==0){  
	                info.add("用户登录成功，欢迎"+user.getUserid()+"光临！");               
	            }else {  
	                info.add("用户登录失败，错误的用户名和密码");  
	            }                         
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	    }  
	    request.setAttribute("info", info);//保存错误信息  
	    request.getRequestDispatcher("login.jsp").forward(request,response);//跳转
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
