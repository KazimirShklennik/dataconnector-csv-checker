<#macro mainPage>
    <!DOCTYPE html>
    <#import "/spring.ftl" as spring />
    <html lang="en">

    <head>
        <meta charset="utf-8"/>
        <title>Csv-checker</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
        <#include "source.ftl">
    </head>
    <body>
    <div class="wrapper">
        <header>
            <#include "header.ftl">
        </header>
        <div class="uui-main-container">
            <main>
               <#if successfully?exists>
                        <div class="uui-alert-messages lime-green" role="alert">
                        <script> $('.uui-alert-messages').delay(2000).animate({'opacity':'0'},500);</script>
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                <span aria-hidden="true"><span></span><span></span></span>
                            </button>
                            <i class="fa fa-exclamation-triangle"></i>
                            <strong></strong> SUCCESSFULLY!
                        </div>
                        </#if>
                <#nested>
            </main>
        </div>
        <footer>
            <#include "footer.ftl">
        </footer>
    </div>
    <script src="<@spring.url '/uui/js/checker.js'/>"></script>
    </body>
    </html>
</#macro>