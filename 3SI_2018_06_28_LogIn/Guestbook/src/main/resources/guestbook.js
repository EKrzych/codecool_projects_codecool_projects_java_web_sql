function saveToDB() {
        var message = document.getElementById("message").value;
        var name = document.getElementById("name").value;
}

function getEntries() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {

        if(this.readyState == 4 && this.status == 200) {
            var entries = JSON.parse(this.response);

            for (x in entries) {
                var container = document.createElement("div");
                container.setAttribute("class", "single")
            	for (y in entries[x]) {
                	container.innerHTML += entries[x][y] + "<br>";
                }
                document.getElementById("entries").append(container);
            }
        }
    };
    xhttp.open("GET", "/data", true);
    xhttp.send();

}

