

<#function max x y>
    <#if (x<y)><#return y><#else><#return x></#if>
</#function>
<#function min x y>
    <#if (x<y)><#return x><#else><#return y></#if>
</#function>
<#function add x y>
    <#return x+y>
</#function>

<#macro pages totalPages p url>
    <#assign size = totalPages>
    <#if (p<=5)> <#-- p among first 5 pages -->
        <#assign interval = 1..(min(5,size))>
    <#elseif ((size-p)<5)> <#-- p among last 5 pages -->
        <#assign interval = (max(1,(size-4)))..size >
    <#else>
        <#assign interval = (p-2)..(p+2)>
    </#if>
    <#if !(interval?seq_contains(1))>
     <a href="${url}?p=1">1</a> ... <#rt>
    </#if>
    <#list interval as page>
        <#if page=p>
         <${page}> <#t>
        <#else>
         <a href="${url}?p=${page}">${page} <#t></a>
        </#if>
    </#list>
    <#if !(interval?seq_contains(size))>
     ... <a href="${url}?p=${size}">${size}</a><#lt>
    </#if>

	<#assign next = (add(p,1))>
	<#if p=size>
	下一页
	<#else>
	<a href="${url}?p=${next}">下一页</a>
	</#if>
</#macro>