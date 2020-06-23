
function validatie(invoer){
    const msg = invoer.name + " ongeldig";
    invoer.setCustomValidity(msg);
}
function valid_postcode(invoer) {
    const msg = invoer.name + " ongeldig, een postcode moet 4 cijfers gevolgd door 2 letters bevatten!";
    invoer.setCustomValidity(msg);
}
function valid_password(invoer) {
    const msg = "Een " + invoer.name + " moet minimaal één cijfer, één hoofdletter,een kleine letter en 8 tekens bevatten!";
    invoer.setCustomValidity(msg);
}
function validatePassword() {
    let ww = document.getElementById("password").value;
    let ww_confirm = document.getElementById("confirm_password").value;
    if (ww !== ww_confirm) {
        document.getElementById("confirm_password").setCustomValidity("Wachtwoord komt niet overeen");
        return false;
    } else {
        document.getElementById("confirm_password").setCustomValidity('');
        return true;
    }
}

