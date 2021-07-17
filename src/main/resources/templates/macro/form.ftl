<#macro form path but_name>

 <div class="modal-dialog">
        <div class="modal-content">
           <form action="/check" method="post" role="form" enctype="multipart/form-data">
            <div class="modal-header">
                <h4 class="modal-title">SCV Files:&nbsp;
                <div class="info">
                 <script> $('.info').delay(2000).animate({'opacity':'0'},1000);</script>
                <#if fileName?exists>${fileName},&nbsp;</#if>
                <#if toSave?exists>Saved: ${toSave}</#if>
                </div>
                </h4>
            </div>

            <div class="modal-body">
              <H4> Unit.csv: <br><br>
               <input type="file" name="unitFile" required="required" placeholder="UNIT.CSV"/><br>
                 <H4> UNITHIERARCHY.CSV: <br><br>
                <input type="file" name="hierarchyFile" required="required" placeholder="UNITHIERARCHY.CSV"/><br>
            </div>

        <#if tree?exists>

            <h4> <div id="tree">Structure: <br><br></div></h4>
           <script>
               var tree = new Tree(document.getElementById('tree'));
               tree.json([${tree}]);
          </script>
         </#if>


            <div class="modal-footer">
                <button type="submit" class="uui-button lime-green">
                        CHECK</button>
            </div>
             </form>
        </div>
    </div>



</#macro>


