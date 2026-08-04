document.addEventListener("DOMContentLoaded", function () {

    if (typeof AOS !== "undefined") {
        AOS.init({
            duration: 900,
            once: true,
            offset: 80,
            easing: "ease-in-out"
        });
    }

    document.querySelectorAll(".btn").forEach(function (button) {

        button.addEventListener("click", function (e) {

            const ripple = document.createElement("span");
            ripple.classList.add("ripple");

            const rect = button.getBoundingClientRect();
            const size = Math.max(rect.width, rect.height);

            ripple.style.width = size + "px";
            ripple.style.height = size + "px";
            ripple.style.left = e.clientX - rect.left - size / 2 + "px";
            ripple.style.top = e.clientY - rect.top - size / 2 + "px";

            button.appendChild(ripple);

            setTimeout(function () {
                ripple.remove();
            }, 600);
        });

    });

});

function toggleRegisterPassword() {
    const pass = document.getElementById("candidatePass");

    if (pass.type === "password") {
        pass.type = "text";
    } else {
        pass.type = "password";
    }
}