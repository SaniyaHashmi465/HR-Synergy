/* ================================================ */
/* myleaves.js                                        */
/* DataTable initialization + client-side stat counts*/
/* No backend calls — reads only what Thymeleaf has   */
/* already rendered into the #leaveTable DOM.         */
/* ================================================ */

$(document).ready(function () {

    /* ---------- 1. Initialize DataTable ---------- */
    $('#leaveTable').DataTable({
        order: [[0, 'desc']],
        pageLength: 10,
        lengthMenu: [[10, 25, 50, -1], [10, 25, 50, "All"]],
        language: {
            search: "",
            searchPlaceholder: "Search leaves...",
            lengthMenu: "Show _MENU_ entries",
            info: "Showing _START_ to _END_ of _TOTAL_ leave requests",
            infoEmpty: "No leave requests found",
            infoFiltered: "(filtered from _MAX_ total leave requests)",
            paginate: {
                previous: "<i class='fa-solid fa-chevron-left'></i>",
                next: "<i class='fa-solid fa-chevron-right'></i>"
            },
            emptyTable: "You have not applied for any leave yet."
        }
    });

    /* ---------- 2. Compute stats from rendered rows ---------- */
    // These counts come purely from the status badges already
    // rendered by the server (column index 5), so no new backend
    // model attribute or controller logic is required.
    var total = $('#leaveTable tbody tr').length;
    var pending = $('#leaveTable tbody .badge-pending').length;
    var approved = $('#leaveTable tbody .badge-approved').length;
    var rejected = $('#leaveTable tbody .badge-rejected').length;

    animateCount('statTotal', total);
    animateCount('statPending', pending);
    animateCount('statApproved', approved);
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