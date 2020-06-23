function validatie(invoer){
    var msg = invoer.name +" ongeldig"
    invoer.setCustomValidity(msg);
}
function valid_rekeningnummer(invoer) {
    var msg = "Rekeningnummer ongeldig, een rekeningnummer moet uit NL35IBVH gevolgd door tien cijfers bestaan."
    invoer.setCustomValidity(msg);
}

function valid_amount(invoer){
    var msg = "Voer een bedrag in."
    invoer.setCustomValidity(msg);
}