<%--
  Author: Aung Thu Moe
  Date: 09/03/2016
  Time: 21:55
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page session="true" %>
<!DOCTYPE html>
<html class="no-js" lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>${title}</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">

</head>
<body>
<h1>${message}</h1>
<h2>${h2}</h2>

<c:if test="${pageContext.request.userPrincipal.name != null}">
    <h2>
        Welcome : ${pageContext.request.userPrincipal.name}
    </h2>
</c:if>
<!-- csrt support -->
<form action="/logout" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <input type="submit" value="SignOut"/>
</form>
</body>
</html>
