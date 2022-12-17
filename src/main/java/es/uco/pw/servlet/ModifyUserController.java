package es.uco.pw.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.uco.pw.business.ValueObject.Tipo;
import es.uco.pw.data.DAO.UsuarioDAO;
import es.uco.pw.display.javabean.CustomerBean;

/**
 * Servlet implementation class ModifyUserController
 */
@WebServlet("/ModifyUserController")
public class ModifyUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyUserController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        CustomerBean customerBean = (CustomerBean) session.getAttribute("customerBean");
        if(customerBean == null || customerBean.getCorreo().equals("")){

            out.println("Tienes que iniciar sesión");
            RequestDispatcher disp = request.getRequestDispatcher("/modifyuserViewFailure.jsp");
            disp.include(request, response);
        }
        else {
            RequestDispatcher disp = request.getRequestDispatcher("MVC/view/modifyView.jsp");
            disp.include(request, response);
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();

        CustomerBean customerBean = (CustomerBean) session.getAttribute("customerBean");

            String nombre = request.getParameter("name");
            String apellidos = request.getParameter("surname");
            String correo = request.getParameter("mail");
            String password = request.getParameter("password");

            customerBean.setNombre(nombre);
            customerBean.setPassword(password);

            if(correo != null){
                UsuarioDAO msd = new UsuarioDAO();
                msd.update(apellidos, nombre, password,correo);
                    if(customerBean.getTipo().equals(Tipo.admin)){
                        RequestDispatcher disp = request.getRequestDispatcher("MVC/view/modifySuccess.jsp");
                        disp.forward(request, response);
                    }
                    else{
                        RequestDispatcher disp = request.getRequestDispatcher("MVC/view/modifySuccess1.jsp");
                        disp.forward(request, response);
                    }
            }
            else{
            	if(customerBean.getTipo().equals(Tipo.admin)) {
            		RequestDispatcher disp = request.getRequestDispatcher("MVC/view/modifyFail.jsp");
                    disp.forward(request, response);
            	}else if(customerBean.getTipo().equals(Tipo.no_admin)) {
            		RequestDispatcher disp = request.getRequestDispatcher("MVC/view/modifyFail1.jsp");
                    disp.forward(request, response);
            	}
                
            }
        }

	}


