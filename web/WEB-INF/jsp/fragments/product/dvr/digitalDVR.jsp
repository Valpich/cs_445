<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<html>
<head>
    <title>Analog DVR</title>
</head>
<body>

<h1>Digital DVR</h1>

<spring:url value="/product/dvr/digital/add" var="digitalAdd"/>
<button class="btn btn-primary" onclick="location.href='${digitalAdd}'">Add Digital DVR</button>

<table class="table table-striped">
    <thead>
    <tr>
        <th>Description</th>
        <th>Storage Types</th>
        <th>ID</th>
        <th>Price</th>
        <th></th>
    </tr>
    </thead>

    <c:forEach var="digitalDVR" items="${digitalsDVR}">
        <tr>
            <td>${digitalDVR.description}</td>
            <td>${digitalDVR.storageTypes}</td>
            <td>${digitalDVR.id}</td>
            <td>${digitalDVR.price}</td>

            <spring:url value="/digitalDVR/${digitalDVR.id}/update" var="updateUrl" />

            <td>
                <button class="btn btn-primary" onclick="location.href='${updateUrl}'">Add Cart</button>
                <button class="btn btn-info" onclick="location.href='${updateUrl}'">Update</button>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>