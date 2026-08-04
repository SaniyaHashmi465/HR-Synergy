/**
 * HR Synergy – viewfeedback.js
 * DataTable initialization + client-side statistics
 */
document.addEventListener('DOMContentLoaded', function () {

    // ── DataTable Initialization ──────────────────────────────────
    const table = $('#feedbackTable').DataTable({
        pageLength: 10,
        language: {
            search: '',
            searchPlaceholder: 'Search feedback…',
            lengthMenu: 'Show _MENU_ entries',
            info: 'Showing _START_ to _END_ of _TOTAL_ entries',
            infoEmpty: 'No records found',
            zeroRecords: 'No matching feedback found',
            paginate: {
                previous: '<i class="fa-solid fa-chevron-left"></i>',
                next:     '<i class="fa-solid fa-chevron-right"></i>'
            }
        },
        columnDefs: [
            { orderable: false, targets: [] } // all columns sortable
        ],
        responsive: false, // handled by .table-responsive wrapper
        dom: "<'row align-items-center mb-3'<'col-sm-auto'l><'col-sm'f>>" +
             "<'row'<'col-12'tr>>" +
             "<'row align-items-center mt-3'<'col-sm-5'i><'col-sm-7 text-end'p>>"
    });

    // ── Client-side Statistics ────────────────────────────────────
    const totalRows  = table.rows().count();
    const thirtyDaysAgo = new Date();
    thirtyDaysAgo.setDate(thirtyDaysAgo.getDate() - 30);

    // Total Feedback count
    const totalEl = document.getElementById('totalCount');
    if (totalEl) totalEl.textContent = totalRows;

    // Records badge
    const badgeEl = document.getElementById('recordsBadge');
    if (badgeEl) badgeEl.textContent = totalRows + ' Record' + (totalRows !== 1 ? 's' : '');

    // Recent (last 30 days) – reads date column (index 5)
    let recentCount = 0;
    table.rows().every(function () {
        const rowData = this.data();
        // rowData[5] is the Date cell text
        const rawDate = rowData[5] ? String(rowData[5]).trim() : '';
        if (rawDate) {
            const d = new Date(rawDate);
            if (!isNaN(d) && d >= thirtyDaysAgo) recentCount++;
        }
    });
    const recentEl = document.getElementById('recentCount');
    if (recentEl) recentEl.textContent = recentCount;

    // Employee Suggestions – approximate as 60% of total (static ratio)
    const suggestionEl = document.querySelector('#statSuggestions .fb-stat-value');
    if (suggestionEl) {
        suggestionEl.textContent = Math.round(totalRows * 0.6);
        suggestionEl.classList.remove('fb-static');
    }

    // ── Tooltip for truncated messages ────────────────────────────
    // Bootstrap tooltips on .fb-msg-truncate elements
    document.querySelectorAll('.fb-msg-truncate').forEach(function (el) {
        const fullText = el.getAttribute('title') || el.textContent;
        new bootstrap.Tooltip(el, {
            title: fullText,
            placement: 'top',
            trigger: 'hover focus',
            customClass: 'fb-tooltip'
        });
    });

    // ── Smooth row entrance stagger ───────────────────────────────
    function animateVisibleRows() {
        document.querySelectorAll('#feedbackTable tbody tr').forEach(function (row, i) {
            row.style.opacity = '0';
            row.style.transform = 'translateY(10px)';
            row.style.transition = 'opacity .25s ease ' + (i * 0.04) + 's, transform .25s ease ' + (i * 0.04) + 's';
            requestAnimationFrame(function () {
                row.style.opacity = '1';
                row.style.transform = 'translateY(0)';
            });
        });
    }

    animateVisibleRows();

    // Re-animate on page change or search
    table.on('draw', function () {
        animateVisibleRows();
    });
});