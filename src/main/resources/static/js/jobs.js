// HR Synergy — Careers / Jobs page interactions

document.addEventListener('DOMContentLoaded', function () {

    // ---------------------------------------------------------------
    // AOS (Animate On Scroll) initialization
    // ---------------------------------------------------------------
    if (typeof AOS !== 'undefined') {
        AOS.init({
            duration: 700,
            once: true,
            offset: 60,
            easing: 'ease-out-cubic'
        });
    }

    // ---------------------------------------------------------------
    // Smooth scroll for in-page anchor links (e.g. "View Open Positions")
    // ---------------------------------------------------------------
    document.querySelectorAll('a[href^="#"]').forEach(function (link) {
        link.addEventListener('click', function (e) {
            var targetId = link.getAttribute('href');
            if (targetId.length > 1) {
                var target = document.querySelector(targetId);
                if (target) {
                    e.preventDefault();
                    target.scrollIntoView({ behavior: 'smooth', block: 'start' });
                }
            }
        });
    });

    // ---------------------------------------------------------------
    // Button ripple effect — applies to all .jobs-btn and .job-apply-btn
    // ---------------------------------------------------------------
    function attachRipple(selector) {
        document.querySelectorAll(selector).forEach(function (btn) {
            btn.addEventListener('click', function (e) {
                var rect = btn.getBoundingClientRect();
                var ripple = document.createElement('span');
                var size = Math.max(rect.width, rect.height) * 2;

                ripple.className = 'jobs-ripple';
                ripple.style.width = ripple.style.height = size + 'px';
                ripple.style.left = (e.clientX - rect.left - size / 2) + 'px';
                ripple.style.top = (e.clientY - rect.top - size / 2) + 'px';

                btn.appendChild(ripple);
                setTimeout(function () {
                    ripple.remove();
                }, 650);
            });
        });
    }
    attachRipple('.jobs-btn');
    attachRipple('.job-apply-btn');

});