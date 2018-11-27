function toggleSettingsTab() {
    var tab = document.querySelector('.settings--tab');
    var display = tab.style.display;
    display = display == "" ? "none" : display;
    switch(display) {
        case "flex":
            tab.style.display = "none";
            break;
        case "none":
            tab.style.display = "flex";
            break;
    }
}


function showEstimationExplanation() {
    var usEstimates = document.querySelectorAll(".estimates");
    for(var i = 0; i < usEstimates.length; i++) {
        estimatesContainer = usEstimates[i];

        estimates = estimatesContainer.querySelectorAll(".estimate");

        for(var j = 0; j < estimates.length; j++) {
            var e = estimates[j];
            e.addEventListener("mouseenter", function(ev) {
                var el = ev.srcElement;
                var expl = el.querySelector(".estimate__explanation");
                expl.style.display = "block";
            });

            e.addEventListener("mouseleave", function(ev) {
                var el = ev.srcElement;
                var expl = el.querySelector(".estimate__explanation");
                expl.style.display = "none";
            });
        }
    }
}

document.addEventListener("DOMContentLoaded", function() {
    showEstimationExplanation();
});
