<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html" %>

<f:view>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Search Medical History</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gradient-to-r from-blue-100 via-purple-100 to-pink-100 min-h-screen flex items-center justify-center p-8">

    <jsp:include page="mNavBar.jsp"/>

    <h:form styleClass="w-full max-w-4xl bg-white rounded-3xl shadow-2xl p-10 animate-fadeIn">

        <h2 class="text-4xl font-extrabold mb-8 text-indigo-700 drop-shadow-lg text-center">
            Search Patient's Medical History
        </h2>

        <!-- Search Field -->
        <div class="mb-6 text-center">
            <h:outputLabel for="patientId" value="Enter Patient ID:" styleClass="text-lg font-semibold mr-4" />
            <h:inputText id="patientId" value="#{hospitalController.searchId}"
                         styleClass="border rounded-lg px-4 py-2 shadow-sm focus:ring-indigo-500 focus:border-indigo-500" />
            <h:commandButton value="Search" action="#{hospitalController.searchMedHistoryById}" 
                             styleClass="ml-4 bg-indigo-600 hover:bg-indigo-700 text-white px-6 py-2 rounded-lg shadow-md transition duration-300" />
        </div>

        <!-- Display Patient ID once -->
        <h:panelGroup rendered="#{not empty hospitalController.foundHistory}">
            <div class="text-center mb-6">
                <span class="text-xl font-bold text-gray-800">Patient ID: </span>
                <h:outputText value="#{hospitalController.searchId}" 
                              styleClass="text-xl text-indigo-700 font-semibold" />
            </div>
        </h:panelGroup>
		<div class="w-full max-w-4xl mb-2 grid grid-cols-3 bg-indigo-100 text-indigo-700 font-semibold px-6 py-3 rounded-t-xl">
    <div>Med ID</div>
    <div>Medicines</div>
    <div>Tests</div>
</div>
        <!-- Medical History Table -->
        <h:dataTable value="#{hospitalController.foundHistory}" var="history" 
                     styleClass="w-full mb-6 rounded-xl shadow-inner text-gray-800 border border-indigo-200"
                     rowClasses="odd:bg-indigo-50 even:bg-indigo-100"
                     headerClass="bg-indigo-100 font-semibold text-indigo-700 text-left px-6 py-3"
                     columnClasses="px-6 py-2">

            <!--
                Header will render ONCE here for the entire table
            -->
            <h:column>
                <h:outputText value="#{history.medId}" />
            </h:column>

            <h:column>
                <h:outputText value="#{history.medicines}" />
            </h:column>

            <h:column>
                <h:outputText value="#{history.tests}" />
            </h:column>

        </h:dataTable>

        <!-- No results message -->
        <h:panelGroup rendered="#{hospitalController.searchPerformed and empty hospitalController.foundHistory}">
            <div class="mt-8 text-red-600 text-center font-semibold">
                No medical history found for the entered patient ID.
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
