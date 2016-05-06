<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Student greeting</title>
    </head>

    <body>
        Hello student <b>${student.name} ${student.surname}</b> from group <b>${student.group.name}</b>
    </body>
</html>