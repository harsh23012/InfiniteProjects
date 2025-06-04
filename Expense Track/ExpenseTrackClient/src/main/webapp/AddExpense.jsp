<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>

<f:view>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Expense</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gradient-to-r from-yellow-100 to-pink-100 min-h-screen flex items-center justify-center">

    <div class="bg-white p-8 rounded-2xl shadow-xl w-full max-w-2xl">
        <h2 class="text-3xl font-bold text-center text-purple-700 mb-8">Add New Expense</h2>

        <h:form styleClass="space-y-6">
            <!-- Description -->
            <div>
                <label class="block text-gray-700 font-medium mb-2">Description</label>
                <h:inputText value="#{expense.description}" 
                             required="true" 
                             styleClass="w-full px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-purple-500" />
            </div>

            <!-- Category -->
            <div>
                <label class="block text-gray-700 font-medium mb-2">Category</label>
                <h:inputText value="#{expense.category}" 
                             styleClass="w-full px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-purple-500" />
            </div>

            <!-- Total Amount -->
            <div>
                <label class="block text-gray-700 font-medium mb-2">Total Amount</label>
                <h:inputText value="#{expense.total_amount}" 
                             required="true"
                             styleClass="w-full px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-purple-500" />
            </div>

            <!-- Expense Date -->
            <div>
                <label class="block text-gray-700 font-medium mb-2">Expense Date(yyyy-MM-dd)</label>
                <h:inputText value="#{expense.expense_date}" 
                             required="true"
                             styleClass="w-full px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-purple-500">
                    <f:convertDateTime pattern="yyyy-MM-dd" />
                </h:inputText>
            </div>

            <!-- Submit Button -->
            <div class="text-center">
                <h:commandButton value="Add Expense" action="#{expenseBeanImpl.addExpenseEjb(expense)}" 
                                 styleClass="bg-purple-600 text-white font-semibold px-6 py-2 rounded-lg shadow hover:bg-purple-700 transition duration-300" />
            </div>
        </h:form>
    </div>

</body>
</html>
</f:view>
