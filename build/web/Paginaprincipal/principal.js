
    function cerrarsesion() {
        // Eliminar cualquier dato relacionado con la sesión del usuario almacenado en localStorage o sessionStorage
        localStorage.clear();
        sessionStorage.clear();

        // Redirigir al usuario a la página de inicio de sesión
        window.location.href = "../index.html";
    }

    // Vincular la función al botón de cerrar sesión
    document.querySelector('.btn-cerrar-sesion').addEventListener('click', cerrarsesion);
