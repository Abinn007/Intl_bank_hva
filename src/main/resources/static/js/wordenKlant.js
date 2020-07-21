//Om een customized bericht te laten zien als de ingevorde waarde niet aan de gevraagde eisen voldoet.
function validatie(invoer) {
    const msg = invoer.name + " ongeldig";
    invoer.setCustomValidity(msg);
}

function valid_postcode(invoer) {
    const msg = invoer.name + " ongeldig, een postcode moet 4 cijfers gevolgd door 2 letters bevatten !";
    invoer.setCustomValidity(msg);
}

function valid_password(invoer) {
    const msg = "Een " + invoer.name + " moet minimaal 8 tekens, één hoofdletter,een kleine letter en " +
        "1 cijfer/special character bevatten!";
    invoer.setCustomValidity(msg);
}

//Om twee wachtworden te controleren of ze overeen komen.
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

//Om de leeftijd van nieuwe klant te controlern of hij/zij minimum 18 jaar oud is.
function dobValidate() {
    let vandaag = new Date();
    let ditJaar = vandaag.getFullYear();
    let ditMaand = vandaag.getMonth();
    let ditDag = vandaag.getDate();
    let geboortedatum = document.getElementById("geboortedatum").value;
    let geboorte = new Date(geboortedatum);
    let geboorteJaar = geboorte.getFullYear();
    let geboorteMaand = geboorte.getMonth();
    let geboorteDag = geboorte.getDate();
    let leeftijd_jaar = ditJaar - geboorteJaar;
    let leeftijd_maand = ditMaand - geboorteMaand;
    let leeftijd_dag = ditDag - geboorteDag;

    if ((leeftijd_jaar === 18 && leeftijd_maand <= 0 && leeftijd_dag < 0) || leeftijd_jaar < 18) {
        document.getElementById("geboortedatum").setCustomValidity("Om klant te worden moet " +
            "u minimaal 18 jaar oud zijn.");
        return false;
    }
}




