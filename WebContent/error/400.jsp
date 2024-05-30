<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    

 <!--================================
         START 404 AREA
 =================================-->
 <section class="four_o_four_area section--padding2">
     <div class="container">
         <div class="row">
             <div class="col-md-6 offset-md-3 text-center">
                 <img src="images/404.png" alt="404 page">
                 <div class="not_found">
                     <h3>Oops! Page Not Found</h3>
                     <a href="<c:url value='/index.do'/>" class="btn btn--round btn--default">Back to Home</a>
                 </div>
             </div>
         </div>
     </div>
 </section>
 <!--================================
         END 404 AREA
 =================================-->