<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Add User</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    <script src="https://unpkg.com/lucide@latest"></script>
    <style>
        body {
            background: linear-gradient(to right, #dfe9f3, #ffffff);
            font-family: 'Segoe UI', sans-serif;
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .glass {
            background: rgba(255, 255, 255, 0.5);
            backdrop-filter: blur(15px);
            border-radius: 1rem;
            padding: 2rem;
            box-shadow: 0 8px 30px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 600px;
            animation: fadeIn 1s ease-in-out;
        }

        @keyframes fadeIn {
            from { opacity: 0; transform: translateY(40px); }
            to { opacity: 1; transform: translateY(0); }
        }

        .icon {
            animation: float 3s ease-in-out infinite;
        }

        @keyframes float {
            0%, 100% { transform: translateY(0); }
            50% { transform: translateY(-8px); }
        }
    </style>
</head>
<body>
<f:view>
    <div class="glass">
        <div class="icon flex justify-center mb-4">
            <i data-lucide="user-plus" class="w-12 h-12 text-blue-600"></i>
        </div>

        <h2 class="text-3xl font-bold text-center text-blue-700 mb-6">Add New User</h2>

        <h:form>
            <h:messages globalOnly="true" layout="list" styleClass="text-red-600 text-sm mb-4 list-disc pl-5"/>

            <div class="grid grid-cols-1 gap-4">
                <div>
                    <h:outputLabel for="name" value="Name" styleClass="block text-gray-700"/>
                    <h:inputText id="name" value="#{user.name}" required="true"
                                 styleClass="w-full px-4 py-2 border rounded-md mt-1"/>
                </div>

                <div>
                    <h:outputLabel for="email" value="Email" styleClass="block text-gray-700"/>
                    <h:inputText id="email" value="#{user.email}" required="true"
                                 styleClass="w-full px-4 py-2 border rounded-md mt-1"/>
                </div>

                <div>
                    <h:outputLabel for="username" value="Username" styleClass="block text-gray-700"/>
                    <h:inputText id="username" value="#{user.user_name}" required="true"
                                 styleClass="w-full px-4 py-2 border rounded-md mt-1"/>
                </div>

                <div>
                    <h:outputLabel for="phone" value="Phone" styleClass="block text-gray-700"/>
                    <h:inputText id="phone" value="#{user.phone}" required="true"
                                 styleClass="w-full px-4 py-2 border rounded-md mt-1"/>
                </div>

                <div>
                    <h:outputLabel for="balance" value="Balance" styleClass="block text-gray-700"/>
                    <h:inputText id="balance" value="#{user.balance}" required="true"
                                 styleClass="w-full px-4 py-2 border rounded-md mt-1"/>
                </div>

                <div>
                    <h:outputLabel for="userType" value="User Type" styleClass="block text-gray-700"/>
                    <h:selectOneMenu id="userType" value="#{user.user_type}" styleClass="w-full px-4 py-2 border rounded-md mt-1">
                        <f:selectItem itemLabel="Select" itemValue="" />
                        <f:selectItem itemLabel="ADMIN" itemValue="ADMIN" />
                        <f:selectItem itemLabel="USER" itemValue="USER" />
                    </h:selectOneMenu>
                </div>

                <div>
                    <h:outputLabel for="password" value="Password" styleClass="block text-gray-700"/>
                    <h:inputSecret id="password" value="#{user.password}" required="true"
                                   styleClass="w-full px-4 py-2 border rounded-md mt-1"/>
                </div>
            </div>

            <div class="mt-6 text-center">
                <h:commandButton value="➕ Add User" action="#{userBeanImpl.addUserEjb(user)}"
                                 styleClass="bg-blue-600 hover:bg-blue-700 text-white font-bold px-6 py-2 rounded-lg shadow-md transition-all duration-200 hover:scale-105"/>
            </div>
            
        </h:form>
    </div>

    <script>
        lucide.createIcons();
    </script>
</f:view>
</body>
</html>
