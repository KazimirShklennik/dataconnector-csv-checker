<#macro form path but_name>

 <div class="modal-dialog">
        <div class="modal-content">
           <form action="/check" method="post" role="form" enctype="multipart/form-data">
            <div class="modal-header">
                <h4 class="modal-title">SCV Files:&nbsp;
                <div class="info">
                </div>
                </h4>
            </div>

            <div class="modal-body">
              <H4> Unit.csv: <br><br>
               <input type="file" name="unitFile" required="required" placeholder="UNIT.CSV"/><br>
                 <H4> UNITHIERARCHY.CSV: <br><br>
                <input type="file" name="hierarchyFile" required="required" placeholder="UNITHIERARCHY.CSV"/><br>
            </div>


            <div class="modal-footer">
                <button type="submit" class="uui-button lime-green">
                        CHECK</button>
            </div>
             </form>
        </div>
    </div>


<#if tree?exists>
 <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Structure:&nbsp;</h4>
                <div class="info">
                </div>
              
            </div>

            <div class="modal-body">
                      <div id="tree"></div>
                         <script>
                             var tree = new Tree(document.getElementById('tree'));
                             tree.json([${tree}]);
                        </script>
            </div>

            <div class="modal-footer"></div>

        </div>
    </div>

 </#if>

</#macro>


