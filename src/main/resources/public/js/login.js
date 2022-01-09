$(document).on('click', '#log', function () {
    var attempt = 3; // Variable to count number of attempts.
    // Below function Executes on click of login button.
 
        var username = document.getElementById("Uname").value;
        var password = document.getElementById("Pass").value;
        if (username == "admin" && password == "admin") {
            alert("Login successfully");
            window.location = "indexAdmin.html"; // Redirecting to other page.
            return false;
        }
        else if (username == "voluntario" && password == "voluntario") {
            alert("Login successfully");
            window.location = "indexVoluntario.html"; // Redirecting to other page.
            return false;
        }
        else if (username == "duenio" && password == "duenio") {
            alert("Login successfully");
            window.location = "indexDuenio.html"; // Redirecting to other page.
            return false;
        }
        else {
            attempt--;// Decrementing by one.
            alert("You have left " + attempt + " attempt;");
            // Disabling fields after 3 attempts.
            if (attempt == 0) {
                document.getElementById("Uname").disabled = true;
                document.getElementById("Pass").disabled = true;
                document.getElementById("submit").disabled = true;
                return false;
            }
        }
});