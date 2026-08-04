// HR Synergy — Enquiry Page interactions

document.addEventListener('DOMContentLoaded', function () {
    // Auto fade-out the success alert after a few seconds
    var successAlert = document.querySelector('.hs-alert-success');
    if (successAlert) {
        setTimeout(function () {
            successAlert.style.transition = 'opacity .6s ease, transform .6s ease';
            successAlert.style.opacity = '0';
            successAlert.style.transform = 'translateY(-8px)';
        }, 4500);
    }
});