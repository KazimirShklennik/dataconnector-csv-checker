<#import "macro/main.ftl" as c>
<@c.mainPage>
    <section class="uui-info-panel-vertical fuchsia">
        <div class="info-panel-body">
            <div class="info-panel-section">
                <#if code?exists>
                    ${code}<br>
                </#if>
            </div>
        </div>
        <div class="info-panel-footer">
            <div class="info-panel-section">
                <#if datetime?exists>
                    ${datetime}<br>
                </#if>
                <#if url?exists>
                    ${url}<br><br>
                </#if>
                <#if exception?exists>
                    ${exception}<br>
                </#if>
            </div>
        </div>
    </section>
</@c.mainPage>



