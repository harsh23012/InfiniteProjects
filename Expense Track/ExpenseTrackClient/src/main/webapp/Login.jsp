<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Login - Expense Tracker</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    <script src="https://unpkg.com/lucide@latest"></script>
    <style>
        body {
            background: linear-gradient(to right, #cfd9df, #e2ebf0);
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            font-family: 'Segoe UI', sans-serif;
        }

        .glass {
            background: rgba(255, 255, 255, 0.3);
            backdrop-filter: blur(15px);
            border: 1px solid rgba(255, 255, 255, 0.2);
            animation: fadeIn 1.2s ease-out;
        }

        @keyframes fadeIn {
            from { opacity: 0; transform: translateY(30px); }
            to { opacity: 1; transform: translateY(0); }
        }

        .icon {
            animation: float 3s ease-in-out infinite;
        }

        @keyframes float {
            0%, 100% { transform: translateY(0); }
            50% { transform: translateY(-10px); }
        }

        .error-message {
            color: #dc2626;
            font-size: 0.875rem;
            margin-top: 0.25rem;
        }
    </style>
</head>
<body>
<f:view>
    <div class="glass max-w-md w-full p-8 rounded-2xl shadow-2xl text-center">

        <div class="icon flex justify-center mb-5">
            <i data-lucide="log-in" class="w-12 h-12 text-blue-700"></i>
        </div>

        <h2 class="text-3xl font-bold text-gray-800 mb-2">Welcome Back</h2>
        <p class="text-sm text-gray-600 mb-6">Login to your account</p>

        <h:form>

            <!-- Global Error Messages -->
            <h:messages globalOnly="true" layout="table" styleClass="text-red-600 text-sm mb-4 text-left" />

            <!-- Login Option Dropdown -->
            <div class="mb-4 text-left">
                <h:outputLabel for="loginOption" value="Login With" styleClass="text-gray-700 block mb-1" />
                <h:selectOneMenu id="loginOption" value="#{userBeanImpl.loginOption}" required="true"
                                 styleClass="w-full px-4 py-2 rounded-md border border-gray-300">
                    <f:selectItem itemLabel="Select Option" itemValue="" />
                    <f:selectItem itemLabel="Username" itemValue="USERNAME" />
                    <f:selectItem itemLabel="Email" itemValue="EMAIL" />
                    <f:selectItem itemLabel="Phone" itemValue="PHONE" />
                </h:selectOneMenu>
                <h:message for="loginOption" styleClass="error-message" />
            </div>

            <!-- User Input Field -->
            <div class="mb-4 text-left">
                <h:outputLabel for="userInput"
                    value="#{userBeanImpl.loginOption eq 'USERNAME' ? 'Username' :
                             (userBeanImpl.loginOption eq 'EMAIL' ? 'Email' :
                             (userBeanImpl.loginOption eq 'PHONE' ? 'Phone' : 'Username / Email / Phone'))}"
                    styleClass="text-gray-700 block mb-1" />
                <h:inputText id="userInput" value="#{userBeanImpl.userInput}" required="true"
                             styleClass="w-full px-4 py-2 rounded-md border border-gray-300" />
                <h:message for="userInput" styleClass="error-message" />
            </div>

            <!-- Password -->
            <div class="mb-6 text-left">
                <h:outputLabel for="passwordInput" value="Password" styleClass="text-gray-700 block mb-1" />
                <h:inputSecret id="passwordInput" value="#{userBeanImpl.password}" required="true"
                               styleClass="w-full px-4 py-2 rounded-md border border-gray-300" />
                <h:message for="passwordInput" styleClass="error-message" />
            </div>

            <!-- Submit -->
            <div class="mt-6">
                <h:commandButton value="🔐 Login" action="#{userBeanImpl.login}"
                                 styleClass="bg-blue-600 text-white font-semibold px-6 py-3 rounded-lg hover:bg-blue-700 transition transform hover:scale-105 shadow-md w-full" />
            </div>

        <p class="mt-6 text-sm text-gray-500">Don't have an account?
            <a href="AddUser.jsf" class="text-blue-700 font-medium hover:underline">Register</a>
        </p>
        </h:form>
    </div>

    <script>
        lucide.createIcons();
    </script>
</f:view>
</body>
</html>
