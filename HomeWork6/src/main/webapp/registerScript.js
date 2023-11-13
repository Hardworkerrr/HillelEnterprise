const submit = document.getElementById("buttonReg");
const usernameField = document.getElementById("usernameReg");
const passwordField = document.getElementById("passwordReg");
const emailField = document.getElementById("emailReg");
const phoneField = document.getElementById("phoneReg");
const nameField = document.getElementById("nameReg")
const form = document.getElementById("registerForm");


String.prototype.isMatch = function (s) {
    return this.match(s) !== null
}

submit.addEventListener("click", function (e) {
    validateFullName(e);
    validateEmail(e);
    validatePhoneNumber(e);
    checkUsernameLength(usernameField, e);
    validateUsername(e);
    validatePassword(e);
})


function validateUsername(e) {
    if (!usernameField.value.isMatch(/^[a-zA-Z0-9]+$/)) {
        clearErrors();
        let para = document.createElement("p");
        para.setAttribute("id", "errorP")
        para.style.color = 'red';
        para.textContent = "Only letters and digits !";
        form.insertBefore(para, form.childNodes[24]);
        e.preventDefault();
    }
}

function validatePassword(e) {
    if(passwordIsEmpty() || passwordContainsSpace() || checkPasswordLength() ){
        e.preventDefault();
    }
}

function passwordIsEmpty(){
    if (passwordField.value === "") {
        clearErrors();
        let parameter = document.createElement("p");
        parameter.setAttribute("id", "errorU");
        parameter.style.color = 'red';
        parameter.textContent = "Password can't be empty !";
        form.insertBefore(parameter, form.childNodes[30]);
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
        form.insertBefore(parameter, form.childNodes[30]);
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
        form.insertBefore(parameter, form.childNodes[30]);
        return true;
    }
}
function clearErrors() {
    if (document.getElementById("errorP") != null) {
        form.removeChild(document.getElementById("errorP"))
    }
    if (document.getElementById("errorU") != null) {
        form.removeChild(document.getElementById("errorU"))
    }
    if(document.getElementById("errorNumber") != null){
        form.removeChild(document.getElementById("errorNumber"))
    }
    if(document.getElementById("errorEmail")){
        form.removeChild(document.getElementById("errorEmail"))
    }
    if(document.getElementById("errorName")){
        form.removeChild(document.getElementById("errorName"))
    }
}

function checkUsernameLength(htmlElement, e) {
    if (htmlElement.value.length > 14 || htmlElement.value.length < 6) {
        clearErrors();
        let parameter = document.createElement("p");
        parameter.setAttribute("id", "errorP");
        parameter.style.color = 'red';
        parameter.textContent = "Username Length should be less than 14 and more than 6 symbols!";
        form.insertBefore(parameter, form.childNodes[24]);
        e.preventDefault();
    }
}

function validatePhoneNumber(e){
    if(!phoneField.value.isMatch("^(?:\\d{10}|\\w+@\\w+\\.\\w{2,3})$")){
        clearErrors();
        let parameter = document.createElement("p");
        parameter.setAttribute("id", "errorNumber");
        parameter.style.color = 'red';
        parameter.textContent = "Unknown phone number format !";
        form.insertBefore(parameter, form.childNodes[16]);
        e.preventDefault();
    }
}

function validateEmail(e){
    if(!emailField.value.isMatch("^(?:\\d{10}|\\w+@\\w+\\.\\w{2,3})$")){
        clearErrors();
        let parameter = document.createElement("p");
        parameter.setAttribute("id", "errorEmail");
        parameter.style.color = 'red';
        parameter.textContent = "Wrong email format !";
        form.insertBefore(parameter, form.childNodes[12]);
        e.preventDefault();
    }
}

function validateFullName(e){
    if(!nameField.value.isMatch("^[a-z]([-']?[a-z]+)*( [a-z]([-']?[a-z]+)*)+$")){
        clearErrors();
        let parameter = document.createElement("p");
        parameter.setAttribute("id", "errorName");
        parameter.style.color = 'red';
        parameter.textContent = "Please type valid full name with space !";
        form.insertBefore(parameter, form.childNodes[6]);
        e.preventDefault();
    }
}
