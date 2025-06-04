<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>

<f:view>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Group Admin - Expense Tracker</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gradient-to-br from-yellow-100 to-pink-200 min-h-screen flex flex-col items-center justify-start p-10">

    <!-- Group Info Card -->
    <div class="bg-white rounded-3xl shadow-xl p-8 w-full max-w-3xl text-center fade-in-up">
        <h2 class="text-3xl font-bold text-indigo-700 mb-4">Group Details</h2>

        <div class="text-left text-gray-700 space-y-3">
            <p><strong>Group ID:</strong> <h:outputText value="#{sessionScope.groupDetails.id}" /></p>
            <p><strong>Name:</strong> <h:outputText value="#{sessionScope.groupDetails.name}" /></p>
            <p><strong>Description:</strong> <h:outputText value="#{sessionScope.groupDetails.description}" /></p>
            <p><strong>Created At:</strong> 
                <h:outputText value="#{sessionScope.groupDetails.created_at}">
                    <f:convertDateTime pattern="yyyy-MM-dd HH:mm" />
                </h:outputText>
            </p>
        </div>

        <!-- Add Expense Button -->
        <h:form styleClass="mt-6">
            <h:commandButton value="Add Expense"
                action="AddExpense"
                styleClass="bg-indigo-600 hover:bg-indigo-700 text-white font-semibold px-6 py-2 mt-4 rounded-lg shadow-md shadow-indigo-300 transition" />
        </h:form>
    </div>

    <!-- Group Members Table -->
    <div class="bg-white rounded-3xl shadow-xl p-8 w-full max-w-3xl mt-10 fade-in-up">
        <h2 class="text-2xl font-bold text-indigo-700 mb-4 text-center">Group Members</h2>

        <h:dataTable value="#{sessionScope.groupMembers}" var="member"
             styleClass="min-w-full text-left border border-gray-200 rounded-lg overflow-hidden shadow-sm"
             headerClass="bg-indigo-600 text-white text-sm font-semibold"
             rowClasses="bg-white even:bg-gray-100 text-gray-700">

		    <h:column>
		        <f:facet name="header"><h:outputLabel value="ID" /></f:facet>
		        <h:outputText value="#{member.id}" />
		    </h:column>
		
		    <h:column>
		        <f:facet name="header"><h:outputLabel value="Name" /></f:facet>
		        <h:outputText value="#{member.user.name}" />
		    </h:column>
		
		    <h:column>
		        <f:facet name="header"><h:outputLabel value="Email" /></f:facet>
		        <h:outputText value="#{member.user.email}" />
		    </h:column>
		
		    <h:column>
		        <f:facet name="header"><h:outputLabel value="Phone" /></f:facet>
		        <h:outputText value="#{member.user.phone}" />
		    </h:column>
		
		    <h:column>
		        <f:facet name="header"><h:outputLabel value="Role" /></f:facet>
		        <h:outputText value="#{member.role}" />
		    </h:column>
		
		    <h:column>
		        <f:facet name="header"><h:outputLabel value="Collected Amount" /></f:facet>
		        <h:outputText value="#{member.collectedAmount}" />
		    </h:column>
		
		</h:dataTable>
    </div>
    
    <div class="bg-white rounded-3xl shadow-xl p-8 w-full max-w-3xl mt-10 fade-in-up">
	    <h2 class="text-2xl font-bold text-red-600 mb-4 text-center">You Owe To</h2>
	
	    <h:dataTable value="#{sessionScope.splits}" var="owed"
	        styleClass="min-w-full text-left border border-gray-200 rounded-lg overflow-hidden shadow-sm"
	        headerClass="bg-red-600 text-white text-sm font-semibold"
	        rowClasses="bg-white even:bg-gray-100 text-gray-700">
	
	        <h:column>
	            <f:facet name="header"><h:outputLabel value="Name" /></f:facet>
	            <h:outputText value="#{groupBeanImpl.getUserNameById(owed.owedto)}" />
	        </h:column>
	
	        <h:column>
	            <f:facet name="header"><h:outputLabel value="Amount Owed" /></f:facet>
	            <h:outputText value="#{owed.amount_owed}">
	                <f:convertNumber type="currency" currencySymbol="₹" />
	            </h:outputText>
	        </h:column>
	
	        <h:column>
	            <f:facet name="header"><h:outputLabel value="Action" /></f:facet>
	            <h:form>
	                <h:commandButton value="Pay"
	                    action="#{expenseBeanImpl.payAmountEjb(owed.owedToId)}"
	                    styleClass="bg-green-600 hover:bg-green-700 text-white px-4 py-1 rounded shadow" />
	            </h:form>
	        </h:column>
	    </h:dataTable>
	</div>

</body>
</html>
</f:view>
