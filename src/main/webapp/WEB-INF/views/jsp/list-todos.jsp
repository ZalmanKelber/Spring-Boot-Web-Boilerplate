<%@ include file="common/header.jspf"%>
    <table class="table table-striped">
        <caption>Your todos are</caption>
        <thead>
        <tr>
            <th>Description</th>
            <th>Target Date</th>
            <th>Status</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${todos}" var="todo">
            <tr>
                <td>${todo.getDesc()}</td>
                <td><fmt:formatDate value="${todo.getTargetDate()}" pattern="dd/MM/yyyy"></fmt:formatDate></td>
                <td>${todo.isDone() ? "completed" : "pending"}</td>
                <td><a type="button" class="btn btn-primary" href="/update-todo?id=${todo.getId()}">Edit</a></td>
                <td><a type="button" class="btn btn-danger" href="/delete-todo?id=${todo.getId()}">Delete</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div>
        <a class="button" href="/add-todo">Add a Todo</a>
    </div>
<%@ include file="common/closing.jspf"%>