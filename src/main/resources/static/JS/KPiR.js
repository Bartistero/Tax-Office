function newInvoice() {
    document.getElementById("newInvoiceForm").showModal();
}

function newCost() {
    document.getElementById("newCostForm").showModal();
}

function edit() {

    checked = -1;
    len = document.getElementsByName("invoice")

    for(i = 0; i<len.length ; i++){
    if(len[i].checked){
            checked = i;
            break;
    }
    }

    if(checked == -1){
    alert("Proszę zaznaczyć, fakturę do edycji.")
    }else{
    checked += 1;
        checked = "a"+checked;
        var price = document.querySelector("#" + checked + " #price span");
        var typeOfInvoice = 1;
        if(price == null){
          var price = document.querySelector("#" + checked + " #priceCost span");
                  var typeOfInvoice = 0;
        }
        price = price.innerText;
        var invoiceNumber = document.querySelector("#" + checked + " #invoiceNumber span").innerText;
        var date  = document.querySelector("#" + checked + " #date span").innerText;
        var customerNIP  = document.querySelector("#" + checked + " #customerNIP span").innerText;
        var customerName  = document.querySelector("#" + checked + " #customerName span").innerText;
        var address  = document.querySelector("#" + checked + " #address span").innerText;
        var invoiceNumber = document.querySelector("#" + checked + " #invoiceNumber span").innerText;

        document.querySelector("#newEditForm  #customerNIP").value = customerNIP;
        document.querySelector("#newEditForm  #customerName").value = customerName;
        document.querySelector("#newEditForm  #address").value = address;
        document.querySelector("#newEditForm  #nettoPrice").value = price;
        document.querySelector("#newEditForm  #invoiceNumber").value = invoiceNumber;
        document.querySelector("#newEditForm  #type").value = typeOfInvoice;
        document.getElementById("newEditForm").showModal();
    }
}

function sum() {

    var sumIncome = 0;
    var income = document.getElementsByClassName("income");
    Array.prototype.forEach.call(income, function (el) {
        sumIncome += parseFloat(el.textContent);
    });
    var divIncome = document.getElementById("sumIncome");
    divIncome.innerText = sumIncome;

    var sumCost = 0;
    var cost = document.getElementsByClassName("cost");
    Array.prototype.forEach.call(cost, function (el) {
        sumCost += parseFloat(el.textContent);
    });
    var divCost = document.getElementById("sumCost");
    divCost.innerText = sumCost;

    var all = document.getElementById("SumIncom");
    var number = Number(Math.round((sumIncome - sumCost) + 'e+2') + 'e-2');
    all.innerText = number;

    var pitTax = document.getElementById("pitTax");
    var pit =0;
    if (number > 0) {
        pit = Number(Math.round((number * 0.17) + 'e+2') + 'e-2');
    }
    pitTax.innerText = pit;

}

function setMonth(month) {

    var monthLikeString = "Miesiąc: "
    if (month == 1) monthLikeString += "Styczeń"
    else if (month == 2) monthLikeString += "Luty"
    else if (month == 3) monthLikeString += "Marzec"
    else if (month == 4) monthLikeString += "Kwiecień"
    else if (month == 5) monthLikeString += "Maj"
    else if (month == 6) monthLikeString += "Czerwiec"
    else if (month == 7) monthLikeString += "Lipiec"
    else if (month == 8) monthLikeString += "Sierpień"
    else if (month == 9) monthLikeString += "Wrzesień"
    else if (month == 10) monthLikeString += "Październik"
    else if (month == 11) monthLikeString += "Listopad"
    else if (month == 12) monthLikeString += "Grudzień"

    var showMonth = document.getElementById("showMonth");
    showMonth.innerText = monthLikeString;
}

