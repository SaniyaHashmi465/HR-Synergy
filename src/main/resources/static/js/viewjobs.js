/**
 * HR Synergy – viewjobs.js
 * DataTable initialization + client-side statistics
 */
document.addEventListener('DOMContentLoaded', function () {

    // ── DataTable Initialization ──────────────────────────────────
    const table = $('#jobTable').DataTable({
        pageLength: 10,
        language: {
            search: '',
            searchPlaceholder: 'Search jobs…',
            lengthMenu: 'Show _MENU_ entries',
            info: 'Showing _START_ to _END_ of _TOTAL_ entries',
            infoEmpty: 'No records found',
            zeroRecords: 'No matching jobs found',
            paginate: {
                previous: '<i class="fa-solid fa-chevron-left"></i>',
                next:     '<i class="fa-solid fa-chevron-right"></i>'
            }
        },
        // Disable sorting on Action column (last)
        columnDefs: [
            { orderable: false, targets: -1 }
        ],
        responsive: false,
        dom: "<'row align-items-center mb-3'<'col-sm-auto'l><'col-sm'f>>" +
             "<'row'<'col-12'tr>>" +
             "<'row align-items-center mt-3'<'col-sm-5'i><'col-sm-7 text-end'p>>"
    });

    // ── Client-side Statistics ────────────────────────────────────
    const totalRows = table.rows().count();

    // Records badge in table header
    const badge = document.getElementById('jmRecordsBadge');
    if (badge) badge.textContent = totalRows + ' Record' + (totalRows !== 1 ? 's' : '');

    // Active Jobs — treat all as active (static approximation)
    const activeEl = document.querySelector('.jm-client-active');
    if (activeEl) activeEl.textContent = totalRows;

    // Open Vacancies — sum column index 2 (noofvacancy)
    let totalVacancies = 0;
    table.rows().every(function () {
        const cellText = this.data()[2];
        const val = parseInt(String(cellText).replace(/\D/g, ''), 10);
        if (!isNaN(val)) totalVacancies += val;
    });
    const vacanciesEl = document.querySelector('.jm-client-vacancies');
    if (vacanciesEl) vacanciesEl.textContent = totalVacancies;

    // Recruitment Pipeline — jobs posted in last 30 days (column index 5: posteddate)
    const thirtyDaysAgo = new Date();
    thirtyDaysAgo.setDate(thirtyDaysAgo.getDate() - 30);
    let pipelineCount = 0;
    table.rows().every(function () {
        const rawDate = String(this.data()[5]).trim();
        if (rawDate) {
            const d = new Date(rawDate);
            if (!isNaN(d) && d >= thirtyDaysAgo) pipelineCount++;
        }
    });
    const pipelineEl = document.querySelector('.jm-client-pipeline');
    if (pipelineEl) pipelineEl.textContent = pipelineCount;

    // ── Row Stagger Animation ─────────────────────────────────────
    function animateRows() {
        document.querySelectorAll('#jobTable tbody tr').forEach(function (row, i) {
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

    // ── Auto-dismiss success alert after 5s ───────────────────────
    const alert = document.querySelector('.jm-success-alert');
    if (alert) {
        setTimeout(function () {
            alert.style.transition = 'opacity .5s ease';
            alert.style.opacity = '0';
            setTimeout(function () { alert.remove(); }, 520);
        }, 5000);
    }
});