function newInvoice() {
    alert("Nowy rachunek");
}

function newCost() {
    alert("Nowy koszt");
}

function edit() {
    alert("edycja");
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

