const modalButton  = document.getElementById('modalButton');
document.getElementById("deleteButton").onclick = function() {
  window.location.href = "delete/"+modalButton.name;
};