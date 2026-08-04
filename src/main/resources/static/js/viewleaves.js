/**
 * HR Synergy – viewleaves.js
 * DataTable initialization + UI enhancements
 */
document.addEventListener('DOMContentLoaded', function () {

    // ── DataTable Initialization ──────────────────────────────────
    const table = $('#leaveTable').DataTable({
        pageLength: 10,
        language: {
            search: '',
            searchPlaceholder: 'Search leaves…',
            lengthMenu: 'Show _MENU_ entries',
            info: 'Showing _START_ to _END_ of _TOTAL_ entries',
            infoEmpty: 'No records found',
            zeroRecords: 'No matching leave requests found',
            paginate: {
                previous: '<i class="fa-solid fa-chevron-left"></i>',
                next:     '<i class="fa-solid fa-chevron-right"></i>'
            }
        },
        columnDefs: [
            { orderable: false, targets: [8] }   // Action column not sortable
        ],
        responsive: false,
        dom: "<'row align-items-center mb-3'<'col-sm-auto'l><'col-sm'f>>" +
             "<'row'<'col-12'tr>>" +
             "<'row align-items-center mt-3'<'col-sm-5'i><'col-sm-7 text-end'p>>"
    });

    // ── Records Badge ─────────────────────────────────────────────
    const totalRows = table.rows().count();
    const badge = document.getElementById('lmRecordsBadge');
    if (badge) badge.textContent = totalRows + ' Record' + (totalRows !== 1 ? 's' : '');

    // ── Row Stagger Animation ─────────────────────────────────────
    function animateRows() {
        document.querySelectorAll('#leaveTable tbody tr').forEach(function (row, i) {
            row.style.opacity = '0';
            row.style.transform = 'translateY(10px)';
            row.style.transition =
                'opacity .25s ease ' + (i * 0.045) + 's, ' +
                'transform .25s ease ' + (i * 0.045) + 's';
            requestAnimationFrame(function () {
                row.style.opacity = '1';
                row.style.transform = 'translateY(0)';
            });
        });
    }

    animateRows();
    table.on('draw', animateRows);

    // ── Reason Tooltips ───────────────────────────────────────────
    document.querySelectorAll('.lm-reason-truncate').forEach(function (el) {
        const full = el.getAttribute('title') || el.textContent;
        if (typeof bootstrap !== 'undefined' && bootstrap.Tooltip) {
            new bootstrap.Tooltip(el, {
                title: full,
                placement: 'top',
                trigger: 'hover focus'
            });
        }
    });

    // ── Auto-dismiss Success Alert after 5 s ──────────────────────
    const alert = document.querySelector('.lm-success-alert');
    if (alert) {
        setTimeout(function () {
            alert.style.transition = 'opacity .5s ease';
            alert.style.opacity = '0';
            setTimeout(function () { alert.remove(); }, 520);
        }, 5000);
    }
});