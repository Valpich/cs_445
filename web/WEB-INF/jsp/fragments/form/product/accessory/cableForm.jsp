<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<body>

<div class="container">

    <c:choose>
        <c:when test="${not empty cableForm}">
            <h1>Add Cable</h1><br>
            <spring:url value="/cable" var="cable"/>
        </c:when>
        <c:otherwise>
            <h1>Update Cable</h1><br>
            <spring:url value="/cableUpdate" var="cable"/>
        </c:otherwise>
    </c:choose>

    <form action='${cable}' method="POST">

        <div class="col-sm-10">
            <label class="col-sm-2 control-label">Description</label>

            <c:choose>
                <c:when test="${not empty cableForm}">
                    <input type="text" name="description" placeholder="">
                </c:when>
                <c:otherwise>
                    <input type="text" name="description" value="${cableFormUpdate.description}" placeholder="">
                    <input hidden type="text" name="id" value="${cableFormUpdate.id}" >
                </c:otherwise>
            </c:choose>

        </div>

        <div class="col-sm-10">
            <label class="col-sm-2 control-label">Length</label>

            <c:choose>
                <c:when test="${not empty cableForm}">
                    <input type="number" step="0.01" name="length" placeholder="">
                </c:when>
                <c:otherwise>
                    <input type="number" step="0.01" name="length" value="${cableFormUpdate.length}" placeholder="">
                </c:otherwise>
            </c:choose>

        </div>

        <div class="col-sm-10">
            <label class="col-sm-2 control-label">Price</label>

            <c:choose>
                <c:when test="${not empty cableForm}">
                    <input type="number" step="0.01" name="price" placeholder="">
                </c:when>
                <c:otherwise>
                    <input type="number" step="0.01" name="price" value="${cableFormUpdate.price}" placeholder="">
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