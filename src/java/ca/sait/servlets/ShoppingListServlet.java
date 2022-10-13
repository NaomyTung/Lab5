/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ca.sait.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author naomy
 */
public class ShoppingListServlet extends HttpServlet {

    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //in the register page, if user does exist it will directly go to the shoppingList 
        String name = (String)request.getSession().getAttribute("name");
        String action = request.getParameter("action");
        if (name != null) {
            if(action != null && action.equals("logout")) {
                request.getSession().invalidate();
                this.getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
                return;
            }
            this.getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
        } else {
            this.getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
        }
        
        
        
       
        
       
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
   
        String action = request.getParameter("action");
        
        if (action.equals("register")) {
            String name = request.getParameter("name");
        
            if (name != null) {
                //cretes session
                request.getSession().setAttribute("name", name);
                
                //must crete array in session, othrwise it will create an exception when we try to pull it in
                ArrayList<String> items = new ArrayList<>();
                request.getSession().setAttribute("items", items);
            } 
        }else if (action.equals("add")) {
            String item = request.getParameter("item");
                
            //adds item 
            ArrayList<String> items = (ArrayList<String>) request.getSession().getAttribute("items");
                
            items.add(item);
                
            request.getSession().setAttribute("items", items);
        } else if (action.equals("delete")) {
            String itemValue = request.getParameter("item");
            
             ArrayList<String> items = (ArrayList<String>) request.getSession().getAttribute("items");
             
             int indexToDelete = -1;
             //items.remove(itemValue);
             for (int i = 0; i < items.size(); i++) {
                 if (items.get(i).equals(itemValue));
                 indexToDelete = i;
                 break;
             }
             
             if (indexToDelete != -1) {
                 items.remove(indexToDelete);
             }
             
             request.getSession().setAttribute("items", items);
             
        }
        
       
        this.getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
        
    }


}
