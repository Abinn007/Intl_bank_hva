function validateRekeningnummer(invoer) {
    var msg = "Rekeningnummer ongeldig, een rekeningnummer moet uit NL35IBVH gevolgd door tien cijfers bestaan."
    invoer.setCustomValidity(msg);
}

function validateAmountCharacters(invoer){
    var bericht = document.getElementsByName("bericht").length;
    if (bericht > 150){
        var msg = "Het bericht mag niet meer dan 150 karakters lang zijn."
        invoer.setCustomValidity(msg);
    }
}

function setTwoNumberDecimal(event) {
    this.value = parseFloat(this.value).toFixed(2);
}

function validateBedrag(invoer) {
    var msg;

    if (isNaN(invoer) || invoer === ""){
        msg = "Voer een bedrag in alstublieft."
        invoer.setCustomValidity(msg);
    }


    //
    // if (!invoer.includes(".00")){
    //     var makeMoney = ".00";
    //     return invoer.concat(makeMoney);
    // }

    var bedrag = document.getElementsByName("bedrag").valueOf();
    var saldo = document.getElementById("saldo").valueOf();
    var nieuwBedrag = saldo - bedrag;

    if (nieuwBedrag < 0){
        msg = "U heeft niet genoeg saldo om dit bedrag over te maken."
        invoer.setCustomValidity(msg);
    } else if (bedrag.includes(",")){
        return bedrag.replace(",", ".")
    }
}