<%@taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html" %>

<h:form>
<nav class="fixed top-0 left-0 w-full bg-indigo-700 text-white shadow-md px-6 py-4 flex items-center justify-between z-50">
    <h1 class="text-xl font-bold flex items-center gap-2">
        <i class="fas fa-hospital-symbol text-2xl"></i>
         <li>
            <h:commandLink action="Main" styleClass="hover:text-pink-400 transition">Hospital Management</h:commandLink>
        </li>
    </h1>

    <ul class="hidden md:flex space-x-8 text-lg font-semibold">
        <li>
            <h:commandLink action="MedicalHistoryHome" styleClass="hover:text-pink-400 transition">Home</h:commandLink>
        </li>
        <li>
            <h:commandLink action="AddMedHistory" styleClass="hover:text-pink-400 transition">Add Medical History</h:commandLink>
        </li>
        <li>
            <h:commandLink action="MedHistoryList" styleClass="hover:text-pink-400 transition">Medical History List</h:commandLink>
        </li>
        <li>
            <h:commandLink action="SearchMedHistoryById" styleClass="hover:text-pink-400 transition">Patient's Medical History</h:commandLink>
        </li>
        <li>
            <h:commandLink action="ShowPatientTest" styleClass="hover:text-pink-400 transition">Patient's Test</h:commandLink>
        </li>
    </ul>

    <button id="mobile-menu-button" type="button" class="md:hidden focus:outline-none">
        <i class="fas fa-bars text-2xl"></i>
    </button>
</nav>

<div id="mobile-menu" class="hidden bg-indigo-600 text-white px-6 py-4 space-y-4 md:hidden">
    <h:commandLink action="MedicalHistoryHome" styleClass="block hover:text-pink-400 transition">Home</h:commandLink>
    <h:commandLink action="AddMedHistory" styleClass="block hover:text-pink-400 transition">Add Medical History</h:commandLink>
    <h:commandLink action="MedHistoryList" styleClass="block hover:text-pink-400 transition">Medical History List</h:commandLink>
    <h:commandLink action="SearchMedHistoryById" styleClass="block hover:text-pink-400 transition">Patient's Medical History</h:commandLink>
    <h:commandLink action="ShowPatientTest" styleClass="block hover:text-pink-400 transition">Patient's Test</h:commandLink>
</div>
</h:form>

<script>
window.addEventListener('DOMContentLoaded', () => {
    const btn = document.getElementById('mobile-menu-button');
    const menu = document.getElementById('mobile-menu');

    btn.addEventListener('click', () => {
        menu.classList.toggle('hidden');
    });
});
</script>
