document.addEventListener("DOMContentLoaded", function () {

    const fadeElements = document.querySelectorAll(".fade-up");

    function showOnScroll() {
        fadeElements.forEach(function (element) {
            const elementTop = element.getBoundingClientRect().top;
            const windowHeight = window.innerHeight;

            if (elementTop < windowHeight - 80) {
                element.classList.add("show");
            }
        });
    }

    window.addEventListener("scroll", showOnScroll);
    showOnScroll();

});