document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("loginForm").addEventListener("submit", function (event) {
        event.preventDefault(); // Previene el comportamiento por defecto del formulario

        const userValue = document.getElementById('txtUser').value.trim(); // Obtener el valor del usuario
        const passValue = document.getElementById('txtPassword').value.trim(); // Obtener el valor de la contraseña
        const userInput = document.getElementById('txtUser');
        const passInput = document.getElementById('txtPassword');

        // Limpiar las clases de error
        userInput.classList.remove('is-invalid');
        passInput.classList.remove('is-invalid');

        let isValid = true;

        // Validación de campos
        if (userValue === '') {
            userInput.classList.add('is-invalid');
            userInput.nextElementSibling.textContent = 'Por favor, ingrese su usuario.'; // Mensaje de error
            isValid = false;
        }
        if (passValue === '') {
            passInput.classList.add('is-invalid');
            passInput.nextElementSibling.textContent = 'Por favor, ingrese su contraseña.'; // Mensaje de error
            isValid = false;
        }

        if (!isValid) {
            return; // Detener si hay errores de validación
        }

        // Hacer la solicitud fetch al backend
        fetch('http://localhost:8080/ProyectoGalleto/api/login/consumes', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                username: userValue,  // Asegúrate de que el nombre del campo sea "username" en el backend
                password: passValue   // Asegúrate de que el nombre del campo sea "password" en el backend
            }),
        })
        .then(response => {
            if (response.ok) {
                return response.json();  // Si la respuesta es exitosa, parsea el JSON
            } else {
                throw new Error(`Error en la solicitud: ${response.statusText}`);
            }
        })
        .then(data => {
            console.log('Respuesta recibida:', data);  // Verifica si "data" tiene el formato correcto
            if (data && data.lastToken) {
                // Si la respuesta contiene un "lastToken", almacénalo en el localStorage
                localStorage.setItem("lastToken", data.lastToken);
                location.href = './Paginaprincipal/index.html'; // Redirigir a la página principal
            } else {
                // Si no se encuentra el token, muestra un mensaje de error
                Swal.fire({
                    text: 'Usuario no encontrado o credenciales incorrectas.',
                    icon: 'info',
                    confirmButtonText: 'Intentar de nuevo',
                }).then(result => {
                    if (result.isConfirmed) {
                        // Limpiar los campos si el usuario intenta de nuevo
                        userInput.value = '';
                        passInput.value = '';
                    }
                });
            }
        })
        .catch(error => {
            console.error('Error en la solicitud:', error);
            Swal.fire({
                text: 'Hubo un error al intentar iniciar sesión. Por favor, intente más tarde.',
                icon: 'error',
                confirmButtonText: 'Aceptar',
            });
        });
    });
});
