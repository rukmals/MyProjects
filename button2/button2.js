
/* This assigns the element with id 'buttonId' to 'btn' */
var btn = document.getElementById("buttonId_5");

/* This sets the action to perform on a click event */
btn.addEventListener("click", function() {
    /* This changes the button's label */
    var btn1= document.getElementById("buttonId_2").innerHTML;
    document.getElementById("buttonId_2").innerHTML =  document.getElementById("buttonId_1").innerHTML;
    document.getElementById("buttonId_1").innerHTML = document.getElementById("buttonId_4").innerHTML;
    document.getElementById("buttonId_4").innerHTML =  document.getElementById("buttonId_7").innerHTML;
    document.getElementById("buttonId_7").innerHTML = document.getElementById("buttonId_8").innerHTML;
    document.getElementById("buttonId_8").innerHTML = document.getElementById("buttonId_9").innerHTML;
    document.getElementById("buttonId_9").innerHTML = document.getElementById("buttonId_6").innerHTML;
    document.getElementById("buttonId_6").innerHTML = document.getElementById("buttonId_3").innerHTML;
    document.getElementById("buttonId_3").innerHTML = btn1;
    
});
