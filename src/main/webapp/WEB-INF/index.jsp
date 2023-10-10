<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="csslogin/estilos.css">
</head>
<body>

    <div class="container">
        <div class="form-box">
            <div class="button-box">
                <div id="elegir"></div>
                <button type="button" class="toggle-btn" onclick="login()">Iniciar Sesion </button>
                <button type="button" class="toggle-btn" onclick="registrar()">Registrar </button>
            </div>
            <div class="redes-sociales"> 
                 <img src="imglogin/gp.png" alt="icono_Googleplus">           
            </div>
           <form action="/login" method="post" class="input-group" id="login">
           
                     <div>
						
						<input type="email" class="input-field" name="email" placeholder="Correo">
						
					</div>
					<div>
					
						<input type="password" class="input-field" name="password" placeholder="Contrase�a">
					</div>
                <button type="submit" class="submit-btn" >Acceder</button>
            </form>
            <form:form method="Post" modelAttribute="nuevoUsuario" action="/registro" class="input-group" id="registrar">
            
           
				<form:input path="nombre" class="input-field" placeholder="Nombre de Usuario"/>
				<form:errors path="nombre" class="text-danger"/>
				
				
				<form:input path="apellido" class="input-field" placeholder="Apellido"/>
				<form:errors path="apellido" class="text-danger"/>
				
				
                
             
				<form:input path="email" class="input-field" placeholder="Correo"/>
				<form:errors path="email" class="text-danger"/>
                
                
           
				<form:password path="password" class="input-field" placeholder="Contrase�a" />
				<form:errors path="password" class="text-danger"/>
                
                
               
				<form:password path="confirmPassword" class="input-field" placeholder="Confirmar Contrase�a"/>
				<form:errors path="confirmPassword" class="text-danger"/>
                
                
                <input type="checkbox" class="check-box"><span>Acepto los t�rminos y Condiciones.</span>
                <button type="submit" class="submit-btn" >Registrar</button>
            </form:form>
        </div>
    </div>

    <script>
        var x = document.getElementById("login");
        var y = document.getElementById("registrar");
        var z = document.getElementById("elegir");

        function login(){
            x.style.left = "50px";
            y.style.left = "450px";
            z.style.left = "0px";
        }

        function registrar(){
            x.style.left = "-400px";
            y.style.left = "50px";
            z.style.left = "120px"; 
        }


    </script>
</body>
</html>