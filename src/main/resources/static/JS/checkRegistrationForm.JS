function checkForm(){
   const form = document.getElementById("JsForm");
   const NIP = document.getElementById("NIP");
   const eMail = document.getElementById("eMail");
   const password1 = document.getElementById("password");
   const password = document.getElementById("password1");
   const PZ = document.getElementById("PZ")
   error = document.getElementById("errors");

   const regNip = /^[0-9]{10}$/;
   const regEmail = /^[a-zA-Z0-9_.-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,3}/;

   var formsErrors = [];
    formsErrors.push("Przed wysłaniem formularza rejestracji  proszę poprawić następujące błędy:");

   if(!regNip.test(NIP.value)){
    formsErrors.push("Podano nieprawidłowy NIP");
   }
   if((password1.value != password1.value) || password1.value==""){
     formsErrors.push("Podane hasła się różnią");
   }
   if(!regEmail.test(eMail.value)){
   formsErrors.push("Podano nieprawidłowy e-mail");
   }

   if(PZ.checked != true){
   formsErrors.push("Należy zweryfikować się poprzez Profil Zaufany");
   }

   if(formsErrors.length <=1 ){
    form.submit();
            localStorage.setItem("Registration", 1);

   }else{
        var html = formsErrors[0] + "</br>";
        for(i=1; i<formsErrors.length;i++){
            html = html + i  + ". " + formsErrors[i] + "<br> ";
        }
        error.innerHTML = html;


   }
}
function checkRegistation(){

        var registration = localStorage.getItem("Registration");
        if(registration == 1){
       // alert("działam2");
            error = document.getElementById("errors");
             var html = "Podany NIP nie został odnaleziony w Bazie danych";
             error.innerHTML = html;
            localStorage.removeItem("Registration");
        }
   }




