<#macro form>

 <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h6 class="modal-title">CSV Files:&nbsp;</h6>
                <div class="info">
                </div>
                </h4>
            </div>
            <div class="modal-body" id="bodyMainForm">
               <input id="unit" type="file" name="unitFile" required="required" placeholder="Unit.CSV"/>Unit.csv</input>
               <input id="hierarchy" type="file" name="hierarchyFile" required="required" placeholder="UnitHierarchy.csv"/>UnitHierarchy.csv</input><br>
            </div>
            <div class="modal-footer">
                  <button id="showStructure" class="btn btn-secondary" onclick="showStructure()">
                    Show structure
                  </button>
                   <button id="checkUnits" class="btn btn-secondary" onclick="checkUnits()">
                                     Check units
                                    </button>
            </div>
        </div>
    </div>

 <div id="resultDiv" class="modal-dialog" style="display: none;">
        <div class="modal-content">
            <div class="modal-header">
                <h6 class="modal-title" id="title"></h6>
                <div class="info">
                </div>
            </div>
            <div class="modal-body">
            <div id="unitInfo">
            </div>
            </div>
            <div class="modal-footer"></div>
        </div>
</div>
</#macro>


