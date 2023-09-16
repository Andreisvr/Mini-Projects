
document.getElementById("first").addEventListener('click', function () {

  window.location.href = "index.html";
});

function changeColor(color) {
  const circle = document.getElementById("circle");
  circle.style.backgroundColor = color;
}
