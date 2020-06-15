<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<h2>Mobiles list</h2>


<ul>
   <c:forEach items="${mobiles}" var="mobile">
      <li><a href='showmobile?id=${mobile.id}'>${mobile.model}</a></li>
   </c:forEach>
</ul>

</body>
</html>
