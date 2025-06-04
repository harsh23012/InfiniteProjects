<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html" %>

<f:view>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Search Doctor by ID</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gradient-to-r from-blue-100 via-purple-100 to-pink-100 min-h-screen flex items-center justify-center p-8">

    <jsp:include page="pNavbar.jsp"/>

    <h:form styleClass="w-full max-w-3xl bg-white rounded-3xl shadow-2xl p-10 animate-fadeIn">

        <h2 class="text-4xl font-extrabold mb-8 text-indigo-700 drop-shadow-lg text-center">
            Search Patient by ID
        </h2>
        <div class="mb-6 text-center">
            <h:outputLabel for="patientId" value="Enter Patient ID:" styleClass="text-lg font-semibold mr-4" />
            <h:inputText id="patientId" value="#{hospitalController.searchId}"
                         styleClass="border rounded-lg px-4 py-2 shadow-sm focus:ring-indigo-500 focus:border-indigo-500" />

            <h:commandButton value="Search" action="#{hospitalController.searchPatientById}" 
                             styleClass="ml-4 bg-indigo-600 hover:bg-indigo-700 text-white px-6 py-2 rounded-lg shadow-md transition duration-300" />
        </div>

        <h:panelGroup rendered="#{not empty hospitalController.foundPatient}">
            <div class="mt-8 bg-indigo-50 rounded-xl p-6 shadow-inner text-lg space-y-4">
                <div><strong>Patient ID:</strong> <h:outputText value="#{hospitalController.foundPatient.patientId}" /></div>
                <div><strong>Name:</strong> <h:outputText value="#{hospitalController.foundPatient.patientName}" /></div>
                <div><strong>Doctor Id:</strong> <h:outputText value="#{hospitalController.foundPatient.doctorId}" /></div>
                <div><strong>Date Of Birth:</strong> <h:outputText value="#{hospitalController.foundPatient.dateOfBirth}" /></div>
            </div>
        </h:panelGroup>

        <!-- If no doctor found -->
        <h:panelGroup rendered="#{hospitalController.searchPerformed and empty hospitalController.foundPatient}">
            <div class="mt-8 text-red-600 text-center font-semibold">
                No patient found with the entered ID.
            </div>
        </h:panelGroup>

    </h:form>

    <style>
        @keyframes fadeIn {
            0% { opacity: 0; transform: translateY(-10px); }
            100% { opacity: 1; transform: translateY(0); }
        }
        .animate-fadeIn {
            animation: fadeIn 0.8s ease forwards;
        }
    </style>
</body>
</html>
</f:view>
