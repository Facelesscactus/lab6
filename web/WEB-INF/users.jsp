<%-- 
    Document   : users
    Created on : 1-Nov-2022, 7:20:31 PM
    Author     : jacke
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
        <title>Users</title>
    </head>
    <body>
        <h1>User management System</h1>     
        <div class="container">
            
            <form action="user" method="POST">
                <input type="hidden" name="action" value="createUser">
                <h2>Add A User</h2>
                <table>
                    <label for="inputEmail">Email: </label><input type="email" name="inputEmail"><br>
                    <label for="inputFirstName">First Name: </label><input type="text" name="inputFirstName" ><br>
                    <label for="inputLastName">Last Name: </label><input type="text" name="inputLastName" ><br>
                    <label for="inputPassword">Password: </label><input type="password" name="inputPassword" ><br>
                    <label for="selectRole"></label><br>
                        
                            
                                <select name="selectRole">
                                    <option>Select Role</option>
                                    <c:forEach var="role" items="${roles}">
                                    <option>${role.name}</option>
                                    </c:forEach>
                                </select>
                            
                        
                
                
                
                <button type="submit">Submit</button>
            </form>
            
            <br>
            <br>
            
            <div class="row">
                <div class="col">
                     <table class="tab">
                        <thread>
                            <tr>
                                <th>E-mail</th>
                                <th>First name</th>
                                <th>Last name</th>
                                <th>Active</th>
                                <th>Actions</th>
                            </tr>
                        </thread>
                        <tbody>
                            <c:forEach var="user" items="${users}"> 
                                    <tr>
                                            <td>${user.email}</td>
                                            <td>${user.firstName}</td>
                                            <td>${user.lastName}</td>
                                            <td>${user.active ? "Y" : "N"}</td>
                                            <td>
                                                <a href="user?action=edit&user=${user.email}">Edit</a>
                                                <a href="user?action=delete&user=${user.email}">Delete</a>
                                            </td>
                                    </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            
            
            <form action="user" method="POST">
                <input type="hidden" name="action" value="submitEdit">
                <h2>Edit User</h2>
                <table>
                    <tr><td><label for="viewEmail">Email: </label></td><td><input type="text" name="viewEmail" value="<%= request.getAttribute("emailToEdit")%>" readonly="true"></td></tr>
                    <tr><td><label for="editFirstName">First Name: </label></td><td><input type="text" name="editFirstName" value="<%= request.getAttribute("editFirstName") %>"></td></tr>
                    <tr><td><label for="editLastName">Last Name: </label></td><td><input type="text" name="editLastName" value="<%= request.getAttribute("editLastName") %>"></td></tr>
                    <tr><td><label for="editRole"></label></td>
                        <td>
                                <select name="editRole">
                                    <option>Select Role</option>
                                    <c:forEach var="role" items="${roles}">
                                    <option>${role.name}</option>
                                    </c:forEach>
                                </select>
                            
                        </td>
                    </tr>
                </table>
                <button type="submit">Submit</button>
            </form>
            
            
        </div> 
    </body>
</html>