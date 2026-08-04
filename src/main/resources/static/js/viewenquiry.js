/* ================================================ */
/* viewenquiry.js                                    */
/* DataTable initialization + animated stat counters */
/* No backend calls — reads only what Thymeleaf has   */
/* already rendered into the #enquiryTable DOM.       */
/* ================================================ */

$(document).ready(function () {

    /* ---------- 1. Initialize DataTable ---------- */
    var enquiryTable = $('#enquiryTable').DataTable({
        responsive: true,
        order: [[0, 'desc']],
        lengthMenu: [[10, 25, 50, -1], [10, 25, 50, "All"]],
        pageLength: 10,
        language: {
            search: "",
            searchPlaceholder: "Search enquiries...",
            lengthMenu: "Show _MENU_ entries",
            info: "Showing _START_ to _END_ of _TOTAL_ enquiries",
            infoEmpty: "No enquiries found",
            infoFiltered: "(filtered from _MAX_ total enquiries)",
            paginate: {
                previous: "<i class='fa-solid fa-chevron-left'></i>",
                next: "<i class='fa-solid fa-chevron-right'></i>"
            },
            emptyTable: "No enquiries available."
        },
        columnDefs: [
            { orderable: false, targets: -1 } // Actions column not sortable
        ]
    });

    /* ---------- 2. Compute stats from rendered rows ---------- */
    // These counts are derived purely from what the server already
    // rendered (status badges in column index 7), so no model
    // attribute or controller logic is touched.
    var total = $('#enquiryTable tbody tr').length;
    var pending = $('#enquiryTable tbody .badge-pending').length;
    var resolved = $('#enquiryTable tbody .badge-resolved').length;

    // "Support Requests" mirrors total enquiry volume, since every
    // enquiry routed through this page is, by definition, a support
    // request. If your data model later distinguishes a separate
    // "support" category, swap this value for that count.
    var support = total;

    animateCount('statTotal', total);
    animateCount('statPending', pending);
    animateCount('statResolved', resolved);
    animateCount('statSupport', support);

    /* ---------- 3. Animated counter helper ---------- */
    function animateCount(elementId, target) {
        var el = document.getElementById(elementId);
        if (!el) return;

        var current = 0;
        var duration = 900; // ms
        var stepTime = Math.max(Math.floor(duration / Math.max(target, 1)), 16);

        if (target === 0) {
            el.textContent = "0";
            return;
        }

        var timer = setInterval(function () {
            current += Math.ceil(target / (duration / stepTime));
            if (current >= target) {
                current = target;
                clearInterval(timer);
            }
            el.textContent = current;
        }, stepTime);
    }

    /* ---------- 4. Auto fade-out success alert ---------- */
    var successAlert = document.getElementById('enquirySuccessAlert');
    if (successAlert) {
        setTimeout(function () {
            successAlert.style.display = 'none';
        }, 5000);
    }

});