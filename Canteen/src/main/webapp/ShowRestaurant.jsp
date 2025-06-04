<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%> 
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%> 
<f:view>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h:form>
        <center>
                <h2><h:outputText value="Restaurant Records"/></h2>
            </center>
        <h:dataTable value="#{restaurantController.showRestaurant()}" var="r" border="3">
        	    <h:column>
                    <f:facet name="header">
                    	<h:outputLabel value="Restaurant Id" />
                    </f:facet>
                    <h:outputText value="#{r.restaurantId}"/>
                </h:column>
                    <h:column>
                     <f:facet name="header">
                    	<h:outputLabel value="Restaurant Name" />
                    </f:facet>
                    <h:outputText value="#{r.restaurantName}"/>
                </h:column>
                 <h:column>
                     <f:facet name="header">
                    	<h:outputLabel value="City" />
                    </f:facet>
                    <h:outputText value="#{r.city}"/>
                </h:column>
                    <h:column>
                     <f:facet name="header">
                    	<h:outputLabel value="Rating" />
                    </f:facet>
                    <h:outputText value="#{r.rating}"/>
                </h:column>
                    <h:column>
                     <f:facet name="header">
                    	<h:outputLabel value="Food Type" />
                    </f:facet>
                    <h:outputText value="#{r.foodType}"/>
                </h:column>
                 <h:column>
                     <f:facet name="header">
                    	<h:outputLabel value="Show menu" />
                    </f:facet>
                    <h:commandButton action="#{employController.searchMenu(r.restaurantId)}" 
                    		value="Show Menu" />
                </h:column>
        </h:dataTable>
        </h:form>
    </body>
</html>
</f:view>