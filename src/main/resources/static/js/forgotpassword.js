// HR Synergy — Forgot Password page interactions

document.addEventListener('DOMContentLoaded', function () {

    // Password show/hide toggle
    var toggleBtn = document.querySelector('.hs-toggle-pass');
    var passInput = document.getElementById('newPassword');

    if (toggleBtn && passInput) {
        toggleBtn.addEventListener('click', function () {
            var isHidden = passInput.getAttribute('type') === 'password';
            passInput.setAttribute('type', isHidden ? 'text' : 'password');

            var icon = toggleBtn.querySelector('i');
            if (icon) {
                icon.classList.toggle('fa-eye', !isHidden);
                icon.classList.toggle('fa-eye-slash', isHidden);
            }
            toggleBtn.setAttribute('aria-label', isHidden ? 'Hide password' : 'Show password');
        });
    }

    // Auto fade-out the success alert after a few seconds
    var successAlert = document.querySelector('.hs-alert-success');
    if (successAlert) {
        setTimeout(function () {
            successAlert.style.transition = 'opacity .6s ease, transform .6s ease';
            successAlert.style.opacity = '0';
            successAlert.style.transform = 'translateY(-8px)';
        }, 5000);
    }
});