const submit = document.getElementById("buttonLog");
const usernameField = document.getElementById("username");
const passwordField = document.getElementById("password");
const form = document.getElementById("loginForm");

submit.addEventListener("click", function (e) {
    checkUsernameLength(usernameField, e);
    validateUsername(e);
    validatePassword(e);
})

String.prototype.isMatch = function (s) {
    return this.match(s) !== null
}

function clearErrors() {
    if (document.getElementById("errorP") != null) {
        form.removeChild(document.getElementById("errorP"))
    }
    if (document.getElementById("errorU") != null) {
        form.removeChild(document.getElementById("errorU"))
    }

}

function validateUsername(e) {
    if (!usernameField.value.isMatch(/^[a-zA-Z0-9]+$/)) {
        clearErrors();
        let para = document.createElement("p");
        para.setAttribute("id", "errorP")
        para.style.color = 'red';
        para.textContent = "Only letters and digits !";
        form.insertBefore(para, form.childNodes[4]);
        e.preventDefault();
    }
}

function checkUsernameLength(htmlElement, e) {
    if (htmlElement.value.length > 14 || htmlElement.value.length < 6) {
        clearErrors();
        let parameter = document.createElement("p");
        parameter.setAttribute("id", "errorP");
        parameter.style.color = 'red';
        parameter.textContent = "Username Length should be less than 14 and more than 6 symbols !";
        form.insertBefore(parameter, form.childNodes[4]);
        e.preventDefault();
    }
}

function validatePassword(e) {
    if(passwordIsEmpty() || passwordContainsSpace() || checkPasswordLength() || passwordContainsBadSymbols()){
        e.preventDefault();
    }
}

function passwordContainsBadSymbols(){
    if(passwordField.value.isMatch("[/^%'?#<>%@]")){
        clearErrors();
        let parameter = document.createElement("p");
        parameter.setAttribute("id", "errorU");
        parameter.style.color = 'red';
        parameter.textContent = "Unaccepted symbols in password !";
        form.insertBefore(parameter, form.childNodes[14]);
        return true;
    }
}

function passwordIsEmpty(){
    if (passwordField.value === "") {
        clearErrors();
        let parameter = document.createElement("p");
        parameter.setAttribute("id", "errorU");
        parameter.style.color = 'red';
        parameter.textContent = "Password can't be empty !";
        form.insertBefore(parameter, form.childNodes[14]);
        return true;
    }
}

function passwordContainsSpace() {
    if (passwordField.value.isMatch(" ")) {
        clearErrors();
        let parameter = document.createElement("p");
        parameter.setAttribute("id", "errorU");
        parameter.style.color = 'red';
        parameter.textContent = "Password can't contain spaces !";
        form.insertBefore(parameter, form.childNodes[14]);
        return true;
    }
}

function checkPasswordLength(){
    if(passwordField.value.length < 6){
        clearErrors();
        let parameter = document.createElement("p");
        parameter.setAttribute("id", "errorU");
        parameter.style.color = 'red';
        parameter.textContent = "Password can't be less than 6 symbols !";
        form.insertBefore(parameter, form.childNodes[14]);
        return true;
    }
}

