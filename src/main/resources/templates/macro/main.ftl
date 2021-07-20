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