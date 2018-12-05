document.getElementById("create").onclick = createNote;

function Note(title = "Edit title", content = "Edit content") {
    this.title = title;
    this.content = content;
}

function createNote() {
    let notesArray = [];
    if(checkIfAnyNotes()) {
        notesArray = JSON.parse(localStorage.getItem("notes"));
        console.log(notesArray);
    }
    let note = new Note();
    notesArray.push(note);
    localStorage.setItem("notes", JSON.stringify(notesArray));
    location.reload();
}

function checkIfAnyNotes() {
    if(localStorage.getItem("notes")) {
        return true;
    }
    return false;
}

function deleteNote() {
    let index = this.parentElement.rowIndex;
    let notesArray = [];
        if(checkIfAnyNotes()) {
            notesArray = JSON.parse(localStorage.getItem("notes"));
        }
    notesArray.splice(index, 1);
    localStorage.setItem("notes", JSON.stringify(notesArray));
    location.reload();
}

function editData(index, isTitle, editedMessage) {

    let notesArray = [];
        if(checkIfAnyNotes()) {
            notesArray = JSON.parse(localStorage.getItem("notes"));
        }
     if(isTitle) {
        notesArray[index].title = editedMessage;
     } else {
        notesArray[index].content = editedMessage;
     }
    localStorage.setItem("notes", JSON.stringify(notesArray));
}

function displayNotes() {
    let notesArray = [];
    if(checkIfAnyNotes()) {
        notesArray = JSON.parse(localStorage.getItem("notes"));
    }
    let table = document.createElement("table");

    for(let note in notesArray) {
        let row = document.createElement("tr");

        let title = document.createElement("td");
        let title_textArea = document.createElement("textarea");
        title_textArea.textContent = notesArray[note].title;
        title_textArea.onblur = function() {
                              let index = this.parentElement.parentElement.rowIndex;
                              editData(index, true, this.value);
                          };

        title.appendChild(title_textArea);
        row.appendChild(title);
        
        let content = document.createElement("td");
        let content_textArea = document.createElement("textarea");
        content_textArea.textContent = notesArray[note].content;
        content_textArea.onblur = function() {
                let index = this.parentElement.parentElement.rowIndex;
                editData(index, false, this.value);
            };

        content.appendChild(content_textArea);
        row.appendChild(content);

        let deleteMe = document.createElement("button");
        deleteMe.textContent = "delete";
        deleteMe.onclick = deleteNote;
        row.appendChild(deleteMe);

        table.appendChild(row);
    }
    document.getElementById("tableSpace").appendChild(table);
}
