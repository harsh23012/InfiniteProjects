<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Home</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    <script src="https://unpkg.com/lucide@latest"></script>
    <style>
        body {
            background: linear-gradient(135deg, #e0f7fa, #fce4ec);
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            font-family: 'Segoe UI', sans-serif;
        }

        .glass {
            background: rgba(255, 255, 255, 0.25);
            backdrop-filter: blur(10px);
            border: 1px solid rgba(255, 255, 255, 0.2);
            animation: fadeIn 1.5s ease-out;
        }

        @keyframes fadeIn {
            from { opacity: 0; transform: scale(0.95); }
            to { opacity: 1; transform: scale(1); }
        }

        .icon-container {
            animation: bounce 2s infinite;
        }

        @keyframes bounce {
            0%, 100% { transform: translateY(0); }
            50% { transform: translateY(-8px); }
        }
    </style>
</head>
<body>
<f:view>
    <div class="glass p-10 rounded-3xl shadow-2xl text-center max-w-lg w-full">
        <div class="icon-container flex justify-center mb-4">
            <i data-lucide="shield-check" class="w-14 h-14 text-blue-700"></i>
        </div>
        <h1 class="text-3xl font-extrabold text-gray-800 mb-2">Welcome to Expense Tracker</h1>
        <p class="text-gray-600 text-sm mb-8">Manage your Expense smartly, securely, and seamlessly.</p>

        <h:form>
            <div class="flex flex-col gap-4">
                <h:commandButton value="🚀 Register"
                                 action="AddUser.jsp?faces-redirect=true"
                                 styleClass="bg-green-600 text-white font-semibold py-3 rounded-xl hover:bg-green-700 transition transform hover:scale-105 shadow-md"/>

                <h:commandButton value="🔐 Login"
                                 action="Login.jsp?faces-redirect=true"
                                 styleClass="bg-blue-600 text-white font-semibold py-3 rounded-xl hover:bg-blue-700 transition transform hover:scale-105 shadow-md"/>
            </div>
        </h:form>
    </div>

    <script>
        lucide.createIcons();
    </script>
</f:view>
</body>
</html>
