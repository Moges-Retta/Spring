"use strict";
let deurMetFriet = Math.floor((Math.random() * 7) + 1);
for (const button of document.getElementsByTagName("button")) {
button.onclick = function () {
if(Number(this.value)!=0){
const img = this.querySelector("img");
if (Number(this.value) === deurMetFriet) {
img.src = "/images/gevonden.png";
img.alt = "gevonden";
} else {
img.src = "/images/deuropen.png";
img.alt = "deur open";
}
}else{
document.querySelectorAll("li img").forEach(img =>
    img.src="/images/deurtoe.png");
}
};
};
/*document.getElementById("NieuwSpel").onclick=function(){
    document.querySelectorAll("li img").forEach(img =>
    img.src="/images/deurtoe.png");
};
};*/