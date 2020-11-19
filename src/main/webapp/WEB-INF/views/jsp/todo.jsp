<%@ include file="common/header.jspf"%>
    <form:form method="post" commandName="todo">
        <form:hidden path="id" />
        <fieldset class="form-group">
            <form:label path="desc">Description</form:label>
            <form:input path="desc" type="text"
                        class="form-control" required="required"/>
            <form:errors path="desc" cssClass="text-warning"/>
        </fieldset>
        <fieldset class="form-group">
            <form:label path="targetDate">Description</form:label>
            <form:input path="targetDate" type="text"
                        class="form-control" required="required"/>
            <form:errors path="targetDate" cssClass="text-warning"/>
        </fieldset>

        <button type="submit" class="btn btn-success">Add</button>
    </form:form>
<%@ include file="common/closing.jspf"%>

