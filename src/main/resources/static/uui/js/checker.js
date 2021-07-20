async function check() {
    let unit = document.getElementById("unitTree");
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
                {fold:true,openAllFold:false}
            );

            document.getElementById("treeUnits").appendChild(tw.root);
      
          });      
        
          
    });
}


