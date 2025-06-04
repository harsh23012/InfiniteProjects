<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html" %>

<f:view>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Add Patient</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gradient-to-r from-green-100 via-blue-100 to-purple-100 min-h-screen flex items-center justify-center p-8">
    
    <jsp:include page="mNavBar.jsp"/>

    <h:form styleClass="w-full max-w-3xl bg-white rounded-3xl shadow-2xl p-10 animate-fadeIn">
        <h2 class="text-4xl font-extrabold mb-8 text-blue-700 drop-shadow-lg text-center">
            Add New Patient
        </h2>

        <div class="grid grid-cols-1 md:grid-cols-2 gap-6">

            <!-- Patient Id -->
            <div>
                <h:outputLabel for="patientId" value="Patient Id:" styleClass="block text-sm font-medium text-gray-700 mb-1"/>
                <h:inputText id="patientId" value="#{hospitalController.medicalHistory.patientId}"
                             styleClass="mt-1 block w-full rounded-md border-gray-300 shadow-sm px-4 py-2" />
            </div>
            <!-- Medicines -->
            <div>
                <h:outputLabel for="medicines" value="Medicines(Seperate By Commas):" styleClass="block text-sm font-medium text-gray-700 mb-1"/>
                <h:inputText id="medicines" value="#{hospitalController.medicalHistory.medicines}"
                             styleClass="mt-1 block w-full rounded-md border-gray-300 shadow-sm px-4 py-2" />
            </div>
            <!-- Tests -->
            <div>
                <h:outputLabel for="tests" value="Tests(Seperate By Commas):" styleClass="block text-sm font-medium text-gray-700 mb-1"/>
                <h:inputText id="tests" value="#{hospitalController.medicalHistory.tests}"
                             styleClass="mt-1 block w-full rounded-md border-gray-300 shadow-sm px-4 py-2" />
            </div>

            
        </div>

        <!-- Submit Button -->
        <div class="mt-8 text-center">
            <h:commandButton value="Add Medical History" action="#{hospitalController.addMedicalHistory}"
                             styleClass="bg-blue-600 hover:bg-blue-700 text-white font-bold py-3 px-8 rounded-lg shadow-md transition duration-300" />
        </div>
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
