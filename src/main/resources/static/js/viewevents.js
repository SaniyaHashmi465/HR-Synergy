/* ================================================ */
/* viewevents.js                                      */
/* DataTable initialization + client-side stat counts*/
/* No backend calls — reads only what Thymeleaf has   */
/* already rendered into the #eventTable DOM.         */
/* ================================================ */

$(document).ready(function () {

    /* ---------- 1. Initialize DataTable ---------- */
    $('#eventTable').DataTable({
        order: [[0, 'desc']],
        pageLength: 10,
        lengthMenu: [[10, 25, 50, -1], [10, 25, 50, "All"]],
        language: {
            search: "",
            searchPlaceholder: "Search events...",
            lengthMenu: "Show _MENU_ entries",
            info: "Showing _START_ to _END_ of _TOTAL_ events",
            infoEmpty: "No events found",
            infoFiltered: "(filtered from _MAX_ total events)",
            paginate: {
                previous: "<i class='fa-solid fa-chevron-left'></i>",
                next: "<i class='fa-solid fa-chevron-right'></i>"
            },
            emptyTable: "No events have been added yet."
        },
        columnDefs: [
            { orderable: false, targets: -1 } // Action column not sortable
        ]
    });

    /* ---------- 2. Compute stats from rendered rows ---------- */
    // "Upcoming Events" counts rows whose Event Date (column index 3)
    // is today or later. "Published Events" simply mirrors the total
    // row count, since every event rendered here has already been
    // posted — there is no draft/unpublished state in the data model.
    // No new backend model attribute is introduced.
    var rows = $('#eventTable tbody tr');
    var total = rows.length;
    var upcoming = 0;

    var today = new Date();
    today.setHours(0, 0, 0, 0);

    rows.each(function () {
        var dateText = $(this).find('td').eq(3).text().trim();
        var parsed = new Date(dateText);

        if (!isNaN(parsed.getTime()) && parsed >= today) {
            upcoming++;
        }
    });

    animateCount('statUpcoming', upcoming);
    animateCount('statPublished', total);

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