
    function cerrarsesion() {
        // Eliminar cualquier dato relacionado con la sesión del usuario almacenado en localStorage o sessionStorage
        localStorage.clear();
        sessionStorage.clear();

        // Redirigir al usuario a la página de inicio de sesión
        window.location.href = "../index.html";
    }

    // Vincular la función al botón de cerrar sesión
    document.querySelector('.btn-cerrar-sesion').addEventListener('click', cerrarsesion);

 function alertar() {
            // Mostrar una alerta con SweetAlert
            Swal.fire({
                title: '¿Deseas imprimir el ticket?',
                icon: 'question',
                showCancelButton: true, // Mostrar el botón de cancelar
                confirmButtonText: 'Sí',
                cancelButtonText: 'No',
                confirmButtonColor: '#007bff',
                cancelButtonColor: '#d33'
            }).then((result) => {
                if (result.isConfirmed) {
                    // Acción si el usuario elige "Sí"
                    console.log("El usuario desea imprimir el ticket.");
                    // Aquí podrías agregar la lógica para imprimir el ticket
                    imprimirTicket();
                } else {
                    // Acción si el usuario elige "No"
                    console.log("El usuario no desea imprimir el ticket.");
                }
            });
        }

        function imprimirTicket() {
            // Lógica para imprimir el ticket (por ejemplo, generar PDF o redirigir a una impresión)
            alert("Ticket enviado a la impresora.");
        }