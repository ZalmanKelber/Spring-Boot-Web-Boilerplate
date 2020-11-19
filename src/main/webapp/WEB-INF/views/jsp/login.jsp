<%@ include file="common/header.jspf"%>
    <p style="color: #ff0000">${errorMessage}</p>
    <form method="post">
        <fieldset>
            <label name="name">Username:</label>
            <input type="text" name="name" class="form-control" />
        </fieldset>
        <fieldset>
            <label name="password">Password:</label>
            <input type="password" name="password" class="form-control" />
        </fieldset>
        <input type="submit" class="btn btn-primary" />
    </form>
<%@ include file="common/closing.jspf"%>