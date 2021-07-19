async function check() {
    let unit = document.getElementById("unit");
    let hierarchy = document.getElementById("hierarchy");
    let formData = new FormData();

    formData.append("unitFile", unit.files[0]);
    formData.append("hierarchyFile", hierarchy.files[0]);

    const rawResponse = await fetch('/check', {
        method: 'POST',
        body: formData
    })
    .then(response => {
        let structureDiv = document.getElementById("structureDiv");
        structureDiv.style.display = "block";    

        response.json().then(data => {

            var tw = new TreeView(
                [data],
                {showAlwaysCheckBox:false,fold:true,openAllFold:false}
            );

            document.getElementById("treeUnits").appendChild(tw.root);
      
          });      
        
          
    });
}


