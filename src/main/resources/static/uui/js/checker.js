async function showStructure() {
    let unit = document.getElementById("unitTree");
    let hierarchy = document.getElementById("hierarchy");
    let formData = new FormData();

    formData.append("unitFile", unit.files[0]);
    formData.append("hierarchyFile", hierarchy.files[0]);

     var buttonShowStructure = document.getElementById("showStructure");
       addSpinnerToButton(buttonShowStructure);

    const rawResponse = await fetch('/showStructure', {
        method: 'POST',
        body: formData
    })
    .then(response => {
        let structureDiv = document.getElementById("structureDiv");
        let treeDiv=document.getElementById("treeUnits");
        structureDiv.style.display = "block";
        treeDiv.innerHTML = "";
        var textCenterDiv = document.createElement("div");
        textCenterDiv.classList.add("text-center");

        var spinnerDiv = document.createElement("div");
        spinnerDiv.classList.add("spinner-border");
        spinnerDiv.classList.add("text-secondary");
        spinnerDiv.setAttribute("role", "status");

        var loading = document.createElement("span");
        loading.classList.add("sr-only");
        loading.innerHTML = "Loading...";

        spinnerDiv.appendChild(loading);
        textCenterDiv.appendChild(spinnerDiv)

        treeDiv.appendChild(textCenterDiv)
        response.json().then(data => {

            var tw = new TreeView(
                [data],
                {fold:true,openAllFold:false}
            );
            var treeDiv=document.getElementById("treeUnits");
            treeDiv.innerHTML = "";
            buttonShowStructure.innerHTML = "Show structure";
            treeDiv.appendChild(tw.root);
          });
    });
}

function addSpinnerToButton(buttonId) {
     buttonId.innerHTML="";
     var spinnerButtonSpan = document.createElement("span");
     spinnerButtonSpan.classList.add("spinner-border");
     spinnerButtonSpan.classList.add("spinner-border-sm");
     spinnerButtonSpan.setAttribute("role", "status");
     spinnerButtonSpan.setAttribute("aria-hidden", "true");
     buttonId.appendChild(spinnerButtonSpan);
     buttonId.innerHTML+= "&nbsp;Loading..."
}


