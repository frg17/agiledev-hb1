var s = [];

var k = new Audio();
k.src = "https://res.cloudinary.com/frozenscloud/video/upload/v1540036072/hehe/348271__metekavruk__fruitbite.ogg";
s.push(k);
k = new Audio();
k.src = "https://res.cloudinary.com/frozenscloud/video/upload/v1540036072/hehe/209171__lukesharples__hagh.wav";
s.push(k);
k = new Audio();
k.src = "https://res.cloudinary.com/frozenscloud/video/upload/v1540036071/hehe/85__plagasul__jeeh.wav";
s.push(k);
k = new Audio();
k.src = "https://res.cloudinary.com/frozenscloud/video/upload/v1540036058/hehe/436163__dersuperanton__what-mouse-squirrel-cartoon.wav";
s.push(k);

document.addEventListener("keydown", function() {
    var r = Math.floor(Math.random() * 4);
    s[r].play();
});
