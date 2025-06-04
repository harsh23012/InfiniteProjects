<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%> 
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%> 
<f:view>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Patient Records</title>
    <!-- Tailwind CSS CDN -->
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gradient-to-r from-indigo-100 via-purple-100 to-pink-100 min-h-screen flex items-center justify-center p-8">
	<jsp:include page="pNavbar.jsp"/>
    <h:form styleClass="w-full max-w-6xl bg-white rounded-3xl shadow-2xl p-10 animate-fadeIn">

        <h2 class="text-4xl font-extrabold mb-10 text-indigo-700 drop-shadow-lg text-center">
            Patient Records Managed By Doctor
        </h2>
		<div class="mb-6 text-center">
            <h:outputLabel for="doctorId" value="Enter Doctor ID:" styleClass="text-lg font-semibold mr-4" />
            <h:inputText id="doctorId" value="#{hospitalController.searchId}"
                         styleClass="border rounded-lg px-4 py-2 shadow-sm focus:ring-indigo-500 focus:border-indigo-500" />

            <h:commandButton value="Search" action="#{hospitalController.searchPatientByDoctorId}" 
                             styleClass="ml-4 bg-indigo-600 hover:bg-indigo-700 text-white px-6 py-2 rounded-lg shadow-md transition duration-300" />
        </div>
        <div class="overflow-x-auto rounded-xl shadow-lg">
            <h:dataTable value="#{hospitalController.filteredPatient}" var="p" border="0"
                styleClass="min-w-full divide-y divide-gray-200 rounded-lg overflow-hidden"
                headerClass="bg-indigo-600 text-white uppercase text-sm font-semibold"
                rowClasses="bg-white odd:bg-indigo-50 hover:bg-indigo-100 transition-colors duration-300">

                <h:column>
                    <f:facet name="header">
                        <h:outputLabel value="Pateint Id" styleClass="px-6 py-3 text-left" />
                    </f:facet>
                    <h:outputText value="#{p.patientId}" styleClass="px-6 py-4 whitespace-nowrap text-gray-700" />
                </h:column>

                <h:column>
                    <f:facet name="header">
                        <h:outputLabel value="Patient Name" styleClass="px-6 py-3 text-left" />
                    </f:facet>
                    <h:outputText value="#{p.patientName}" styleClass="px-6 py-4 whitespace-nowrap text-gray-800 font-semibold" />
                </h:column>

                <h:column>
                    <f:facet name="header">
                        <h:outputLabel value="DoctorId" styleClass="px-6 py-3 text-left" />
                    </f:facet>
                    <h:outputText value="#{p.doctorId}" styleClass="px-6 py-4 whitespace-nowrap text-gray-700" />
                </h:column>

                <h:column>
                    <f:facet name="header">
                        <h:outputLabel value="Date Of Birth" styleClass="px-6 py-3 text-left" />
                    </f:facet>
                    <h:outputText value="#{p.dateOfBirth}" styleClass="px-6 py-4 whitespace-nowrap text-gray-700" />
                </h:column>

            </h:dataTable>
        </div>

    </h:form>

    <style>
        @keyframes fadeIn {
            0% {
                opacity: 0;
                transform: translateY(-20px);
            }
            100% {
                opacity: 1;
                transform: translateY(0);
            }
        }

        .animate-fadeIn {
            animation: fadeIn 1s ease forwards;
        }
    </style>

</body>
</html>
</f:view>
