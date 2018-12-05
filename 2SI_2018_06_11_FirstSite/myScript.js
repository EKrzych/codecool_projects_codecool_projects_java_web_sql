function validateIfEmpty() {
    var isValid=0;
    isValid+=validateFirstNameEmpty();
    isValid+=validateEmailEmpty();
    isValid+=validateTextAreaEmpty();
    
    if(isValid==3) {
        document.getElementById("submit").disabled=false;
    } else {
        document.getElementById("submit").disabled=true;
    }
}

function validate() {
    validateIfEmpty();
    validateFirstName();
    validateEmail();
    validateTextArea();
}

function validateFirstName() {
    var firstName = document.getElementById("firstName");
    var pattern = /(.?[A-Z][a-z]*)+/;
    if(pattern.test(firstName.value)){
        document.getElementById("errorMessageFirstName").className="error-hidden";
        return 1;
    } else {
        document.getElementById("errorMessageFirstName").className="error-visible"; 
    }
}

function validateEmail() {
    var email = document.getElementById("email");
    var pattern =/[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}/;
    if(pattern.test(email.value)) {
        document.getElementById("errorMessageEmail").className="error-hidden";
        return 1;
    } else {
        document.getElementById("errorMessageEmail").className="error-visible";
    }
}

function validateFirstNameEmpty() {
    var firstName = document.getElementById("firstName");
    if(firstName.value != ""){
        return 1;
    }
}

function validateEmailEmpty() {
    var email = document.getElementById("email");
    if(email.value != ""){
        return 1;
    }
}

function validateTextAreaEmpty() {
    if(document.getElementById("message").value != ""){
        return 1;
    }
}

function validateTextArea() {
    if(document.getElementById("message").value != ""){
        document.getElementById("errorMessage").className="error-hidden";
        return 1;
    } else {
        document.getElementById("errorMessage").className="error-visible";
    }
}
