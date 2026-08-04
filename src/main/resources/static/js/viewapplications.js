/* ================================================ */
/* viewapplications.js                                */
/* DataTable initialization + client-side stat counts*/
/* No backend calls — reads only what Thymeleaf has   */
/* already rendered into the #applicationTable DOM.   */
/* ================================================ */

$(document).ready(function () {

    /* ---------- 1. Initialize DataTable ---------- */
    $('#applicationTable').DataTable({
        order: [[0, 'desc']],
        pageLength: 10,
        lengthMenu: [[10, 25, 50, -1], [10, 25, 50, "All"]],
        language: {
            search: "",
            searchPlaceholder: "Search applications...",
            lengthMenu: "Show _MENU_ entries",
            info: "Showing _START_ to _END_ of _TOTAL_ applications",
            infoEmpty: "No applications found",
            infoFiltered: "(filtered from _MAX_ total applications)",
            paginate: {
                previous: "<i class='fa-solid fa-chevron-left'></i>",
                next: "<i class='fa-solid fa-chevron-right'></i>"
            },
            emptyTable: "No applications available."
        },
        columnDefs: [
            { orderable: false, targets: -1 } // Action column not sortable
        ]
    });

    /* ---------- 2. Compute stats from rendered rows ---------- */
    // These counts come purely from the status badges already
    // rendered by the server (column index 6), so no new backend
    // model attribute or controller logic is required.
    var total = $('#applicationTable tbody tr').length;
    var applied = $('#applicationTable tbody .badge-applied').length;
    var shortlisted = $('#applicationTable tbody .badge-shortlisted').length;
    var hired = $('#applicationTable tbody .badge-hired').length;
    var rejected = $('#applicationTable tbody .badge-rejected').length;

    animateCount('statTotal', total);
    animateCount('statApplied', applied);
    animateCount('statShortlisted', shortlisted);
    animateCount('statHired', hired);
    animateCount('statRejected', rejected);

    /* ---------- 3. Animated counter helper ---------- */
    function animateCount(elementId, target) {
        var el = document.getElementById(elementId);
        if (!el) return;

        if (target === 0) {
            el.textContent = "0";
            return;
        }

        var current = 0;
        var duration = 800;
        var stepTime = Math.max(Math.floor(duration / target), 16);

        var timer = setInterval(function () {
            current += Math.ceil(target / (duration / stepTime));
            if (current >= target) {
                current = target;
                clearInterval(timer);
            }
            el.textContent = current;
        }, stepTime);
    }

});