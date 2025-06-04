<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>

<f:view>
<html lang="en" class="scroll-smooth">
<head>
    <meta charset="UTF-8" />
    <title>User Home - Expense Tracker</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <style>
        /* Smooth fade-in animation */
        .fade-in-up {    
            animation: fadeInUp 0.8s ease forwards;
            opacity: 0;
            transform: translateY(20px);
        }
        @keyframes fadeInUp {
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }
    </style>
</head>
<body class="bg-gradient-to-r from-green-100 to-blue-200 min-h-screen flex flex-col">

    <!-- Navbar-style Header -->
    <header class="bg-white shadow-lg p-6 flex flex-col sm:flex-row sm:items-center sm:justify-between max-w-7xl mx-auto rounded-b-3xl">
        <div class="mb-6 sm:mb-0">
            <h1 class="text-3xl font-extrabold text-indigo-800 flex items-center gap-3">
                <svg xmlns="http://www.w3.org/2000/svg" class="h-10 w-10 text-green-500" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2">
                    <path stroke-linecap="round" stroke-linejoin="round" d="M12 8c1.105 0 2 .895 2 2s-.895 2-2 2-2-.895-2-2 .895-2 2-2zM4 20v-2a8 8 0 0116 0v2" />
                </svg>
                Welcome, <span class="ml-2 text-indigo-700"><h:outputText value="#{sessionScope.loggedInUser.name}" /></span>
            </h1>
            <div class="mt-3 space-y-1 text-gray-700 font-semibold">
                <p><strong>Email:</strong> <h:outputText value="#{sessionScope.loggedInUser.email}" /></p>
                <p><strong>Phone:</strong> <h:outputText value="#{sessionScope.loggedInUser.phone}" /></p>
                <p><strong>Wallet Balance:</strong> <span class="text-green-600">₹<h:outputText value="#{sessionScope.loggedInUser.balance}" /></span></p>
            </div>
        </div>

        <h:form styleClass="flex gap-4 justify-center sm:justify-end">
            <h:commandButton value="Join Group" action="JoinGroup"
                styleClass="bg-blue-600 hover:bg-blue-700 transition duration-300 ease-in-out text-white font-semibold px-6 py-2 rounded-lg shadow-md shadow-blue-300" />
            <h:commandButton value="Logout" action="#{userBeanImpl.logout}"
                styleClass="bg-red-600 hover:bg-red-700 transition duration-300 ease-in-out text-white font-semibold px-6 py-2 rounded-lg shadow-md shadow-red-300" />
        </h:form>
    </header>
    
    <!-- Groups Section -->
    <h:form>
        <div class="max-w-6xl mx-auto fade-in-up mt-12">
            <div class="text-center mb-10">
                <h2 class="text-4xl font-extrabold text-indigo-800">Groups Joined</h2>
                <p class="text-gray-600 mt-2">Manage your joined groups below</p>
            </div>

            <div class="overflow-x-auto shadow-lg rounded-3xl bg-white p-8 border border-indigo-100">
                <h:dataTable value="#{groupBeanImpl.showGroupsByUserIdEjb()}" var="group"
                             styleClass="min-w-full divide-y divide-gray-300 text-gray-800 text-center">

                    <h:column>
                        <f:facet name="header">
                            <span class="font-semibold text-indigo-700 tracking-wide"><h:outputLabel value="Group Id" /></span>
                        </f:facet>
                        <h:outputText value="#{group.id}" />
                    </h:column>

                    <h:column>
                        <f:facet name="header">
                            <span class="font-semibold text-indigo-700 tracking-wide"><h:outputLabel value="Group Name" /></span>
                        </f:facet>
                        <h:outputText value="#{group.name}" />
                    </h:column>

                    <h:column>
                        <f:facet name="header">
                            <span class="font-semibold text-indigo-700 tracking-wide"><h:outputLabel value="Description" /></span>
                        </f:facet>
                        <h:outputText value="#{group.description}" />
                    </h:column>

                     <h:column>
				        <f:facet name="header">
				           <span class="font-bold text-gray-700"> <h:outputLabel value="View Group" /></span>
				        </f:facet>
				        <h:commandButton action="#{groupBeanImpl.searchUserGroupByIdEjb(group.id)}" 
				            value="View Group" 
				            styleClass="bg-blue-500 hover:bg-red-600 text-white font-semibold px-6 py-2 rounded" />
				    </h:column>
                    

                </h:dataTable>
            </div>
        </div>
    </h:form> 

    <!-- Footer -->
    <footer class="mt-auto bg-white shadow-inner p-4 text-center text-gray-500 font-medium max-w-7xl mx-auto rounded-t-3xl">
        &copy; 2025 Expense Tracker | Built with ❤️ using JSF & Tailwind CSS
    </footer>

</body>
</html>
</f:view>
