/* ALGO */
$(function () {
    var includes = $('[data-include]')
    $.each(includes, function () {
        console.log($(this).data('include') + '.html');
        var file = $(this).data('include') + '.html'
        $(this).load(file)
    })
})

$.getScript("js/popper.min.js", function () {
});
$.getScript("js/bootstrap.min.js", function () {
});


function includeHTML() {
    var z, i, elmnt, file, xhttp;
    /* Loop through a collection of all HTML elements: /
    z = document.getElementsByTagName("");
    for (i = 0; i < z.length; i++) {
      elmnt = z[i];
      /search for elements with a certain atrribute:/
      file = elmnt.getAttribute("w3-include-html");
      if (file) {
        /* Make an HTTP request using the attribute value as the file name: /
        xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function() {
          if (this.readyState == 4) {
            if (this.status == 200) {elmnt.innerHTML = this.responseText;}
            if (this.status == 404) {elmnt.innerHTML = "Page not found.";}
            / Remove the attribute, and call this function once more: /
            elmnt.removeAttribute("w3-include-html");
            includeHTML();
          }
        }
        xhttp.open("GET", file, true);
        xhttp.send();
        / Exit the function: */
    return;
//}
//}
}

$(document).ready(function () {
    $("#inputUsuario").on("keyup", function () {
        var value = $(this).val().toLowerCase();
        $("#tabla tr").filter(function () {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });
});

function confirmarEliminacion(id) {
    document.getElementById("userId").value = id;
    document.getElementById("modalEliminar").style.display = 'block';
}

function eliminarUsuario() {
    var id = document.getElementById("userId").value;
    $.ajax({
        type: "DELETE",
        url: "usuario/" + id,
        success: function (result) {
            location.reload(true);
        }
    });
}

function cerrarModal() {
    document.getElementsByClassName("modal")[0].style.display = 'none';
}

//Registro de Mascotas
/*  Parte Uno  */
$(document).on('click', '#siguiente-parte-uno', function () {
    $("#parte-uno").attr("hidden", true);
    $("#parte-dos").attr("hidden", false);
});
/*  Parte Dos  */
$(document).on('click', '#atras-parte-dos', function () {
    $("#parte-uno").attr("hidden", false);
    $("#parte-dos").attr("hidden", true);
});

$(document).on('click', '#siguiente-parte-dos', function () {
    $("#parte-dos").attr("hidden", true);
    $("#parte-tres").attr("hidden", false);
});

/*  Parte Tres  */
$(document).on('click', '#atras-parte-tres', function () {
    $("#parte-dos").attr("hidden", false);
    $("#parte-tres").attr("hidden", true);
});

//VALIDACION DATOS DE RESGISTRO
const regsiterForm = document.querySelector('.register-form');
const submitBtn = document.getElementById('submit-btn');
const submissionStatus = document.querySelector('.submission-status');
const passwordStatus = document.querySelector('.password-status');

// input values
const firstName = document.getElementById('firstname'),
    lastName = document.getElementById('lastname'),
    dni = document.getElementById('nroDoc'),
    calle = document.getElementById('calle'),
    altura = document.getElementById('altura'),
    codigoPostal = document.getElementById('codigopostal'),
    emailAddr = document.getElementById('email'),
    phoneNumber = document.getElementById('phonenumber'),
    usuario = document.getElementById('username'),
    password = document.getElementById('password'),
    confirmPassword = document.getElementById('confirm-password');


submitBtn.addEventListener('click', (e) => {
    e.preventDefault();
    let isValidForm = validateInputValues();
    if(isValidForm){
        submissionStatus.classList.add('successMessage');
        submissionStatus.textContent = "Registration succeeded!";
        regsiterForm.submit();
    } else {
        submissionStatus.classList.add('errorMessage');
        submissionStatus.textContent = "Registration failed!";
        passwordStatus.classList.add('errorPassword');
        passwordStatus.textContent = "Valida tu password: inclui al menos una mayuscula, una minuscula, un numero y un caracter especial. Al menos 8 digitos";
    }

    setTimeout(() => {
        submissionStatus.classList.remove('errorMessage', 'successMessage');
    }, 1500);
    setTimeout(() => {
        passwordStatus.classList.remove('errorPassword');
    }, 1500);
});

function validateInputValues(){
    let inputValidationStatus = [];
    if(validateName(firstName.value)){
        inputStatus(true, firstName);
        inputValidationStatus[0] = true;
    } else {
        inputStatus(false, firstName);
        inputValidationStatus[0] = false;
    }

    if(validateName(lastName.value)){
        inputStatus(true, lastName);
        inputValidationStatus[1] = true;
    } else {
        inputStatus(false, lastName);
        inputValidationStatus[1] = false;
    }

    if(validateDoc(dni.value)){
        inputStatus(true, dni);
        inputValidationStatus[6] = true;
    } else {
        inputStatus(false, dni);
        inputValidationStatus[6] = false;
    }

    if(validateName(calle.value)){
        inputStatus(true, calle);
        inputValidationStatus[6] = true;
    } else {
        inputStatus(false, calle);
        inputValidationStatus[6] = false;
    }

    if(validateDir(altura.value)){
        inputStatus(true, altura);
        inputValidationStatus[6] = true;
    } else {
        inputStatus(false, altura);
        inputValidationStatus[6] = false;
    }

    if(validateDir(codigoPostal.value)){
        inputStatus(true, codigoPostal);
        inputValidationStatus[6] = true;
    } else {
        inputStatus(false, codigoPostal);
        inputValidationStatus[6] = false;
    }

    if(validateEmail(emailAddr.value)){
        inputStatus(true, emailAddr);
        inputValidationStatus[2] = true;
    } else {
        inputStatus(false, emailAddr);
        inputValidationStatus[2] = false;
    }

    if(validatePhoneNo(phoneNumber.value)){
        inputStatus(true, phoneNumber);
        inputValidationStatus[3] = true;
    } else {
        inputStatus(false, phoneNumber);
        inputValidationStatus[3] = false;
    }

    if(validatePassword(password.value)){
        inputStatus(true, password);
        validarContra(true, password);
        inputValidationStatus[4] = true;
    } else {
        inputStatus(false, password);
        validarContra(false, password);
        inputValidationStatus[4] = false;
    }

    if(confirmPassword.value.trim() !== "" && validateConfirmPassword(password.value, confirmPassword.value)){
        inputStatus(true, confirmPassword);
        inputValidationStatus[5] = true;
    } else {
        inputStatus(false, confirmPassword);
        inputValidationStatus[5] = false;
    }

    return (inputValidationStatus.includes(false) ? false : true);
}

// validate first and lastname and calle
function validateName(nameTxt){
    const nameRegex = /^[A-Za-z]+$/; // firstname or lastname containing only letters
    return nameRegex.test(nameTxt);
}

// validate dni
function validateDoc(dniTxt){
    const dniRegex = /[0-9]{7,8}/;
    return dniRegex.test(dniTxt);
}

// validate altura y cp
function validateDir(dirTxt){
    const dirRegex = /[0-9]{1,5}/;
    return dirRegex.test(dirTxt);
}

// validate email address
function validateEmail(emailTxt){
    const emailRegex = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return emailRegex.test(emailTxt);
}

// validate phonenumber
function validatePhoneNo(phoneTxt){
    const phoneRegex = /^[\+]?[(]?[0-9]{2}[)]?[-\s\.]?[0-9]{4}[-\s\.]?[0-9]{4}$/im;
    /* supports following number formats
        11 3403-1128
        15 3403-1128
    */
    return phoneRegex.test(phoneTxt);
}

// validate password
function validatePassword(passwordTxt){
    const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
    // Minimum eight characters, at least one uppercase letter, one lowercase letter, one number and one special character
    return passwordRegex.test(passwordTxt);
}

// validate confirm password
function validateConfirmPassword(passwordTxt, confirmPasswordTxt){
    return (passwordTxt == confirmPasswordTxt);
}


// error or success status
function inputStatus(status, input){
    let inputGroup = input.parentElement;
    if(status){
        inputGroup.classList.add('success');
    } else {
        inputGroup.classList.add('error');
    }

    setTimeout(() => {
        inputGroup.classList.remove('success', 'error');
    }, 1500);
}

function validarContra(status, input){
    let inputGroup = input.parentElement;
    if(status){
        inputGroup.classList.add('success');
    } else {
        inputGroup.classList.add('error');
    }

    setTimeout(() => {
        inputGroup.classList.remove('success', 'error');
    }, 1500);
}/* ALGO */
 $(function () {
     var includes = $('[data-include]')
     $.each(includes, function () {
         console.log($(this).data('include') + '.html');
         var file = $(this).data('include') + '.html'
         $(this).load(file)
     })
 })

 $.getScript("js/popper.min.js", function () {
 });
 $.getScript("js/bootstrap.min.js", function () {
 });


 function includeHTML() {
     var z, i, elmnt, file, xhttp;
     /* Loop through a collection of all HTML elements: /
     z = document.getElementsByTagName("");
     for (i = 0; i < z.length; i++) {
       elmnt = z[i];
       /search for elements with a certain atrribute:/
       file = elmnt.getAttribute("w3-include-html");
       if (file) {
         /* Make an HTTP request using the attribute value as the file name: /
         xhttp = new XMLHttpRequest();
         xhttp.onreadystatechange = function() {
           if (this.readyState == 4) {
             if (this.status == 200) {elmnt.innerHTML = this.responseText;}
             if (this.status == 404) {elmnt.innerHTML = "Page not found.";}
             / Remove the attribute, and call this function once more: /
             elmnt.removeAttribute("w3-include-html");
             includeHTML();
           }
         }
         xhttp.open("GET", file, true);
         xhttp.send();
         / Exit the function: */
     return;
 //}
 //}
 }

 $(document).ready(function () {
     $("#inputUsuario").on("keyup", function () {
         var value = $(this).val().toLowerCase();
         $("#tabla tr").filter(function () {
             $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
         });
     });
 });

 function confirmarEliminacion(id) {
     document.getElementById("userId").value = id;
     document.getElementById("modalEliminar").style.display = 'block';
 }

 function eliminarUsuario() {
     var id = document.getElementById("userId").value;
     $.ajax({
         type: "DELETE",
         url: "usuario/" + id,
         success: function (result) {
             location.reload(true);
         }
     });
 }

 function cerrarModal() {
     document.getElementsByClassName("modal")[0].style.display = 'none';
 }

 //Registro de Mascotas
 /*  Parte Uno  */
 $(document).on('click', '#siguiente-parte-uno', function () {
     $("#parte-uno").attr("hidden", true);
     $("#parte-dos").attr("hidden", false);
 });
 /*  Parte Dos  */
 $(document).on('click', '#atras-parte-dos', function () {
     $("#parte-uno").attr("hidden", false);
     $("#parte-dos").attr("hidden", true);
 });

 $(document).on('click', '#siguiente-parte-dos', function () {
     $("#parte-dos").attr("hidden", true);
     $("#parte-tres").attr("hidden", false);
 });

 /*  Parte Tres  */
 $(document).on('click', '#atras-parte-tres', function () {
     $("#parte-dos").attr("hidden", false);
     $("#parte-tres").attr("hidden", true);
 });

 //VALIDACION DATOS DE RESGISTRO
 const regsiterForm = document.querySelector('.register-form');
 const submitBtn = document.getElementById('submit-btn');
 const submissionStatus = document.querySelector('.submission-status');
 const passwordStatus = document.querySelector('.password-status');

 // input values
 const firstName = document.getElementById('firstname'),
     lastName = document.getElementById('lastname'),
     dni = document.getElementById('nroDoc'),
     calle = document.getElementById('calle'),
     altura = document.getElementById('altura'),
     codigoPostal = document.getElementById('codigopostal'),
     emailAddr = document.getElementById('email'),
     phoneNumber = document.getElementById('phonenumber'),
     usuario = document.getElementById('username'),
     password = document.getElementById('password'),
     confirmPassword = document.getElementById('confirm-password');


 submitBtn.addEventListener('click', (e) => {
     e.preventDefault();
     let isValidForm = validateInputValues();
     if(isValidForm){
         submissionStatus.classList.add('successMessage');
         submissionStatus.textContent = "Registration succeeded!";
         regsiterForm.submit();
     } else {
         submissionStatus.classList.add('errorMessage');
         submissionStatus.textContent = "Registration failed!";
         passwordStatus.classList.add('errorPassword');
         passwordStatus.textContent = "password: al menos una mayuscula, una minuscula, un numero y un caracter especial. El numero debe contener exactamente 8 digitos sin guiones. El codigo postal y la altura debe contener 1-5 numeros";
     }

     setTimeout(() => {
         submissionStatus.classList.remove('errorMessage', 'successMessage');
     }, 1500);
     setTimeout(() => {
         passwordStatus.classList.remove('errorPassword');
     }, 1500);
 });

 function validateInputValues(){
     let inputValidationStatus = [];
     if(validateName(firstName.value)){
         inputStatus(true, firstName);
         inputValidationStatus[0] = true;
     } else {
         inputStatus(false, firstName);
         inputValidationStatus[0] = false;
     }

     if(validateName(lastName.value)){
         inputStatus(true, lastName);
         inputValidationStatus[1] = true;
     } else {
         inputStatus(false, lastName);
         inputValidationStatus[1] = false;
     }

     if(validateDoc(dni.value)){
         inputStatus(true, dni);
         inputValidationStatus[6] = true;
     } else {
         inputStatus(false, dni);
         inputValidationStatus[6] = false;
     }

     if(validateName(calle.value)){
         inputStatus(true, calle);
         inputValidationStatus[6] = true;
     } else {
         inputStatus(false, calle);
         inputValidationStatus[6] = false;
     }

     if(validateDir(altura.value)){
         inputStatus(true, altura);
         inputValidationStatus[6] = true;
     } else {
         inputStatus(false, altura);
         inputValidationStatus[6] = false;
     }

     if(validateDir(codigoPostal.value)){
         inputStatus(true, codigoPostal);
         inputValidationStatus[6] = true;
     } else {
         inputStatus(false, codigoPostal);
         inputValidationStatus[6] = false;
     }

     if(validateEmail(emailAddr.value)){
         inputStatus(true, emailAddr);
         inputValidationStatus[2] = true;
     } else {
         inputStatus(false, emailAddr);
         inputValidationStatus[2] = false;
     }

     if(validatePhoneNo(phoneNumber.value)){
         inputStatus(true, phoneNumber);
         inputValidationStatus[3] = true;
     } else {
         inputStatus(false, phoneNumber);
         inputValidationStatus[3] = false;
     }

     if(validatePassword(password.value)){
         inputStatus(true, password);
         validarContra(true, password);
         inputValidationStatus[4] = true;
     } else {
         inputStatus(false, password);
         validarContra(false, password);
         inputValidationStatus[4] = false;
     }

     if(confirmPassword.value.trim() !== "" && validateConfirmPassword(password.value, confirmPassword.value)){
         inputStatus(true, confirmPassword);
         inputValidationStatus[5] = true;
     } else {
         inputStatus(false, confirmPassword);
         inputValidationStatus[5] = false;
     }

     return (inputValidationStatus.includes(false) ? false : true);
 }

 // validate first and lastname and calle
 function validateName(nameTxt){
     const nameRegex = /^[A-Za-z]+$/; // firstname or lastname containing only letters
     return nameRegex.test(nameTxt);
 }

 // validate dni
 function validateDoc(dniTxt){
     const dniRegex = /[0-9]{7,8}/;
     return dniRegex.test(dniTxt);
 }

 // validate altura y cp
 function validateDir(dirTxt){
     const dirRegex = /[0-9]{1,5}/;
     return dirRegex.test(dirTxt);
 }

 // validate email address
 function validateEmail(emailTxt){
     const emailRegex = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
     return emailRegex.test(emailTxt);
 }

 // validate phonenumber
 function validatePhoneNo(phoneTxt){
     const phoneRegex = /^[\+]?[(]?[0-9]{2}[)]?[-\s\.]?[0-9]{4}[-\s\.]?[0-9]{4}$/im;
     /* supports following number formats
         11 3403-1128
         15 3403-1128
     */
     return phoneRegex.test(phoneTxt);
 }

 // validate password
 function validatePassword(passwordTxt){
     const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
     // Minimum eight characters, at least one uppercase letter, one lowercase letter, one number and one special character
     return passwordRegex.test(passwordTxt);
 }

 // validate confirm password
 function validateConfirmPassword(passwordTxt, confirmPasswordTxt){
     return (passwordTxt == confirmPasswordTxt);
 }


 // error or success status
 function inputStatus(status, input){
     let inputGroup = input.parentElement;
     if(status){
         inputGroup.classList.add('success');
     } else {
         inputGroup.classList.add('error');
     }

     setTimeout(() => {
         inputGroup.classList.remove('success', 'error');
     }, 1500);
 }

 function validarContra(status, input){
     let inputGroup = input.parentElement;
     if(status){
         inputGroup.classList.add('success');
     } else {
         inputGroup.classList.add('error');
     }

     setTimeout(() => {
         inputGroup.classList.remove('success', 'error');
     }, 1500);
 }