document.addEventListener("DOMContentLoaded", function () {

    if (typeof AOS !== "undefined") {
        AOS.init({
            duration: 900,
            once: true,
            offset: 80
        });
    }

    const counters = document.querySelectorAll(".counter");
    let counterStarted = false;

    function startCounters() {
        counters.forEach(counter => {
            const target = Number(counter.getAttribute("data-target"));
            let count = 0;
            const speed = Math.max(1, Math.floor(target / 80));

            const updateCounter = () => {
                count += speed;

                if (count < target) {
                    counter.innerText = count + "+";	
                    requestAnimationFrame(updateCounter);
                } else {
                    counter.innerText = target + (target === 98 ? "%" : "+");
                }
            };

            updateCounter();
        });
    }

    function checkCounterPosition() {
        const statsSection = document.querySelector(".stats-section");

        if (!statsSection || counterStarted) return;

        const position = statsSection.getBoundingClientRect().top;
        const screenHeight = window.innerHeight;

        if (position < screenHeight - 100) {
            counterStarted = true;
            startCounters();
        }
    }

    window.addEventListener("scroll", checkCounterPosition);
    checkCounterPosition();

    document.querySelectorAll('a[href^="#"]').forEach(anchor => {
        anchor.addEventListener("click", function (e) {
            const target = document.querySelector(this.getAttribute("href"));

            if (target) {
                e.preventDefault();
                target.scrollIntoView({
                    behavior: "smooth",
                    block: "start"
                });
            }
        });
    });

});