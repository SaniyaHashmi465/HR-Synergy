/* ================================================ */
/* candidatedashboard.js                              */
/* Sidebar off-canvas toggle, overlay, mobile UX      */
/* Pure client-side — no backend calls                */
/* ================================================ */

(function () {
    'use strict';

    /* ---------- 1. DOM refs ---------- */
    var sidebar  = document.getElementById('cdSidebar');
    var overlay  = document.getElementById('cdOverlay');
    var toggleBtn = document.getElementById('cdToggleBtn');

    if (!sidebar || !overlay || !toggleBtn) return;

    /* ---------- 2. Open / close sidebar ---------- */
    function openSidebar() {
        sidebar.classList.add('open');
        overlay.classList.add('active');
        document.body.style.overflow = 'hidden'; // prevent background scroll on mobile
    }

    function closeSidebar() {
        sidebar.classList.remove('open');
        overlay.classList.remove('active');
        document.body.style.overflow = '';
    }

    toggleBtn.addEventListener('click', function () {
        if (sidebar.classList.contains('open')) {
            closeSidebar();
        } else {
            openSidebar();
        }
    });

    overlay.addEventListener('click', closeSidebar);

    /* ---------- 3. Auto-close sidebar on resize to desktop ---------- */
    window.addEventListener('resize', function () {
        if (window.innerWidth > 992) {
            closeSidebar(); // tidy up classes/overflow when returning to desktop
        }
    });

    /* ---------- 4. Active nav-link highlighting ---------- */
    // Marks the sidebar link whose href matches the current URL path.
    // Falls back to the hard-coded "active" class on Dashboard if
    // no link matches (since Dashboard is the current page).
    var currentPath = window.location.pathname;

    document.querySelectorAll('.cd-nav-link').forEach(function (link) {
        var linkPath = link.getAttribute('href');
        if (linkPath && linkPath !== '#' && currentPath.includes(linkPath)) {
            // Remove the static active class from Dashboard
            document.querySelectorAll('.cd-nav-link').forEach(function (l) {
                l.classList.remove('active');
            });
            link.classList.add('active');
        }
    });

    /* ---------- 5. Staggered fade-in for cards ---------- */
    // Applies a small delay multiplier to each .fade-in-up card
    // so they appear to cascade in from the top-left.
    // The CSS already handles the animation; this just lets us
    // add an IntersectionObserver for cards below the fold.
    if ('IntersectionObserver' in window) {
        var observerOptions = {
            threshold: 0.1,
            rootMargin: '0px 0px -30px 0px'
        };

        var observer = new IntersectionObserver(function (entries) {
            entries.forEach(function (entry) {
                if (entry.isIntersecting) {
                    entry.target.style.animationPlayState = 'running';
                    observer.unobserve(entry.target);
                }
            });
        }, observerOptions);

        document.querySelectorAll('.fade-in-up').forEach(function (el) {
            // Pause the animation until the element scrolls into view
            el.style.animationPlayState = 'paused';
            observer.observe(el);
        });
    }

})();