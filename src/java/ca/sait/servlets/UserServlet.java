
package ca.sait.servlets;

import ca.sait.models.Role;
import ca.sait.models.User;
import ca.sait.services.RoleService;
import ca.sait.services.UserService;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jacke
 */
public class UserServlet extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserService UserService = new UserService();
        RoleService RoleService = new RoleService();
        String emailToEdit = "";
        String editFirstName = "";
        String editLastName = "";
      
        
        
        
        
        
        List<User> users;
        List<Role> roles;
        try {
            users = UserService.getAll();
            roles = RoleService.getAll();
            
            
            request.setAttribute("users", users);
            request.setAttribute("roles", roles);
            
            String action = request.getParameter("action");
            if (action != null && action.equals("delete")) {
            String userEmail = request.getParameter("user").replaceAll(" ", "+");
            
            
                UserService.delete(userEmail);

                response.sendRedirect("user");
                request.getSession().invalidate();
            }
            else if(action != null && action.equals("edit"))
            {
                String userEmail = request.getParameter("user").replaceAll(" ", "+");
                
                for(int x = 0; x < users.size(); x++)
                {
                    String check = users.get(x).getEmail();
                    if(userEmail.equals(check))
                    {
                        emailToEdit = users.get(x).getEmail();
                        editFirstName = users.get(x).getFirstName();
                        editLastName = users.get(x).getLastName();
                    }
                }
            }
            
            
            
            
            request.setAttribute("emailToEdit", emailToEdit);
            request.setAttribute("editFirstName", editFirstName);
            request.setAttribute("editLastName", editLastName);
            this.getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("---Error---");
        }
        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserService userService = new UserService();
        RoleService roleService = new RoleService();
        List<User> users;
        List<Role> roles = null;
        try {
            roles = roleService.getAll();
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String action = request.getParameter("action");
        
        if(action.equals("createUser"))
            {
                String email = request.getParameter("inputEmail");
                String firstName = request.getParameter("inputFirstName");
                String lastName = request.getParameter("inputLastName");
                String password = request.getParameter("inputPassword");
                String roleName = request.getParameter("selectRole");
                Role role = null;
                
                for(int x = 0; x < roles.size(); x++)
                {
                    if(roles.get(x).getName().equals(roleName))
                    {
                        role = roles.get(x);
                    }
                }
                    

            try {
                    userService.insert(email, true, firstName, lastName, password, role);
                } 
                    catch (Exception ex) {
                    Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        else if(action.equals("submitEdit"))
        {
                String email = request.getParameter("viewEmail");
                String firstName = request.getParameter("editFirstName");
                String lastName = request.getParameter("editLastName");
                String roleName = request.getParameter("editRole");
                String password = "";
                Role role = null;
                boolean active = false;
                User user = null;
                
                
                  for(int x = 0; x < roles.size(); x++)
                {
                    if(roles.get(x).getName().equals(roleName))
                    {
                        role = roles.get(x);
                    }
                }
                  
            try {
                user = userService.get(email);
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            password = user.getPassword();
            active = user.isActive();
            
            try {
                userService.update(email, active, firstName, lastName, password, role);
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
               
            
            request.getSession().invalidate();
            response.sendRedirect("user");
        }
}