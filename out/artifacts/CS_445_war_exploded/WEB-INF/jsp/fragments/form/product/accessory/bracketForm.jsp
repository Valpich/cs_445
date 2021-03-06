<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<body>

<div class="container">

    <c:choose>
        <c:when test="${not empty bracketForm}">
            <h1>Add Bracket</h1><br>
            <spring:url value="/bracket" var="bracket"/>
        </c:when>
        <c:otherwise>
            <h1>Update Bracket</h1><br>
            <spring:url value="/bracketUpdate" var="bracket"/>
        </c:otherwise>
    </c:choose>

    <form action='${bracket}' method="POST">

        <div class="col-sm-10">
            <label class="col-sm-2 control-label">Description</label>

            <c:choose>
                <c:when test="${not empty bracketForm}">
                    <input type="text" name="description" placeholder="">
                </c:when>
                <c:otherwise>
                    <input type="text" name="description" value="${bracketFormUpdate.description}" placeholder="">
                    <input hidden type="text" name="id" value="${bracketFormUpdate.id}" >
                </c:otherwise>
            </c:choose>

        </div>

        <div class="col-sm-10">
            <label class="col-sm-2 control-label">Price</label>

            <c:choose>
                <c:when test="${not empty bracketForm}">
                    <input type="text" name="price" placeholder="">
                </c:when>
                <c:otherwise>
                    <input type="text" name="price" value="${bracketFormUpdate.price}" placeholder="">
                </c:otherwise>
            </c:choose>

        </div>

        <div class="col-sm-10">
            <button type="submit" class="btn btn-primary">Save</button>
        </div>

    </form>

</div>

</body>
</html>