const body = document.querySelector("body");
const sidebar = document.querySelector(".sidebar");
const toggle = document.querySelector(".toggle");
const searchBtn = document.querySelector(".search-box");
const modeSwitch = document.querySelector(".toggle-switch");
const modeText = document.querySelector(".mode-text");

// Función para habilitar el modo oscuro
function enableDarkMode() {
    body.classList.add("dark");
    modeText.innerText = "Light Mode";
    localStorage.setItem('darkModeEnabled', 'true');
    updateFooterBackground()
}

// Función para deshabilitar el modo oscuro
function disableDarkMode() {
    body.classList.remove("dark");
    modeText.innerText = "Dark Mode";
    localStorage.setItem('darkModeEnabled', 'false');
    updateFooterBackground()
}

// Función para actualizar el color de fondo del footer
function updateFooterBackground() {
    const footer = document.querySelector('.footer');
    if (body.classList.contains('dark')) {
        footer.style.backgroundColor = 'var(--footer-background-dark)';
    } else {
        footer.style.backgroundColor = 'var(--footer-background-light)';
    }
}


// Función para cargar el estado del modo oscuro al cargar la página
function loadDarkModeState() {
    const darkModeEnabled = localStorage.getItem('darkModeEnabled');
    if (darkModeEnabled === 'true') {
        enableDarkMode();
    }
    updateFooterBackground()
}

// Toggle para abrir y cerrar la barra lateral
toggle.addEventListener("click", () => {
    sidebar.classList.toggle("close");
});

// Botón de búsqueda
searchBtn.addEventListener("click", () => {
    sidebar.classList.remove("close");
});

// Toggle para cambiar entre modos oscuro y claro
modeSwitch.addEventListener("click", () => {
    if (body.classList.contains("dark")) {
        disableDarkMode();
    } else {
        enableDarkMode();
    }
});

document.addEventListener("click", function (event) {
    if (event.target.classList.contains("custom-checkbox")) {
        event.target.classList.toggle("checked");
    } else if (event.target.classList.contains("custom-list")) {
        event.target.classList.toggle("checked");
    }
});

// Llama a la función para cargar el estado del modo oscuro al cargar la página
loadDarkModeState();








