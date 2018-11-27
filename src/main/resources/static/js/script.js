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