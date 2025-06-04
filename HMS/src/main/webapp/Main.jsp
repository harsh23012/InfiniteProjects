<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html" %>

<f:view>
<html>
<head>
    <title>Hospital Management System - Main</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet" />
</head>
<body class="bg-gradient-to-r from-indigo-100 via-purple-100 to-pink-100 min-h-screen flex items-center justify-center p-8">

    <h:form styleClass="bg-white p-12 rounded-3xl shadow-2xl max-w-md w-full text-center">
        <h1 class="text-4xl font-extrabold mb-12 text-indigo-700 drop-shadow-lg">
            Welcome to Hospital Management System
        </h1>

        <div class="space-y-8">
            <h:commandButton value="Go to Doctor Page" 
                             action="Home" 
                             styleClass="w-full bg-indigo-600 hover:bg-indigo-700 text-white font-bold py-4 rounded-lg shadow-md transition duration-300" />

            <h:commandButton value="Go to Patient Page" 
                             action="PatientHome" 
                             styleClass="w-full bg-purple-600 hover:bg-purple-700 text-white font-bold py-4 rounded-lg shadow-md transition duration-300" />
            <h:commandButton value="Go to Medical History Page" 
                             action="MedicalHistoryHome" 
                             styleClass="w-full bg-indigo-600 hover:bg-indigo-700 text-white font-bold py-4 rounded-lg shadow-md transition duration-300" />
        </div>
    </h:form>

</body>
</html>
</f:view>
