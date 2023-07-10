'use strict'

function switchForm(selectedForm) {
    var memberForm = document.getElementById('client-login');
    var adminForm = document.getElementById('manager-login');
    if (selectedForm === 'member') {
        memberForm.style.display = 'block';
        adminForm.style.display = 'none';
    } else if (selectedForm === 'admin') {
        memberForm.style.display = 'none';
        adminForm.style.display = 'block';
    }
}