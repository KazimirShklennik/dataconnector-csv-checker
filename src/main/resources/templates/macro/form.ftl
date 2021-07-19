<#macro form path but_name>

 <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">SCV Files:&nbsp;
                <div class="info">
                </div>
                </h4>
            </div>
            <div class="modal-body">
              <H4> Unit.csv: <br><br>
               <input id="unit" type="file" name="unitFile" required="required" placeholder="UNIT.CSV"/><br>
                    <H4> UNITHIERARCHY.CSV: <br><br>
                <input id="hierarchy" type="file" name="hierarchyFile" required="required" placeholder="UNITHIERARCHY.CSV"/><br>
            </div>
            <div class="modal-footer">
                  <button class="uui-button lime-green" onclick="check()">CHECK</button>

            </div>
        </div>
    </div>

 <div id="structureDiv" class="modal-dialog" style="display: none;">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Structure:&nbsp;</h4>
                <div class="info">
                </div>
            </div>
            <div class="modal-body">
            <div id="treeUnits"></div>
            </div>
            <div class="modal-footer"></div>
        </div>
</div>
</#macro>


