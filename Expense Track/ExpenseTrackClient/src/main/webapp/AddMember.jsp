<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<f:view>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Members</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gradient-to-r from-yellow-100 to-pink-200 min-h-screen flex items-center justify-center">

    <h:form styleClass="bg-white p-8 rounded-3xl shadow-xl max-w-3xl w-full space-y-6">

        <h2 class="text-2xl font-bold text-indigo-700 text-center">Add Members to Group</h2>
		<h:commandButton value="Load Users" action="#{groupBeanImpl.getAvailableUsersForGroup}"
                styleClass="bg-red-600 hover:bg-red-700 transition duration-300 ease-in-out text-white font-semibold px-6 py-2 rounded-lg shadow-md shadow-red-300" />
        
        <!-- Advance Amount Input -->
        <div>
            <h:outputLabel for="amount" value="Advance Amount (Same for All):" 
                styleClass="block mb-1 font-semibold text-gray-700"/>
            <h:inputText id="amount" value="#{groupBeanImpl.amountCollected}"
                styleClass="w-full border border-gray-300 rounded px-3 py-2 focus:outline-none focus:ring-2 focus:ring-indigo-400"/>
        </div>
        
        

        <!-- User List with Checkboxes -->
        <div class="overflow-x-auto border rounded-xl">
            <h:dataTable value="#{groupBeanImpl.availableUsersForGroup}" var="user"
                         styleClass="min-w-full text-center text-sm text-gray-700 divide-y divide-gray-300">

                <h:column>
                    <f:facet name="header">
                        <span class="font-semibold text-indigo-700"><h:outputLabel value="Select" /></span>
                    </f:facet>
                    <h:selectBooleanCheckbox value="#{groupBeanImpl.selectedUserMap[user.id]}" />
                </h:column>

                <h:column>
                    <f:facet name="header">
                        <span class="font-semibold text-indigo-700"><h:outputLabel value="User Id" /></span>
                    </f:facet>
                    <h:outputText value="#{user.id}" />
                </h:column>

                <h:column>
                    <f:facet name="header">
                        <span class="font-semibold text-indigo-700"><h:outputLabel value="Name" /></span>
                    </f:facet>
                    <h:outputText value="#{user.name}" />
                </h:column>

                <h:column>
                    <f:facet name="header">
                        <span class="font-semibold text-indigo-700"><h:outputLabel value="Email" /></span>
                    </f:facet>
                    <h:outputText value="#{user.email}" />
                </h:column>
            </h:dataTable>
        </div>

        <!-- Submit Button -->
        <h:commandButton value="Add Selected Members"
                         action="#{groupBeanImpl.addSelectedUsersToGroup}"
                         styleClass="w-full bg-indigo-600 hover:bg-indigo-700 text-white font-semibold py-2 rounded-lg shadow-md transition" />

        <h:messages styleClass="text-red-600 text-sm" />

    </h:form>

</body>
</html>
</f:view>
