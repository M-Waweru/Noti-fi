$(document).ready(function () {
    $('.datepicker').datepicker();
    var elems = document.querySelectorAll('.timepicker');
    var options = {"twelveHour": false};
    var instances = M.Timepicker.init(elems, options);
});
