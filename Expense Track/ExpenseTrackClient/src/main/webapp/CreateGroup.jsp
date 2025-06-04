<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>

<f:view>
<html lang="en" class="scroll-smooth">
<head>
    <meta charset="UTF-8" />
    <title>Create Group - Expense Tracker</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gradient-to-r from-yellow-100 to-pink-200 min-h-screen flex flex-col">

    <!-- Header -->
    <header class="bg-white shadow-md p-6 flex flex-col sm:flex-row sm:items-center sm:justify-between max-w-7xl mx-auto rounded-b-3xl">
        <div>
            <h1 class="text-3xl font-extrabold text-indigo-800">Create a New Group</h1>
            <p class="mt-2 text-gray-600">Logged in as <strong><h:outputText value="#{sessionScope.loggedInUser.name}" /></strong></p>
        </div>
        <h:form>
            <h:commandButton value="Back to Dashboard" action="AdminHome"
                styleClass="bg-indigo-600 hover:bg-indigo-700 text-white font-semibold px-6 py-2 rounded-lg shadow-md shadow-indigo-300 transition" />
        </h:form>
    </header>

    <!-- Create Group Form -->
    <div class="flex-grow flex justify-center items-center py-10">
        <div class="bg-white shadow-2xl rounded-3xl p-10 w-full max-w-2xl">
            <h:form styleClass="space-y-6">
                <h:panelGrid columns="1" styleClass="space-y-4">

                    <!-- Group Name -->
                    <div>
                        <label for="groupName" class="block text-gray-700 font-semibold mb-1">Group Name</label>
                        <h:inputText id="groupName" value="#{tripGroup.name}"
                                     required="true" requiredMessage="Group name is required"
                                     styleClass="w-full border border-gray-300 rounded-lg px-4 py-2 focus:outline-none focus:ring-2 focus:ring-yellow-500" />
                        <h:message for="groupName" style="color: red;" />
                    </div>

                    <!-- Group Description -->
                    <div>
                        <label for="description" class="block text-gray-700 font-semibold mb-1">Description</label>
                        <h:inputTextarea id="description" value="#{tripGroup.description}" rows="4"
                                         required="true" requiredMessage="Description is required"
                                         styleClass="w-full border border-gray-300 rounded-lg px-4 py-2 focus:outline-none focus:ring-2 focus:ring-yellow-500" />
                        <h:message for="description" style="color: red;" />
                    </div>


                    <!-- Submit Button -->
                    <div class="text-center">
                        <h:commandButton value="Create Group" action="#{groupBeanImpl.createGroupEjb(tripGroup)}"
					        styleClass="bg-yellow-500 hover:bg-yellow-600 text-white font-bold py-2 px-6 rounded-lg shadow-md transition duration-300">
					        
					        <!-- Correct placement of setPropertyActionListener -->
					        <f:setPropertyActionListener target="#{tripGroup.user}" value="#{sessionScope.loggedInUser}" />
					    </h:commandButton>
                        </div>

                </h:panelGrid>
            </h:form>
        </div>
    </div>

    <!-- Footer -->
    <footer class="mt-auto bg-white shadow-inner p-4 text-center text-gray-500 font-medium max-w-7xl mx-auto rounded-t-3xl">
        &copy; 2025 Expense Tracker | Built with ❤️ using JSF & Tailwind CSS
    </footer>

</body>
</html>
</f:view>
