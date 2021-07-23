async function showStructure() {
    let unit = document.getElementById("unit");
    let hierarchy = document.getElementById("hierarchy");
    let formData = new FormData();

    formData.append("unitFile", unit.files[0]);
    formData.append("hierarchyFile", hierarchy.files[0]);

    var buttonShowStructure = document.getElementById("showStructure");
    addSpinnerToButton(buttonShowStructure);
    var unitInfo = document.getElementById("unitInfo");
    const rawResponse = await fetch('/structure', {
        method: 'POST',
        body: formData
    })
        .then(response => {
            if (!response.ok) {
                unitInfo.innerHTML = "";
                throw new Error("Check files please.");
            }

            let resultDiv = document.getElementById("resultDiv");
            resultDiv.style.display = "block";
            let title = document.getElementById("title");
            addTextToBlock(title, "Structure");
            addTextToBlock(unitInfo, "");
            unitInfo.appendChild(addSpinnerToDiv())

            response.json().then(data => {

                var tw = new TreeView(
                    [data],
                    { fold: true, openAllFold: false }
                );
                var unitInfo = document.getElementById("unitInfo");
                addTextToBlock(unitInfo, "");
                buttonShowStructure.innerHTML = "Show structure";
                unitInfo.appendChild(tw.root);
            });
        })
        .catch((error) => {
            let resultDiv = document.getElementById("resultDiv");
            resultDiv.style.display = "block";
            var buttonShowStructure = document.getElementById("showStructure");
            buttonShowStructure.innerHTML = "Show structure";
            let unitInfo = document.getElementById("unitInfo");
            unitInfo.appendChild(alert(error));
        });
}

async function checkUnits() {
    let unit = document.getElementById("unit");
    let formData = new FormData();
    formData.append("unitFile", unit.files[0]);
    var buttonCheckUnits = document.getElementById("checkUnits");
    addSpinnerToButton(buttonCheckUnits);
    let title = document.getElementById("title");
    addTextToBlock(title, "Units");
    const rawResponse = await fetch('/units', {
        method: 'POST',
        body: formData
    })
        .then(response => {
            var resultDiv = document.getElementById("resultDiv");
            var unitInfo = document.getElementById("unitInfo");
            if (!response.ok) {
                addTextToBlock(unitInfo, "");
                throw new Error("Check files please.");
            }
            resultDiv.style.display = "block";
            response.json().then(data => {
               addTextToBlock(unitInfo, "");
               unitInfo.appendChild(alert(data));
               addTextToBlock(buttonCheckUnits, "Check units");
            })
        })
        .catch((error) => {
                    let resultDiv = document.getElementById("resultDiv");
                    resultDiv.style.display = "block";
                    var buttonCheckUnits = document.getElementById("checkUnits");
                    addTextToBlock(buttonCheckUnits, "Check units");
                    let unitInfo = document.getElementById("unitInfo");
                    unitInfo.appendChild(alert(error));
                });
}

function addSpinnerToDiv() {
    var textCenterDiv = document.createElement("div");
    textCenterDiv.classList.add("text-center");

    var spinnerDiv = document.createElement("div");
    spinnerDiv.classList.add("spinner-border");
    spinnerDiv.classList.add("text-secondary");
    spinnerDiv.setAttribute("role", "status");

    var loading = document.createElement("span");
    loading.classList.add("sr-only");
    addTextToBlock(loading, "Loading...")

    spinnerDiv.appendChild(loading);
    textCenterDiv.appendChild(spinnerDiv)
    return textCenterDiv;
}

function alert(error) {
    var alert = document.createElement("div");
    alert.classList.add("alert");
    alert.classList.add("alert-danger");
    alert.setAttribute("role", "status");
    alert.innerHTML = error;
    return alert;
}

function addSpinnerToButton(buttonId) {
    addTextToBlock(buttonId, "");
    var spinnerButtonSpan = document.createElement("span");
    spinnerButtonSpan.classList.add("spinner-border");
    spinnerButtonSpan.classList.add("spinner-border-sm");
    spinnerButtonSpan.setAttribute("role", "status");
    spinnerButtonSpan.setAttribute("aria-hidden", "true");
    buttonId.appendChild(spinnerButtonSpan);
    buttonId.innerHTML += "&nbsp;Loading..."
}

function addTextToBlock(block, text) {
    block.innerHTML = text;
}


